package com.shalahudinfadil.bimbelms.repository;

import com.shalahudinfadil.bimbelms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StudentRepo extends JpaRepository<Student, UUID> {
}
