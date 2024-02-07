package com.cluster2.ajosavingscluster2.controller;


import com.cluster2.ajosavingscluster2.dto.KycUpdateResponse;
import com.cluster2.ajosavingscluster2.dto.UserKycUpdateRequest;
import com.cluster2.ajosavingscluster2.service.KycServices;
import com.cluster2.ajosavingscluster2.service.serviceImplementaion.KycServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {
    private final KycServices kycServices;
    @PostMapping("update_kyc")
    public ResponseEntity<KycUpdateResponse> updateKyc(UserKycUpdateRequest kycUpdateRequest){

        KycUpdateResponse kycUpdateResponse = kycServices.updateKyc(kycUpdateRequest);
        System.out.println(kycUpdateResponse);
        return null;
    }
}
