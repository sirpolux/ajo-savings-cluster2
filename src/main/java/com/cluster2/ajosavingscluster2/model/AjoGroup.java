package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AjoGroup extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String groupName;
    private String groupIcon;
    @Column(nullable = false)
    private Double contributionAmount;
    @Column(nullable = false)
    private Date expectedStartDate;
    @Column(nullable = false)
    private Date expectedEndDate;

    private String duration;
    @Column(nullable = false)
    private Integer numberOfParticipants;
    @Column(nullable = false)
    private String contributionFrequency;
    @Column(nullable = false)
    private String contributionTime;
    @Column(nullable = false)
    private String purposeAndGoals;
    @ManyToOne
    private User admin;


}
