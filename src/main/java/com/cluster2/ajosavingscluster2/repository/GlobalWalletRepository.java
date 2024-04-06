package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalWalletRepository extends JpaRepository<GlobalWallet,Long> {
    GlobalWallet findByUserId(Long userId);
    GlobalWallet findByUser(User user);

}
