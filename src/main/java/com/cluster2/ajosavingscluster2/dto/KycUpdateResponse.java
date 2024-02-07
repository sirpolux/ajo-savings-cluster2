package com.cluster2.ajosavingscluster2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KycUpdateResponse {
    private String msg;
    private String status;
}
