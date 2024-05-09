package com.shalahudinfadil.bimbelms.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LevelTierUpdateDTO (
        String id,
        @NotNull(message = "'tier' is required")
        @NotBlank(message = "'tier' must be filled")
        String tier,
        @NotNull(message = "'order' is required")
        Integer order
) {
}
