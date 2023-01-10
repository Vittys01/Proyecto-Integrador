package com.digital.booking.core.domain.enums;

import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;

import java.util.Arrays;

public enum ProductType {

    DEPARTMENT("DEPARMENT", "Departamento"),
    HOUSE("HOUSE", "Casa"),
    CABINS("CABINS","Cabaña"),
    BEACHHOUSE("BEACHHOUSE", "Casas de Playa");

    private String internalName;

    private String name;

    ProductType(String internalName, String name){
        this.internalName = internalName;
        this.name = name;
    }

    public static ProductType getByName(String name){
        return Arrays.stream(ProductType.values()).filter(productType -> productType.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> {throw new BusinessException(ErrorCode.INVALID_PROPERTY_TYPE);
                });
    }

    public String getInternalName(){return this.internalName;}
}
