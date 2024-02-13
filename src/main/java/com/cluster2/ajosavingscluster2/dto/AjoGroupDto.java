package com.cluster2.ajosavingscluster2.dto;


import com.cluster2.ajosavingscluster2.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AjoGroupDto {
    private Long id;
    private String groupIcon;
    private String groupName;
    private Double contributionAmount;
    private Date expectedStartDate;
    private Date expectedEndDate;
    private String duration;
    private Integer numberOfParticipants;
    private String time;
    private String purposeAndGoals;
    private User user;
}
