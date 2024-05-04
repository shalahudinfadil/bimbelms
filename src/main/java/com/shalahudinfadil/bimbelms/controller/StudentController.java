package com.shalahudinfadil.bimbelms.controller;

import com.shalahudinfadil.bimbelms.constant.ResponseCode;
import com.shalahudinfadil.bimbelms.dto.StudentDTO;
import com.shalahudinfadil.bimbelms.response.GenericResponse;
import com.shalahudinfadil.bimbelms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/list")
    ResponseEntity<GenericResponse> getAll() {
        return new ResponseEntity<>(new GenericResponse(ResponseCode.SUCCESS.getCode(), null, studentService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/search")
    ResponseEntity<GenericResponse> getById(@RequestBody Map<String, Object> request) {
        StudentDTO student = studentService.getById((String) request.get("id"));

        if (student == null) {
            return new ResponseEntity<>(new GenericResponse(
                    ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), null), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(new GenericResponse(
            ResponseCode.SUCCESS.getCode(), null, student), HttpStatus.OK
        );

    }

    @PostMapping("/save")
    ResponseEntity<GenericResponse> save(@RequestBody StudentDTO request) {
        try {
            StudentDTO studentDTO = studentService.save(request);

            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCode.SAVED.getCode(),
                            "Student saved",
                            studentDTO
                    ),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCode.GENERAL_ERROR.getCode(),
                            ResponseCode.GENERAL_ERROR.getMessage(),
                            e
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> update(@RequestBody Map<String, Object> request) {
        String id = String.valueOf(request.get("id"));
        LinkedHashMap<String, String> requestDTO = (LinkedHashMap<String, String>) request.get("data");

        try {

            StudentDTO studentDTO = studentService.update(id, requestDTO);

            if (studentDTO == null) {
                return new ResponseEntity<>(
                        new GenericResponse(
                                ResponseCode.NOT_FOUND.getCode(),
                                ResponseCode.NOT_FOUND.getMessage(),
                                null
                        ),
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCode.SAVED.getCode(),
                            ResponseCode.SAVED.getMessage(),
                            studentDTO
                    ),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCode.GENERAL_ERROR.getCode(),
                            ResponseCode.GENERAL_ERROR.getMessage(),
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
                            ResponseCode.SUCCESS.getCode(),
                            ResponseCode.SUCCESS.getMessage(),
                            null
                    ),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCode.GENERAL_ERROR.getCode(),
                            ResponseCode.GENERAL_ERROR.getMessage(),
                            e.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
