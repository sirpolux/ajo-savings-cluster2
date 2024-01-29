package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.ApiResponse;
import com.cluster2.ajosavingscluster2.dto.UserRequest;

public interface UserService {
    ApiResponse signUp (UserRequest userRequest);
}
