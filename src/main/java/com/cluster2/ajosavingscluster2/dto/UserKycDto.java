package com.cluster2.ajosavingscluster2.dto;

import com.cluster2.ajosavingscluster2.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserKycDto {
    private Long id;
    private String gender;
    private String occupation;
    private String passport;
    private Date dateOfBirth;
    private String identificationType;
    private Long bvn;
    private String address;
    private Long identificationNumber;
    private String identificationDocument;
    private String proofOfAddress;
    private User user;
}
