package com.shalahudinfadil.bimbelms.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LookupUpdateDTO(
        String id,
        @NotNull(message = "'group' is required")
        @NotBlank(message = "'group' cannot be blank")
        String group,
        @NotNull(message = "'value' is required")
        @NotBlank(message = "'value' cannot be blank")
        String value,
        @NotNull(message = "'data_type' is required")
        @NotBlank(message = "'data_type' cannot be blank")
        String dataType,
        @NotNull(message = "'order' is required")
        Integer order
) {
}
