package com.shalahudinfadil.bimbelms.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@SQLDelete(sql = "UPDATE Student s SET s.deletedAt = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction(value = "deleted_at IS NULL")
@Table(name = "students")
public class Student extends BaseAuditable {
    private String name;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    private String gender;
    private String address;

    public void setDateOfBirth(String date) {
        this.dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
