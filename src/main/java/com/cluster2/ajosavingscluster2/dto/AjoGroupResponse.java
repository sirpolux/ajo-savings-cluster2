package com.cluster2.ajosavingscluster2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AjoGroupResponse{
    private String msg;
    private String status;
}
