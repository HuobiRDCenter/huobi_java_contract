package com.huobi.api.enums;

public enum OpEnum {

    SUB("sub"),
    UNSUB("unsub");

    private String value;

    OpEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
