package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserKyc  extends AuditBaseEntity {
    @OneToOne
    private User user;
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
