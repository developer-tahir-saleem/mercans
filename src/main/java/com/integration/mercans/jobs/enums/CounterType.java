package com.integration.mercans.jobs.enums;

public enum CounterType {
//    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
    first(1), second(2), third(3), fourth(4), fifth(5), sixth(6), seventh(7), eighth(8), ninth(9), tenth(10);
    private int value;
    CounterType(int value) {
        this.value=value;
    }


    public static CounterType fromId(int id) {
        for (CounterType type : values()) {
            if (type.value == id) {
                return type;
            }
        }
        return null;
    }
}
