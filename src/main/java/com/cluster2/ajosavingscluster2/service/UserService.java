package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.ApiResponse;
import com.cluster2.ajosavingscluster2.dto.CreditDebitWalletRequest;
import com.cluster2.ajosavingscluster2.dto.LoginRequest;
import com.cluster2.ajosavingscluster2.dto.UserRequest;
import com.cluster2.ajosavingscluster2.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ApiResponse> signUp (UserRequest userRequest);
    ApiResponse login(LoginRequest loginRequest);
    ApiResponse credit (CreditDebitWalletRequest creditDebitWalletRequest);
    User getUser ();


}
