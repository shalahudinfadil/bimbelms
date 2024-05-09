package com.shalahudinfadil.bimbelms.repository;

import com.shalahudinfadil.bimbelms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID> {
}
