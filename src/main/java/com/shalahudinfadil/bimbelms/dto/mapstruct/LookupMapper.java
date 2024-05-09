package com.shalahudinfadil.bimbelms.dto.mapstruct;

import com.shalahudinfadil.bimbelms.dto.request.create.LookupCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LookupUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LookupResponseDTO;
import com.shalahudinfadil.bimbelms.entity.Lookup;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LookupMapper {
    Lookup requestDtoToEntity(LookupCreateDTO dto);
    Lookup requestDtoToEntity(LookupUpdateDTO dto);
    LookupResponseDTO entityToResponseDto(Lookup entity);
    List<LookupResponseDTO> entityListToResponseDtoList(List<Lookup> entityList);

}
