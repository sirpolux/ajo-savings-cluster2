package com.cluster2.ajosavingscluster2.dto;

import com.cluster2.ajosavingscluster2.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JoinAjoGroupRequestDto {
    private User firstName;
    private String groupName;

}
