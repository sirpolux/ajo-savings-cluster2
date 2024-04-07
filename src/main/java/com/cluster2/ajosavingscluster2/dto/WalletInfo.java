package com.cluster2.ajosavingscluster2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletInfo {
    private String walletName;
    private String walletBalance;
    private String walletNumber;

}
