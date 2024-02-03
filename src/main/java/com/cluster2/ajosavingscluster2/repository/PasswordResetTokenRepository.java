package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String passwordResetToken);
}