package com.shalahudinfadil.bimbelms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "md_levels")
public class Level extends BaseAuditable implements Serializable {
    private String name;
    @Column(name = "level_order")
    private Integer order;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<LevelTier> tiers;
}
