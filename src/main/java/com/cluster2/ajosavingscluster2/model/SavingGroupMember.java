package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SavingGroupMember extends BaseEntity{
    private Long savingGroupId;
    private Long userId;
}
