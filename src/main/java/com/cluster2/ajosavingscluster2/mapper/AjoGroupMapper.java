package com.cluster2.ajosavingscluster2.mapper;

import com.cluster2.ajosavingscluster2.dto.AjoGroupDto;
import com.cluster2.ajosavingscluster2.dto.AjoGroupResponse;
import com.cluster2.ajosavingscluster2.model.AjoGroup;
import org.springframework.stereotype.Service;

@Service
public class AjoGroupMapper{
    public AjoGroup ajoGroupDTOToAjoGroup(AjoGroupDto ajoGroupDto) {
        return AjoGroup.builder()
                .groupIcon(ajoGroupDto.getGroupIcon())
                .groupName(ajoGroupDto.getGroupName())
                .contributionAmount(ajoGroupDto.getContributionAmount())
                .expectedStartDate(ajoGroupDto.getExpectedStartDate())
                .expectedEndDate(ajoGroupDto.getExpectedEndDate())
                .duration(ajoGroupDto.getDuration())
                .numberOfParticipants(ajoGroupDto.getNumberOfParticipants())
                .time(ajoGroupDto.getTime())
                .build();
    }
    public AjoGroupDto ajoGroupToAjoGroupDTO(AjoGroup ajoGroup){
        return  AjoGroupDto.builder()
                .id(ajoGroup.getId())
                .groupIcon(ajoGroup.getGroupIcon())
                .groupName(ajoGroup.getGroupName())
                .contributionAmount(ajoGroup.getContributionAmount())
                .expectedStartDate(ajoGroup.getExpectedStartDate())
                .expectedEndDate(ajoGroup.getExpectedEndDate())
                .duration(ajoGroup.getDuration())
                .numberOfParticipants(ajoGroup.getNumberOfParticipants())
                .time(ajoGroup.getTime())
                .build();
    }
}
