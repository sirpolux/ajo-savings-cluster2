package com.cluster2.ajosavingscluster2.repository;

import com.cluster2.ajosavingscluster2.model.AjoGroup;
import com.cluster2.ajosavingscluster2.model.GroupMember;
import com.cluster2.ajosavingscluster2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByAjoGroup(AjoGroup ajoGroup);

    Optional<GroupMember> findByAjoGroupAndUser(AjoGroup ajoGroup, User user);
}
