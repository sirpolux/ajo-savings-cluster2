package com.cluster2.ajosavingscluster2.dto;

import com.cluster2.ajosavingscluster2.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinAjoGroupRequestDto {
    private User firstName;
    private String groupName;
}
