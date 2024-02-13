package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.cluster2.ajosavingscluster2.dto.AjoGroupResponse;
import com.cluster2.ajosavingscluster2.mapper.AjoGroupMapper;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.repository.AjoGroupRepository;
import com.cluster2.ajosavingscluster2.service.AjoGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AjoGroupServiceImplementation implements AjoGroupService {

    private final AjoGroupMapper ajoGroupMapper;
    private final AjoGroupRepository ajoGroupRepository;
    @Override
    public ResponseEntity<AjoGroupResponse> createAjoGroup(AjoGroupDto ajoGroupRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User)authentication.getPrincipal();
        log.info("Received request for new Ajo group from:  {}", user.getFirstName());
        ajoGroupRequest.setUser(user);
        try{
            log.info("Creating a new Ajo Group on request for {}",user.getFirstName());
            ajoGroupRepository.save(ajoGroupMapper.ajoGroupDTOToAjoGroup(ajoGroupRequest));
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

}
