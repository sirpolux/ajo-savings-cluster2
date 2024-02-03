package com.cluster2.ajosavingscluster2.controller;

import com.cluster2.ajosavingscluster2.dto.ApiResponse;
import com.cluster2.ajosavingscluster2.dto.LoginRequest;
import com.cluster2.ajosavingscluster2.dto.PasswordResetNewPasswordRequest;
import com.cluster2.ajosavingscluster2.dto.PasswordResetEmailRequest;
import com.cluster2.ajosavingscluster2.dto.UserRequest;
import com.cluster2.ajosavingscluster2.service.PasswordService;
import com.cluster2.ajosavingscluster2.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
    public final UserService userService;
    public final PasswordService passwordService;

    @PostMapping("create-user")
    public ApiResponse signUp(@RequestBody UserRequest userRequest) {
        System.out.println("path1");
        return userService.signUp(userRequest);

    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/password-reset-request")
    public ResponseEntity<ApiResponse> resetPasswordRequest(@RequestBody PasswordResetEmailRequest emailRequest) throws UnsupportedEncodingException, MessagingException {
        String passwordResetUrl = passwordService.resetPasswordRequest(emailRequest);
        ApiResponse response = ApiResponse.builder()
                .responseCode("00")
                .responseMessage("Password reset URL generated successfully")
                .data(passwordResetUrl)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody PasswordResetNewPasswordRequest newPasswordRequest,
                                                        @RequestParam("token") String token) {
        String resetResult = passwordService.resetPassword(newPasswordRequest, token);
        ApiResponse response = ApiResponse.builder()
                .responseCode("00")
                .responseMessage(resetResult)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
