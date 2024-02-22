package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class GlobalWallet extends WalletBaseEntity {
    @OneToOne
    private User user;
}
