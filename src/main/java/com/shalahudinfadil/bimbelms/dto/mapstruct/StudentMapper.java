package com.shalahudinfadil.bimbelms.dto.mapstruct;

import com.shalahudinfadil.bimbelms.commons.ReferenceMapper;
import com.shalahudinfadil.bimbelms.dto.request.create.StudentCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.StudentUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.StudentResponseDTO;
import com.shalahudinfadil.bimbelms.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface StudentMapper {

    @Mapping(target = "dateOfBirth", source = "dto.dob")
    Student requestDtoToEntity(StudentCreateDTO dto);
    @Mapping(target = "dateOfBirth", source = "dto.dob")
    Student requestDtoToEntity(StudentUpdateDTO dto);
    @Mapping(target = "dob", source = "entity.dateOfBirth")
    StudentResponseDTO entityToResponseDto(Student entity);
    List<StudentResponseDTO> entityListToResponseDtoList(List<Student> entityList);

}
