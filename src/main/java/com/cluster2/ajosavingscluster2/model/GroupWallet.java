package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupWallet extends WalletBaseEntity {
    @OneToOne
    private SavingsGroup savingsGroup;
}
