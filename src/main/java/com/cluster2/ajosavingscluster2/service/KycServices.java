package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.KycUpdateResponse;
import com.cluster2.ajosavingscluster2.dto.UserKycUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface KycServices {
    KycUpdateResponse updateKyc(UserKycUpdateRequest userKycUpdateRequest);
}
