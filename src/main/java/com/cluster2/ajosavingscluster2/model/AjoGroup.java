package com.cluster2.ajosavingscluster2.model;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjoGroup extends AuditBaseEntity {
    private String groupIcon;
    @Column(nullable = false)
    private String groupName;
    @Column(nullable = false)
    private Double contributionAmount;
    @Column(nullable = false)
    private Date expectedStartDate;
    @Column(nullable = false)
    private Date expectedEndDate;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private Integer numberOfParticipants;
    @Column(nullable = false)
    private String time;
    private  String purposeAndGoals;
    @OneToOne
    private User admin;

    @ManyToOne
    @JsonManagedReference
    private User members;
}
