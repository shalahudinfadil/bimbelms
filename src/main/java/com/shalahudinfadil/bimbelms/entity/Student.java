package com.shalahudinfadil.bimbelms.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@SQLDelete(sql = "UPDATE students SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction(value = "deleted_at IS NULL")
@Table(name = "md_students")
public class Student extends BaseAuditable {
    @Column(unique = true)
    @Setter(AccessLevel.NONE)
    private String uniqueCode;
    private String name;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    @Nullable
    private String email;
    @Nullable
    private String phoneNumber;

    @PrePersist
    private void prePersist() {
        uniqueCode = (name + dateOfBirth.toString().replace("-","") +gender).toUpperCase();

    }
}
