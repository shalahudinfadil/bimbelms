package com.shalahudinfadil.bimbelms.dto.response;

import java.util.Set;

public record LevelResponseDTO(
        String id,
        String name,
        Integer order,
        Set<LevelTierResponseDTO> tiers
) {
}
