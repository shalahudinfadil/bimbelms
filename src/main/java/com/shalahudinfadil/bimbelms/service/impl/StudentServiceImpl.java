package com.shalahudinfadil.bimbelms.service.impl;


import com.shalahudinfadil.bimbelms.dto.StudentDTO;
import com.shalahudinfadil.bimbelms.dto.mapstruct.StudentMapper;
import com.shalahudinfadil.bimbelms.entity.Student;
import com.shalahudinfadil.bimbelms.repository.StudentRepo;
import com.shalahudinfadil.bimbelms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudentMapper mapper;
    @Override
    public List<StudentDTO> getAll() {
        ArrayList<Student> students = (ArrayList<Student>) studentRepo.findAll();
        ArrayList<StudentDTO> studentDTOS = (ArrayList<StudentDTO>) students.stream().map(student -> mapper.entityToDTO(student)).collect(Collectors.toList());

        return studentDTOS;
    }

    @Override
    public StudentDTO getById(String id) {
        Optional<Student> studentOptional = studentRepo.findById(UUID.fromString(id));

        if (studentOptional.isEmpty()) {
            return null;
        }

        return mapper.entityToDTO(studentOptional.get());
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.name());
        student.setGender(dto.gender());
        student.setDateOfBirth(dto.dob());
        student.setAddress(dto.address());

        studentRepo.save(student);

        return dto;
    }

    @Override
    public StudentDTO update(String id, LinkedHashMap<String, String> requestDTO) {
        Optional<Student> studentOpt = studentRepo.findById(UUID.fromString(id));

        if (studentOpt.isEmpty()) {
            return null;
        }

        StudentDTO incomingDTO = mapper.hashMaptoDTO(requestDTO);

        Student student = studentOpt.get();
        student.setName(incomingDTO.name());
        student.setGender(incomingDTO.gender());
        student.setDateOfBirth(incomingDTO.dob());
        student.setAddress(incomingDTO.address());

        studentRepo.save(student);

        return incomingDTO;
    }

    @Override
    public void softDelete(String id) {
        studentRepo.deleteById(UUID.fromString(id));
    }
}
