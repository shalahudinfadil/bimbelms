package com.shalahudinfadil.bimbelms.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record GenericResponse (
        String errCode,
        String errMessage,
        Object data
) {
}
