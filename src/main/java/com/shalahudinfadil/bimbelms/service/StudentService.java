package com.shalahudinfadil.bimbelms.service;

import com.shalahudinfadil.bimbelms.dto.StudentDTO;

import java.util.LinkedHashMap;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAll();
    StudentDTO getById(String id);
    StudentDTO save(StudentDTO student);
    StudentDTO update(String id, LinkedHashMap<String, String> requestDTO);
    void softDelete(String id);
}
