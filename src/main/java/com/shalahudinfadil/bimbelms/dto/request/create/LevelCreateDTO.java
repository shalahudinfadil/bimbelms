package com.shalahudinfadil.bimbelms.dto.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record LevelCreateDTO(
        @NotNull(message = "'name' is required")
        @NotBlank(message = "'name' must be filled")
        String name,
        @NotNull(message = "'order' is required")
        Integer order,
        @Valid
        Set<LevelTierCreateDTO> tiers
) {
}
