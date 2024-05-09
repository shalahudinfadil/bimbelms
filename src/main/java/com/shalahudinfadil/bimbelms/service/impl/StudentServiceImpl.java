package com.shalahudinfadil.bimbelms.service.impl;


import com.shalahudinfadil.bimbelms.dto.mapstruct.StudentMapper;
import com.shalahudinfadil.bimbelms.dto.request.create.StudentCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.StudentUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.StudentResponseDTO;
import com.shalahudinfadil.bimbelms.entity.Student;
import com.shalahudinfadil.bimbelms.repository.StudentRepo;
import com.shalahudinfadil.bimbelms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudentMapper mapper;
    @Override
    public List<StudentResponseDTO> getAll() {
        ArrayList<Student> students = (ArrayList<Student>) studentRepo.findAll();
        ArrayList<StudentResponseDTO> studentDTOS = (ArrayList<StudentResponseDTO>) mapper.entityListToResponseDtoList(students);

        return studentDTOS;
    }

    @Override
    public StudentResponseDTO getById(String id) {
        Optional<Student> studentOptional = studentRepo.findById(UUID.fromString(id));

        return studentOptional.map(student -> mapper.entityToResponseDto(student)).orElse(null);

    }

    @Override
    public StudentResponseDTO save(@Valid StudentCreateDTO dto) {
        Student student = mapper.requestDtoToEntity(dto);

        StudentResponseDTO saveDTO = mapper.entityToResponseDto(studentRepo.save(student));

        return saveDTO;
    }

    @Override
    public StudentResponseDTO update(@Valid StudentUpdateDTO dto) {
        Optional<Student> studentOpt = studentRepo.findById(UUID.fromString(dto.id()));

        if (studentOpt.isEmpty()) {
            return null;
        }

        Student student = mapper.requestDtoToEntity(dto);

        StudentResponseDTO saveDTO = mapper.entityToResponseDto(studentRepo.save(student));

        return saveDTO;
    }

    @Override
    public void softDelete(String id) {
        studentRepo.deleteById(UUID.fromString(id));
    }
}
