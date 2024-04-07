package com.cluster2.ajosavingscluster2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDebitWalletRequest {
    private String walletNumber;
    private BigDecimal amount;


}
