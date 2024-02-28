package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByWalletNumber(String walletNumber);
}
