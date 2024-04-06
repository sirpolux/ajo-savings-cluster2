package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
