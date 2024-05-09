package com.shalahudinfadil.bimbelms.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record StudentUpdateDTO(
        String id,
        @NotNull(message = "'name' is required")
        @NotBlank(message = "'name' cannot be blank")
        String name,
        @NotNull(message = "'dob' is required")
//        @Past(message = "'dob' must be in the past")
        String dob,
        @NotNull(message = "'gender' is required")
        @Pattern(regexp="^([MFO])$",message="invalid gender, only (M)ale/(F)emale/(O)thers are accepted")
        String gender,
        @NotNull(message = "'address' is required")
        String address,
        @NotNull(message = "'level_id' is required")
        String levelId
) {
}
