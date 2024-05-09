package com.shalahudinfadil.bimbelms.dto.mapstruct;

import com.shalahudinfadil.bimbelms.dto.request.create.LevelCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LevelUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LevelResponseDTO;
import com.shalahudinfadil.bimbelms.entity.Level;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LevelTierMapper.class})
public interface LevelMapper {
    LevelResponseDTO entityToResponseDto(Level entity);
    List<LevelResponseDTO> entityListToResponseDtoList(List<Level> entityList);
    Level requestDtoToEntity(LevelCreateDTO dto);
    Level requestDtoToEntity(LevelUpdateDTO dto);
}
