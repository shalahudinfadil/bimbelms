package com.shalahudinfadil.bimbelms.service.impl;

import com.shalahudinfadil.bimbelms.dto.mapstruct.LookupMapper;
import com.shalahudinfadil.bimbelms.dto.request.create.LookupCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LookupUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LookupResponseDTO;
import com.shalahudinfadil.bimbelms.entity.Lookup;
import com.shalahudinfadil.bimbelms.repository.LookupRepo;
import com.shalahudinfadil.bimbelms.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional
public class LookupServiceImpl implements LookupService {
    private Logger logger = Logger.getLogger("LookupServiceImpl");
    @Autowired
    LookupRepo lookupRepo;
    @Autowired
    LookupMapper mapper;
    @Override
    public List<LookupResponseDTO> getAll() {
        ArrayList<Lookup> entityList = (ArrayList<Lookup>) lookupRepo.findAll();
        ArrayList<LookupResponseDTO> dtoList = (ArrayList<LookupResponseDTO>) mapper.entityListToResponseDtoList(entityList);

        return dtoList;
    }

    @Override
    public LookupResponseDTO getById(String id) {
        Optional<Lookup> lookupOptional = lookupRepo.findById(UUID.fromString(id));

        return lookupOptional.map(lookup -> mapper.entityToResponseDto(lookup)).orElse(null);

    }

    @Override
    public List<LookupResponseDTO> getByGroup(String group) {
        ArrayList<Lookup> lookupByGroup = (ArrayList<Lookup>) lookupRepo.getByGroup(group, Sort.by(Sort.Direction.ASC, "order"));
        ArrayList<LookupResponseDTO> dtoByGroup = (ArrayList<LookupResponseDTO>) mapper.entityListToResponseDtoList(lookupByGroup);

        return dtoByGroup;
    }

    @Override
    public LookupResponseDTO save(LookupCreateDTO dto) {
        logger.info("/api/v1/list/save => Incoming Request : "+ dto.toString());
        Lookup entity = mapper.requestDtoToEntity(dto);
        LookupResponseDTO saveDto = mapper.entityToResponseDto(lookupRepo.save(entity));

        return saveDto;
    }

    @Override
    public LookupResponseDTO update(LookupUpdateDTO dto) {
        Optional<Lookup> lookupOptional = lookupRepo.findById(UUID.fromString(dto.id()));

        if (lookupOptional.isEmpty()) {
            return null;
        }

        Lookup entity = mapper.requestDtoToEntity(dto);
        entity.setId(lookupOptional.get().getId());

        LookupResponseDTO saveDTO = mapper.entityToResponseDto(lookupRepo.save(entity));

        return saveDTO;
    }
}
