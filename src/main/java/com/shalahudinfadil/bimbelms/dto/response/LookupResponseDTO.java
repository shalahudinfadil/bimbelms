package com.shalahudinfadil.bimbelms.dto.response;

public record LookupResponseDTO(
        String id,
        String group,
        String value,
        String dateType,
        Integer order
) {
}
