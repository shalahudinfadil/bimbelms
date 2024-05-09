package com.shalahudinfadil.bimbelms.constant;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {
    SUCCESS("BMS-00-01", "Action Performed Successfully"),
    SAVED("BMS-00-02", "Item Saved"),
    NOT_FOUND("BMS-01-01", "Item Not Found"),
    VALIDATION_FAILED("BMS-03-01", "Validation Failed"),
    ILLEGAL_ARGUMENT("BMS-03-02", "Illegal/Malformed Argument(s)"),
    GENERAL_ERROR("BMS-99-99", "Internal Server Error");

    private final String code;
    private final String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}