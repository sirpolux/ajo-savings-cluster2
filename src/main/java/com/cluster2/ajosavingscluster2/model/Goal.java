package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Goal extends BaseEntity{
    @Column(nullable = false)
    private String target;
    @Column(nullable = false)
    private Long targetAmount;
    @Column(nullable = false)
    private String frequency;
    @Column(nullable = false)
    private LocalDateTime startDate;
    private LocalDateTime withdrawalDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
