package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.cluster2.ajosavingscluster2.dto.AjoGroupResponse;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupRequestDto;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupResponseDto;
import com.cluster2.ajosavingscluster2.exception.MultipleMemberInstanceException;
import com.cluster2.ajosavingscluster2.mapper.AjoGroupMapper;
import com.cluster2.ajosavingscluster2.model.AjoGroup;
import com.cluster2.ajosavingscluster2.model.GroupMember;
import com.cluster2.ajosavingscluster2.model.GroupWallet;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.repository.AjoGroupRepository;
import com.cluster2.ajosavingscluster2.repository.GroupMemberRepository;
import com.cluster2.ajosavingscluster2.repository.GroupWalletRepository;
import com.cluster2.ajosavingscluster2.service.AjoGroupService;
import com.cluster2.ajosavingscluster2.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final WalletService walletService;
    private final GroupMemberRepository groupRepository;
    private final GroupWalletRepository groupWalletRepository;
    @Override
    public ResponseEntity<AjoGroupResponse> createAjoGroup(AjoGroupDto ajoGroupRequest) {
        System.out.println(ajoGroupRequest);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User)authentication.getPrincipal();
        log.info("Received request for new Ajo group from:  {}", user.getFirstName());
        ajoGroupRequest.setUser(user);

        try{
            log.info("Creating a new Ajo Group on request for {}",user.getFirstName());
            AjoGroup ajoG =ajoGroupMapper.ajoGroupDTOToAjoGroup(ajoGroupRequest);
            ajoG.setAdmin(user);
            AjoGroup ajoGroup= ajoGroupRepository.save(ajoG);
            ajoGroup.setNumberOfParticipants(1);
            GroupWallet groupWallet = new GroupWallet(ajoGroup);
            groupWallet.setWalletNumber(walletService.generateWalletNumber());

            GroupMember groupMember = new GroupMember();
            groupMember.setAjoGroup(ajoGroup);
            groupMember.setUser(user);

            groupRepository.save(groupMember);
            groupWalletRepository.save(groupWallet);

            AjoGroupResponse.builder().status("completer").msg("Ajo Group created by "+ user.getFirstName()).build();
            return new ResponseEntity<>(
                    AjoGroupResponse.builder().status("completed").msg("Ajo Group created by "+ user.getFirstName()).build(),
                    HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(
                    AjoGroupResponse.builder().status("Error encountered").msg("Failed to create group for"+ user.getFirstName()).build(),
                    HttpStatus.OK);
        }
    }

//    @Override
//    public ResponseEntity<JoinAjoGroupResponseDto> joinGroup1(JoinAjoGroupRequestDto joinGroupRequestDto) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new JoinAjoGroupResponseDto("User is not authenticated"));
//        }
//        Object principal = authentication.getPrincipal();
//        if (!(principal instanceof User user)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new JoinAjoGroupResponseDto("User is not authenticated"));
//        }
//
//        Optional<AjoGroup> optionalGroup = ajoGroupRepository.findByGroupName(joinGroupRequestDto.getGroupName());
//        if (optionalGroup.isEmpty()) {
//            return ResponseEntity.badRequest()
//                    .body(new JoinAjoGroupResponseDto("Group " + joinGroupRequestDto.getGroupName() + " does not exist."));
//        }
//
//        AjoGroup ajoGroup = optionalGroup.get();
//
//        List<GroupMember> members =   groupRepository.findByAjoGroup(ajoGroup);
//
//        Optional<GroupMember> isAlreadyAMember = groupRepository.findByAjoGroupAndUser(ajoGroup,user);
//
//        if (members == null || members.isEmpty()) {
//            String message = "User " + user.getFirstName() + " joined group " + joinGroupRequestDto.getGroupName() + " successfully.";
//            JoinAjoGroupResponseDto responseDto = new JoinAjoGroupResponseDto(message);
//            return ResponseEntity.ok().body(responseDto);
//        } else if (members.size() == 1) {
//            User member = members.get(0);
//            if (!member.isEnabled()) {
//                return ResponseEntity.badRequest()
//                        .body(new JoinAjoGroupResponseDto("User " + member.getFirstName() + " is not enabled."));
//            }
//        } else {
//            for (User member : members) {
//                if (!member.isEnabled()) {
//                    return ResponseEntity.badRequest()
//                            .body(new JoinAjoGroupResponseDto("User " + member.getFirstName() + " is not enabled."));
//                }
//            }
//        }
//        String message = "User " + user.getFirstName() + " joined group " + joinGroupRequestDto.getGroupName() + " successfully.";
//        JoinAjoGroupResponseDto responseDto = new JoinAjoGroupResponseDto(message);
//        return ResponseEntity.ok().body(responseDto);
//    }

    @Override
    public ResponseEntity<JoinAjoGroupResponseDto> joinGroup(JoinAjoGroupRequestDto joinGroupRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User)authentication.getPrincipal();

        Optional<AjoGroup> optionalGroup = ajoGroupRepository.findById(joinGroupRequestDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new JoinAjoGroupResponseDto("Group " + joinGroupRequestDto.getGroupName() + " does not exist."));
        }
        AjoGroup ajoGroup = optionalGroup.get();
        Optional<GroupMember> isAlreadyAMember = groupRepository.findByAjoGroupAndUser(ajoGroup,user);
        if (isAlreadyAMember.isEmpty()) {
            String message = "User " + user.getFirstName() + " joined group " + joinGroupRequestDto.getGroupName() + " successfully.";
            JoinAjoGroupResponseDto responseDto = new JoinAjoGroupResponseDto(message);
            return ResponseEntity.ok().body(responseDto);
        }
        throw new MultipleMemberInstanceException("You are not allowed to join a group multiple times");
    }


}

