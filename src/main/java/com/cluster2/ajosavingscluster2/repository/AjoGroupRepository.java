package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.AjoGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AjoGroupRepository extends JpaRepository<AjoGroup, Long> {
    Optional<AjoGroup> findByGroupName(String groupName);

}
