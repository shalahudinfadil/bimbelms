package com.shalahudinfadil.bimbelms.service;

import com.shalahudinfadil.bimbelms.dto.request.create.LevelCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LevelUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LevelResponseDTO;

import java.util.List;

public interface LevelService {
    List<LevelResponseDTO> getAll();
    LevelResponseDTO getById(String id);
    LevelResponseDTO save(LevelCreateDTO request);
    LevelResponseDTO update(LevelUpdateDTO request);
}
