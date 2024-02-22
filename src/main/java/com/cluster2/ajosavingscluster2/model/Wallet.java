package com.cluster2.ajosavingscluster2.model;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallet extends BaseEntity{
    private String walletNumber;
}
