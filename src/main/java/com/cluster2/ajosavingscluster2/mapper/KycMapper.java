package com.cluster2.ajosavingscluster2.mapper;

import com.cluster2.ajosavingscluster2.dto.UserKycDto;
import com.cluster2.ajosavingscluster2.model.UserKyc;
import org.springframework.stereotype.Service;

@Service
public class KycMapper {
    public UserKyc userKycDtoToUserKyc(UserKycDto kycDto){
        return UserKyc.builder().
                user(kycDto.getUser())
                .gender(kycDto.getGender())
                .occupation(kycDto.getOccupation())
                .passport(kycDto.getPassport())
                .dateOfBirth(kycDto.getDateOfBirth())
                .identificationType(kycDto
                .getIdentificationType())
                .bvn(kycDto.getBvn())
                .address(kycDto.getAddress())
                .identificationNumber(kycDto.getIdentificationNumber())
                .identificationDocument(kycDto.getIdentificationDocument())
                .proofOfAddress(kycDto.getProofOfAddress())
                .build();
    }

    public UserKycDto userKycToUserKycDto(UserKyc userKyc){
        return UserKycDto.builder()
                .id(userKyc.getId())
                .user(userKyc.getUser())
                .gender(userKyc.getGender())
                .occupation(userKyc.getOccupation())
                .passport(userKyc.getPassport())
                .dateOfBirth(userKyc.getDateOfBirth())
                .identificationType(userKyc.getIdentificationType())
                .bvn(userKyc.getBvn())
                .address(userKyc.getAddress())
                .identificationNumber(userKyc.getIdentificationNumber())
                .identificationDocument(userKyc.getIdentificationDocument())
                .proofOfAddress(userKyc.getProofOfAddress())
                .build();
    }
}
