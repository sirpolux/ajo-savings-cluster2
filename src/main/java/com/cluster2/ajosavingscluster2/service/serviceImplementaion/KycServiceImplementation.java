package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.KycUpdateResponse;
import com.cluster2.ajosavingscluster2.dto.UserKycDto;
import com.cluster2.ajosavingscluster2.mapper.KycMapper;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.repository.KycRepository;
import com.cluster2.ajosavingscluster2.service.KycServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class KycServiceImplementation implements KycServices {
    private final KycRepository kycRepository;
    private final KycMapper kycMapper;
    @Override
    public ResponseEntity<KycUpdateResponse> updateKyc(UserKycDto userKycDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User)authentication.getPrincipal();
        log.info("Received request to update kyc for:  {}", user.getFirstName());

        userKycDto.setUser(user);
        try{
            kycRepository.save(kycMapper.userKycDtoToUserKyc(userKycDto));
            log.info("Kyc successfully updated kyc for:  {}", user.getFirstName());
            return new ResponseEntity<>(KycUpdateResponse.builder()
                    .msg("Kyc updated successfully").status("completed").build(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(KycUpdateResponse.builder()
                    .msg("Error updating Kyc").status("Error").build(), HttpStatus.BAD_REQUEST);
        }

    }


}
