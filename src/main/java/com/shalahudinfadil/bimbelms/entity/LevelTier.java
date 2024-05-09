package com.shalahudinfadil.bimbelms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true, exclude = "level")
@ToString(exclude = "level")
@Entity
@Data
@Table(name = "md_level_tiers")
public class LevelTier extends BaseAuditable {
    private String name;
    @Column(name = "level_tier_order")
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Level level;
}
