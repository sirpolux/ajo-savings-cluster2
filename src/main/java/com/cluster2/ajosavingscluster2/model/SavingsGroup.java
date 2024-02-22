package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SavingsGroup extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String groupName;
    private String groupIcon;
    @Column(nullable = false)
    private Long contributionAmount;
    @Column(nullable = false)
    private LocalDateTime expectedStartDate;
    @Column(nullable = false)
    private LocalDateTime expectedEndDate;
    private String duration;
    @Column(nullable = false)
    private Integer numberOfParticipants;
    @Column(nullable = false)
    private String contributionFrequency;
    @Column(nullable = false)
    private LocalDateTime contributionTime;
    @Column(nullable = false)
    private String purposeAndGoals;
    @OneToOne
    private User admin;


}
