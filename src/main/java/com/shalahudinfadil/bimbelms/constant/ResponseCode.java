package com.shalahudinfadil.bimbelms.constant;

public enum ResponseCode {
    SUCCESS("BMS-00-01", "Action Performed Successfully"),
    SAVED("BMS-00-02", "Item Saved"),
    NOT_FOUND("BMS-01-01", "Item Not Found"),
    GENERAL_ERROR("BMS-99-99", "Internal Server Error");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}