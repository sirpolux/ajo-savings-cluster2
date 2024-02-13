package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.KycUpdateResponse;
import com.cluster2.ajosavingscluster2.dto.UserKycDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface KycServices {
    ResponseEntity<KycUpdateResponse> updateKyc(UserKycDto userKycDto);
}
