package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CityRestModel implements Serializable{
    private Long id;
    private String name;
    private ProvinceRestModel province;

    public static CityRestModel fromDomain(City city){
        return CityRestModel.builder()
                .id(city.getId())
                .name(city.getName())
                .province(ProvinceRestModel.fromDomain(city.getProvince()))
                .build();
    }
}
