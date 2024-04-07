package com.cluster2.ajosavingscluster2.model;

import com.cluster2.ajosavingscluster2.enums.AccountType;
import com.cluster2.ajosavingscluster2.enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transaction extends AuditBaseEntity{
    private BigDecimal transactionAmount;
    private String senderWalletNumber;
    @Enumerated(EnumType.STRING)
    private AccountType senderAccountType;
    private String receiverWalletNumber;
    @Enumerated(EnumType.STRING)
    private AccountType receiverAccountType;
    private String description;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
