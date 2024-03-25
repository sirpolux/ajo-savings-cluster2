package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.PersonalWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalWalletRepository extends JpaRepository<PersonalWallet,Long> {
    PersonalWallet findByUserId(java.lang.Long userId);
}
