package com.digital.booking.core.domain.enums;

import java.util.Arrays;

public enum Authority {

    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    public static Authority getFromValue(String value){
        return Arrays.stream(Authority.values())
                .filter(authority -> authority.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    Authority(String value){this.value = value;}

    public String getValue(){return this.value;}
}
