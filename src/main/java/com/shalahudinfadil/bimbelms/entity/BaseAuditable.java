package com.shalahudinfadil.bimbelms.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseAuditable {

    @Id
    @Column(name = "Id")
    private UUID id;

    @Column(name = "created_by")
    private UUID createdBy;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_by")
    private UUID updatedBy;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_by")
    private UUID deletedBy;
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    @PrePersist
    protected void prePresist() {
        this.id = UUID.randomUUID();
    }

    @PreRemove
    protected void preRemove() {
        this.deletedAt = LocalDateTime.now();
    }
}
