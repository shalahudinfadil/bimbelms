package com.shalahudinfadil.bimbelms.dto.mapstruct;

import com.shalahudinfadil.bimbelms.dto.request.create.LevelTierCreateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LevelTierResponseDTO;
import com.shalahudinfadil.bimbelms.entity.LevelTier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LevelTierMapper {
    @Mapping(source = "name", target = "tier")
    LevelTierResponseDTO entityToResponseDto(LevelTier entity);

    @Mapping(source = "tier", target = "name")
    LevelTier requestDtoToEntity(LevelTierCreateDTO dto);
}
