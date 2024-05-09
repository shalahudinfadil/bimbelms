package com.shalahudinfadil.bimbelms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IdRequestDTO(
        @NotNull(message = "'id' is required")
        @NotBlank(message = "'id' must be filled")
        String id
) {
}
