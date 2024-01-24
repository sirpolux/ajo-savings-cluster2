package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserKyc extends BaseEntity {
    private String gender;
    @Column(nullable = false)
    private String occupation;
    @Column(nullable = false)
    private String passport;
    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String identificationType;

    @Column(nullable = false, unique = true)
    private Long bvn;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private Long identificationNumber;
    private String identificationDocument;
    private String proofOfAddress;
}
