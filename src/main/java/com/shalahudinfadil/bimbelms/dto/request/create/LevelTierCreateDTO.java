package com.shalahudinfadil.bimbelms.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LevelTierCreateDTO (
        @NotNull(message = "'tier' is required")
        @NotBlank(message = "'tier' must be filled")
        String tier,
        @NotNull(message = "'order' is required")
        Integer order
) {
}
