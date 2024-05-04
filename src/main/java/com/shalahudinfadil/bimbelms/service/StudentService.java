package com.shalahudinfadil.bimbelms.service;

import com.shalahudinfadil.bimbelms.dto.StudentDTO;
import com.shalahudinfadil.bimbelms.entity.Student;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    List<StudentDTO> getAll();
    StudentDTO getById(String id);
    StudentDTO save(StudentDTO student);
    StudentDTO update(String id, LinkedHashMap<String, String> requestDTO);
    void softDelete(String id);
}
