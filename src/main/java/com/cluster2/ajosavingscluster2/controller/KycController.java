package com.cluster2.ajosavingscluster2.controller;


import com.cluster2.ajosavingscluster2.dto.KycUpdateResponse;
import com.cluster2.ajosavingscluster2.dto.UserKycDto;
import com.cluster2.ajosavingscluster2.service.KycServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {
    private final KycServices kycServices;
    @PostMapping("/update_kyc")
    public ResponseEntity<KycUpdateResponse> updateKyc(@RequestBody UserKycDto kycUpdateRequest){
        return kycServices.updateKyc(kycUpdateRequest);
    }

}
