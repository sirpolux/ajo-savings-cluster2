package com.cluster2.ajosavingscluster2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long  getId(){
        return id;
    }
}
