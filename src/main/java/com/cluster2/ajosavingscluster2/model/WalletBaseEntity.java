package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WalletBaseEntity extends AuditBaseEntity{
    private String walletName;
    private String walletNumber;
    private BigDecimal walletBalance;

}
