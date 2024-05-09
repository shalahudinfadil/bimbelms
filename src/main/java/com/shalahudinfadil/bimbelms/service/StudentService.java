package com.shalahudinfadil.bimbelms.service;

import com.shalahudinfadil.bimbelms.dto.request.create.StudentCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.StudentUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.StudentResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> getAll();
    StudentResponseDTO getById(String id);
    StudentResponseDTO save(@Valid StudentCreateDTO student);
    StudentResponseDTO update(@Valid StudentUpdateDTO requestDTO);
    void softDelete(String id);
}
