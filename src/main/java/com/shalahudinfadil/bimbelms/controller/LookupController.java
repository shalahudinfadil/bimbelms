package com.shalahudinfadil.bimbelms.controller;

import com.shalahudinfadil.bimbelms.constant.ResponseCodeEnum;
import com.shalahudinfadil.bimbelms.dto.request.create.LookupCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LookupUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.GenericResponse;
import com.shalahudinfadil.bimbelms.dto.response.LookupResponseDTO;
import com.shalahudinfadil.bimbelms.service.LookupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/lookup")
public class LookupController {
    private final Logger logger = Logger.getLogger("LookupCOntroller");
    @Autowired
    LookupService lookupService;
    
    @GetMapping("/list")
    public ResponseEntity<GenericResponse> getAll() {
        ArrayList<LookupResponseDTO> dtos = (ArrayList<LookupResponseDTO>) lookupService.getAll();
        
        return new ResponseEntity<>(new GenericResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), dtos), HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<GenericResponse> getById(@RequestParam @NotNull @NotBlank String id) {
        LookupResponseDTO dto = lookupService.getById(id);

        if (dto == null) {
            return new ResponseEntity<>(new GenericResponse(
                    ResponseCodeEnum.NOT_FOUND.getCode(), ResponseCodeEnum.NOT_FOUND.getMessage(), null), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(new GenericResponse(
                ResponseCodeEnum.SUCCESS.getCode(), null, dto), HttpStatus.OK
        );

    }

    @PostMapping("/group")
    ResponseEntity<GenericResponse> getByIGroup(@RequestParam @NotNull @NotBlank String group) {
        List<LookupResponseDTO> dto = lookupService.getByGroup((String) group);

        return new ResponseEntity<>(new GenericResponse(
                ResponseCodeEnum.SUCCESS.getCode(), null, dto), HttpStatus.OK
        );

    }
    
    @PostMapping("/save")
    public ResponseEntity<GenericResponse> save(@RequestBody @Valid LookupCreateDTO request) {
        try {
            logger.info(getClass().getName() + " | /api/v1/lookup/save => Incoming request : "+request.toString());
            LookupResponseDTO dto = lookupService.save(request);

            return new ResponseEntity<>(
                    new GenericResponse(
                            ResponseCodeEnum.SAVED.getCode(),
                            "Lookup Saved",
                            dto
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
    public ResponseEntity<GenericResponse> update(@RequestBody @Valid LookupUpdateDTO request) {

        try {

            LookupResponseDTO dto = lookupService.update(request);

            if (dto == null) {
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
                            ResponseCodeEnum.SAVED.getMessage(),
                            dto
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
