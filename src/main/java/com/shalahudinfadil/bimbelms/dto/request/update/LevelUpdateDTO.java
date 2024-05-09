package com.shalahudinfadil.bimbelms.dto.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record LevelUpdateDTO(
        String id,
        @NotNull(message = "'name' is required")
        @NotBlank(message = "'name' must be filled")
        String name,
        @NotNull(message = "'order' is required")
        Integer order,
        @Valid
        Set<LevelTierUpdateDTO> tiers
) {
}
