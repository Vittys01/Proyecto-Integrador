package com.digital.booking.application.security.enums;

import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;

import java.util.Arrays;

public enum EntityAuthorizationRole {

    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    EntityAuthorizationRole(String value){this.value = value;}

    public static EntityAuthorizationRole getFromValue(String value){
        return Arrays.stream(EntityAuthorizationRole.values())
                .filter(role -> role.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(()-> {throw new BusinessException(ErrorCode.INVALID_USER_AUTHORITY);
                });
    }

    public String getValue(){return  this.value;}
}
