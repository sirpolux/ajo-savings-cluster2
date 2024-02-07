package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.UserKyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<UserKyc, Long> {
}
