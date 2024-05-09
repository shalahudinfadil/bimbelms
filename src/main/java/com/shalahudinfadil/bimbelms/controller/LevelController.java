package com.shalahudinfadil.bimbelms.controller;

import com.shalahudinfadil.bimbelms.constant.ResponseCodeEnum;
import com.shalahudinfadil.bimbelms.dto.request.create.LevelCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LevelUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.GenericResponse;
import com.shalahudinfadil.bimbelms.dto.response.LevelResponseDTO;
import com.shalahudinfadil.bimbelms.service.LevelService;
import com.shalahudinfadil.bimbelms.service.impl.LevelServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/md/level")
public class LevelController {
    private final Logger logger = Logger.getLogger("LevelController");
    @Autowired
    LevelService levelService;
    @GetMapping("/list")
    public ResponseEntity<GenericResponse> getAll() {
        List<LevelResponseDTO> dtoList = levelService.getAll();

        return new ResponseEntity<>(new GenericResponse(
                ResponseCodeEnum.SUCCESS.getCode(),
                ResponseCodeEnum.SUCCESS.getMessage(),
                dtoList),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse> getById(@RequestParam @NotNull @NotBlank String id) {
        LevelResponseDTO dto = levelService.getById(id);

        if (dto == null) {
            return ResponseEntity.ok(
                    new GenericResponse(
                            ResponseCodeEnum.NOT_FOUND.getCode(),
                            ResponseCodeEnum.NOT_FOUND.getMessage(),
                            null
                    )
            );
        }

        return ResponseEntity.ok(
                new GenericResponse(
                        ResponseCodeEnum.SUCCESS.getCode(),
                        ResponseCodeEnum.SUCCESS.getMessage(),
                        dto
                )
        );
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> create(@RequestBody @Valid LevelCreateDTO requestDto) {
        try {
            logger.info(getClass().getName() +" => " + requestDto.toString());
            LevelResponseDTO dto = levelService.save(requestDto);

            return ResponseEntity.ok(
                    new GenericResponse(
                            ResponseCodeEnum.SUCCESS.getCode(),
                            ResponseCodeEnum.SUCCESS.getMessage(),
                            dto
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new GenericResponse(
                            ResponseCodeEnum.GENERAL_ERROR.getCode(),
                            ResponseCodeEnum.GENERAL_ERROR.getMessage(),
                            e.getMessage()
                    )
            );
        }

    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> update(@RequestBody @Valid LevelUpdateDTO requestDto) {
        try {
            LevelResponseDTO dto = levelService.update(requestDto);

            return ResponseEntity.ok(
                    new GenericResponse(
                            ResponseCodeEnum.SUCCESS.getCode(),
                            ResponseCodeEnum.SUCCESS.getMessage(),
                            dto
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new GenericResponse(
                            ResponseCodeEnum.GENERAL_ERROR.getCode(),
                            ResponseCodeEnum.GENERAL_ERROR.getMessage(),
                            e.getMessage()
                    )
            );
        }

    }
}
