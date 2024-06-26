package com.shalahudinfadil.bimbelms.controller;

import com.shalahudinfadil.bimbelms.constant.ResponseCodeEnum;
import com.shalahudinfadil.bimbelms.dto.request.create.StudentCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.StudentUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.GenericResponse;
import com.shalahudinfadil.bimbelms.dto.response.StudentResponseDTO;
import com.shalahudinfadil.bimbelms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/list")
    ResponseEntity<GenericResponse> getAll() {
        return new ResponseEntity<>(new GenericResponse(ResponseCodeEnum.SUCCESS.getCode(), null, studentService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/search")
    ResponseEntity<GenericResponse> getById(@RequestBody Map<String, Object> request) {
        StudentResponseDTO student = studentService.getById((String) request.get("id"));

        if (student == null) {
            return new ResponseEntity<>(new GenericResponse(
                    ResponseCodeEnum.NOT_FOUND.getCode(), ResponseCodeEnum.NOT_FOUND.getMessage(), null), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(new GenericResponse(
            ResponseCodeEnum.SUCCESS.getCode(), null, student), HttpStatus.OK
        );

    }

    @PostMapping("/save")
    ResponseEntity<GenericResponse> save(@RequestBody @Valid StudentCreateDTO request) {
        try {
            StudentResponseDTO studentDTO = studentService.save(request);

            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.SAVED.getCode(),
                            "Student saved",
                            studentDTO
                    ),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.GENERAL_ERROR.getCode(),
                            ResponseCodeEnum.GENERAL_ERROR.getMessage(),
                            e
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> update(@Valid @RequestBody StudentUpdateDTO request) {

        try {

            StudentResponseDTO studentDTO = studentService.update(request);

            if (studentDTO == null) {
                return new ResponseEntity<>(
                        new GenericResponse(
                                ResponseCodeEnum.NOT_FOUND.getCode(),
                                ResponseCodeEnum.NOT_FOUND.getMessage(),
                                null
                        ),
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.SAVED.getCode(),
                            "Item Updated",
                            studentDTO
                    ),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.GENERAL_ERROR.getCode(),
                            ResponseCodeEnum.GENERAL_ERROR.getMessage(),
                            e.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse> delete(@RequestBody Map<String, String> request) {
        try {
            studentService.softDelete(request.get("id"));

            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.SUCCESS.getCode(),
                            ResponseCodeEnum.SUCCESS.getMessage(),
                            null
                    ),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.GENERAL_ERROR.getCode(),
                            ResponseCodeEnum.GENERAL_ERROR.getMessage(),
                            e.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
