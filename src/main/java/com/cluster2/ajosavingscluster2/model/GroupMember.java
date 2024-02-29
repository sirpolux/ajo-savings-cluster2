package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GroupMember extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "ajo_group_id")
    private AjoGroup ajoGroup;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
