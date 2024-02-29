package com.cluster2.ajosavingscluster2.controller;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.cluster2.ajosavingscluster2.dto.AjoGroupResponse;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupRequestDto;
import com.cluster2.ajosavingscluster2.dto.JoinAjoGroupResponseDto;
import com.cluster2.ajosavingscluster2.exception.MultipleMemberInstanceException;
import com.cluster2.ajosavingscluster2.service.AjoGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/ajo_group")
@RequiredArgsConstructor

public class AjoGroupController {

    private final AjoGroupService ajoGroupService;
    @PostMapping("/create_ajo_group")
    public ResponseEntity<AjoGroupResponse> createAjoGroup(@RequestBody AjoGroupDto ajoGroupRequest){
        return ajoGroupService.createAjoGroup(ajoGroupRequest);
    }

    @PostMapping("/join_ajo_group")
    public ResponseEntity<JoinAjoGroupResponseDto> joinGroup(@RequestBody JoinAjoGroupRequestDto joinGroupRequestDto) {
        ResponseEntity<JoinAjoGroupResponseDto> response = ajoGroupService.joinGroup(joinGroupRequestDto);
        System.out.println(response);
        return response;
    }
    @ExceptionHandler(MultipleMemberInstanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleMultipleMemberInstanceException(MultipleMemberInstanceException ex){
        HashMap<String, String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return error;
    }
}
