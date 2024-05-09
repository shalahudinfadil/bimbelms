package com.shalahudinfadil.bimbelms.service.impl;

import com.shalahudinfadil.bimbelms.dto.mapstruct.LevelMapper;
import com.shalahudinfadil.bimbelms.dto.mapstruct.LevelTierMapper;
import com.shalahudinfadil.bimbelms.dto.request.create.LevelCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.create.LevelTierCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LevelUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LevelResponseDTO;
import com.shalahudinfadil.bimbelms.entity.Level;
import com.shalahudinfadil.bimbelms.entity.LevelTier;
import com.shalahudinfadil.bimbelms.repository.LevelRepo;
import com.shalahudinfadil.bimbelms.repository.LevelTierRepo;
import com.shalahudinfadil.bimbelms.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class LevelServiceImpl implements LevelService {
    private final Logger logger = Logger.getLogger("LevelServiceImpl");
    @Autowired
    LevelRepo levelRepo;
    @Autowired
    LevelTierRepo levelTierRepo;
    @Autowired
    LevelMapper mapper;
    @Override
    public List<LevelResponseDTO> getAll() {
        List<Level> entityList = levelRepo.findAll();

        return mapper.entityListToResponseDtoList(entityList);
    }

    @Override
    public LevelResponseDTO getById(String id) {
        Optional<Level> entityOptional = levelRepo.findById(UUID.fromString(id));

        return entityOptional.map(mapper::entityToResponseDto).orElse(null);

    }

    @Override
    public LevelResponseDTO save(LevelCreateDTO request) {
        Level newEntity = mapper.requestDtoToEntity(request);
        logger.info(getClass().getName() + " => " + newEntity.toString());
        Level savedEntity = levelRepo.save(newEntity);
        logger.info(getClass().getName() + " => " + savedEntity.toString());

        for (LevelTier levelTierEntity : newEntity.getTiers()) {
            levelTierEntity.setLevel(newEntity);
            logger.info(getClass().getName() + " => " + levelTierEntity.toString());
            levelTierRepo.save(levelTierEntity);
        }

        LevelResponseDTO dto = mapper.entityToResponseDto(levelRepo.findById(savedEntity.getId()).get());

        return dto;
    }

    @Override
    public LevelResponseDTO update(LevelUpdateDTO request) {
        UUID id = UUID.fromString(request.id());
        Optional<Level> existingEntity = levelRepo.findById(id);

        if (existingEntity.isEmpty()) {
            return null;
        }

        Level updateEntity = mapper.requestDtoToEntity(request);
        updateEntity.setId(existingEntity.get().getId());

        for (LevelTier levelTierEntity : updateEntity.getTiers()) {
            levelTierEntity.setLevel(updateEntity);
            levelTierRepo.save(levelTierEntity);
        }

        Level updatedEntity = levelRepo.save(updateEntity);
        LevelResponseDTO dto = mapper.entityToResponseDto(updatedEntity);

        return dto;

    }
}
