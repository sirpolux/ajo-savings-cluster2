package com.cluster2.ajosavingscluster2.controller;

import com.cluster2.ajosavingscluster2.dto.ApiResponse;
import com.cluster2.ajosavingscluster2.dto.UserRequest;
import com.cluster2.ajosavingscluster2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
    public final UserService userService;

    @PostMapping("create-user")
    public ApiResponse signUp(@RequestBody UserRequest userRequest) {
        System.out.println("path1");
        return userService.signUp(userRequest);

    }
}
