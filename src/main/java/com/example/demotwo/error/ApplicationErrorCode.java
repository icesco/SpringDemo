package com.example.demotwo.error;

public enum ApplicationErrorCode {

    INTERNAL            ("E001"),
    USER_NOT_FOUND      ("E002"),

    USER_ALREADY_EXISTS ("E003"),
    VALIDATION          ("E004");

    private String code;
    ApplicationErrorCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
