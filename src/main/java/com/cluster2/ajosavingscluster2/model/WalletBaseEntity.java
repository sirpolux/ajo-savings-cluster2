package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.math.BigDecimal;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletBaseEntity extends AuditBaseEntity{
    private String walletNumber;
    private BigDecimal walletBalance= BigDecimal.valueOf(0.0);

}
