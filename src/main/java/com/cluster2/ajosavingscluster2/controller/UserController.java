package com.cluster2.ajosavingscluster2.controller;

import com.cluster2.ajosavingscluster2.dto.*;
import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.GroupWallet;
import com.cluster2.ajosavingscluster2.model.PersonalWallet;
import com.cluster2.ajosavingscluster2.model.SafeLockWallet;
import com.cluster2.ajosavingscluster2.service.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;
    public final PasswordService passwordService;
    public final AjoGroupService ajoGroupService;
    public final WalletService walletService;
    private final SavingsService savingsService;


    @PostMapping("create-user")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserRequest userRequest) {
        System.out.println("path1");
        System.out.println(userRequest);
        return userService.signUp(userRequest);

    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return userService.login(loginRequest);
    }
    @PostMapping("/credit")
    public ApiResponse credit(@RequestBody CreditDebitWalletRequest creditDebitWalletRequest) {
        return userService.credit(creditDebitWalletRequest);
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


    @PostMapping("/transfer_money")
    public ResponseEntity<TransferResponseDto> transferMoneyToPersonalWallet(@RequestBody TransferRequestDto request) {
        TransferResponseDto response = walletService.transferMoneyToPersonalWallet(request.getUserId(), request.getAmount());
        return ResponseEntity.ok(response);
    }
    @GetMapping ("/dashboard")
    public SavingsResponseDto getAllSavings() {
        List<GlobalWallet> globalWallets = savingsService.getAllGlobalWallets();
        List<GroupWallet> groupWallets = savingsService.getAllGroupWallets();
        List<PersonalWallet> personalWallets = savingsService.getAllPersonalWallets();
        List<SafeLockWallet> safeLockWallets = savingsService.getAllSafeLockWallets();

        return new SavingsResponseDto(globalWallets, groupWallets, personalWallets, safeLockWallets);
    }
}


