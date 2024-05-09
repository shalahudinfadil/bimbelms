package com.shalahudinfadil.bimbelms.handler;

import com.shalahudinfadil.bimbelms.constant.ResponseCodeEnum;
import com.shalahudinfadil.bimbelms.dto.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " => " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        GenericResponse body = new GenericResponse(
                ResponseCodeEnum.VALIDATION_FAILED.getCode(),
                ResponseCodeEnum.VALIDATION_FAILED.getMessage(),
                errors
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GenericResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        GenericResponse body = new GenericResponse(
                ResponseCodeEnum.NOT_FOUND.getCode(),
                ResponseCodeEnum.NOT_FOUND.getMessage(),
                ex.getMessage()
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        GenericResponse body = new GenericResponse(
                ResponseCodeEnum.ILLEGAL_ARGUMENT.getCode(),
                ResponseCodeEnum.ILLEGAL_ARGUMENT.getMessage(),
                ex.getMessage()
        );

        return ResponseEntity.badRequest().body(body);
    }
}
