package com.shalahudinfadil.bimbelms.service;

import com.shalahudinfadil.bimbelms.dto.request.create.LookupCreateDTO;
import com.shalahudinfadil.bimbelms.dto.request.update.LookupUpdateDTO;
import com.shalahudinfadil.bimbelms.dto.response.LookupResponseDTO;

import java.util.List;

public interface LookupService {
    List<LookupResponseDTO> getAll();
    LookupResponseDTO getById(String id);
    List<LookupResponseDTO> getByGroup(String group);
    LookupResponseDTO save(LookupCreateDTO dto);
    LookupResponseDTO update(LookupUpdateDTO requestDTO);
}
