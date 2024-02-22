package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.cluster2.ajosavingscluster2.dto.AjoGroupResponse;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupRequestDto;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupResponseDto;
import com.cluster2.ajosavingscluster2.mapper.AjoGroupMapper;
import com.cluster2.ajosavingscluster2.model.AjoGroup;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.repository.AjoGroupRepository;
import com.cluster2.ajosavingscluster2.repository.UserRepository;
import com.cluster2.ajosavingscluster2.service.AjoGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AjoGroupServiceImplementation implements AjoGroupService {

    private final AjoGroupMapper ajoGroupMapper;
    private final AjoGroupRepository ajoGroupRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<AjoGroupResponse> createAjoGroup(AjoGroupDto ajoGroupRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        log.info("Received request for new Ajo group from:  {}", user.getFirstName());
        ajoGroupRequest.setUser(user);
        try {
            log.info("Creating a new Ajo Group on request for {}", user.getFirstName());
            ajoGroupRepository.save(ajoGroupMapper.ajoGroupDTOToAjoGroup(ajoGroupRequest));
            AjoGroupResponse.builder().status("completer").msg("Ajo Group created by " + user.getFirstName()).build();
            return new ResponseEntity<>(
                    AjoGroupResponse.builder().status("completed").msg("Ajo Group created by " + user.getFirstName()).build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    AjoGroupResponse.builder().status("Error encountered").msg("Failed to create group for" + user.getFirstName()).build(),
                    HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<JoinAjoGroupResponseDto> joinGroup(JoinAjoGroupRequestDto joinGroupRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JoinAjoGroupResponseDto("User is not authenticated"));
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JoinAjoGroupResponseDto("User is not authenticated"));
        }

        User user = (User) principal;
        Optional<AjoGroup> optionalGroup = ajoGroupRepository.findByGroupName(joinGroupRequestDto.getGroupName());
        if (optionalGroup.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new JoinAjoGroupResponseDto("Group " + joinGroupRequestDto.getGroupName() + " does not exist."));
        }

        AjoGroup ajoGroup = optionalGroup.get();
        List<User> members = (List<User>) ajoGroup.getMembers();

        if (members == null || members.isEmpty()) {
            String message = "User " + user.getFirstName() + " joined group " + joinGroupRequestDto.getGroupName() + " successfully.";
            JoinAjoGroupResponseDto responseDto = new JoinAjoGroupResponseDto(message);
            return ResponseEntity.ok().body(responseDto);
        } else if (members.size() == 1) {
            User member = members.get(0);
            if (!member.isEnabled()) {
                return ResponseEntity.badRequest()
                        .body(new JoinAjoGroupResponseDto("User " + member.getFirstName() + " is not enabled."));
            }
        } else {
            for (User member : members) {
                if (!member.isEnabled()) {
                    return ResponseEntity.badRequest()
                            .body(new JoinAjoGroupResponseDto("User " + member.getFirstName() + " is not enabled."));
                }
            }
        }
        String message = "User " + user.getFirstName() + " joined group " + joinGroupRequestDto.getGroupName() + " successfully.";
        JoinAjoGroupResponseDto responseDto = new JoinAjoGroupResponseDto(message);
        return ResponseEntity.ok().body(responseDto);
    }
}
