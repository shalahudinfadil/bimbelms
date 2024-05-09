package com.shalahudinfadil.bimbelms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name="md_lookups")
public class Lookup extends BaseAuditable {
    @Column(name = "lookup_group")
    private String group;
    private String value;
    @Column(name = "data_type")
    private String dataType;
    @Column(name = "lookup_order")
    private int order;
}
