package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.KycUpdateResponse;
import com.cluster2.ajosavingscluster2.dto.UserKycUpdateRequest;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.model.UserKyc;
import com.cluster2.ajosavingscluster2.repository.KycRepository;
import com.cluster2.ajosavingscluster2.service.KycServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class KycServiceImplementation implements KycServices {
    private final KycRepository kycRepository;
    @Override
    public KycUpdateResponse updateKyc(UserKycUpdateRequest userKycUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User)authentication.getPrincipal();
        log.info("Received request to update kyc for:  {}", user.getEmail());
        if (validInput(userKycUpdateRequest.getGender())){
            log.info("Gender note provided");
        }
        UserKyc userKyc =  UserKyc.builder().
                user(user)
                .gender(userKycUpdateRequest.getGender())
                .occupation(userKycUpdateRequest.getOccupation())
                .passport(userKycUpdateRequest.getPassport())
                .dateOfBirth(userKycUpdateRequest.getDateOfBirth())
                .identificationType(userKycUpdateRequest
                .getIdentificationType())
                .bvn(Long.valueOf(userKycUpdateRequest.getBvn()))
                .address(userKycUpdateRequest.getAddress())
                .identificationNumber(Long.valueOf(userKycUpdateRequest.getIdentificationNumber()))
                .identificationDocument(userKycUpdateRequest.getIdentificationDocument())
                .proofOfAddress(userKycUpdateRequest.getProofOfAddress())
                .build();
        kycRepository.save(userKyc);
        return KycUpdateResponse.builder().msg("Kyc updated successfully").status("completed").build();
    }

    private boolean validInput(String string){
        return  string!=null;
    }
    private boolean validInput (Long l){
        return  l!=null;
    }
    private boolean validInput(Date d){
        return d!=null;
    }


}
