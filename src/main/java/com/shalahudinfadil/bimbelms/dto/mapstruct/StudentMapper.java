package com.shalahudinfadil.bimbelms.dto.mapstruct;

import com.shalahudinfadil.bimbelms.dto.StudentDTO;
import com.shalahudinfadil.bimbelms.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "dateOfBirth", source = "dto.dob", dateFormat = "yyyy-MM-dd")
    Student dtoToEntity(StudentDTO dto);
    @Mapping(target = "dob", source = "entity.dateOfBirth", dateFormat = "yyyy-MM-dd")
    StudentDTO entityToDTO(Student entity);
    StudentDTO hashMaptoDTO(LinkedHashMap<String, String> hashMap);
}
