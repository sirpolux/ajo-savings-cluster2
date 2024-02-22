package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.cluster2.ajosavingscluster2.dto.AjoGroupResponse;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupRequestDto;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupResponseDto;
import org.springframework.http.ResponseEntity;

public interface AjoGroupService{
    ResponseEntity<AjoGroupResponse> createAjoGroup(AjoGroupDto ajoGroupRequest);

    ResponseEntity<JoinAjoGroupResponseDto> joinGroup(JoinAjoGroupRequestDto joinGroupRequestDto);


}
