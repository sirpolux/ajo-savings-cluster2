package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCase(String username);
}
