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
public class CountryRestModel implements Serializable{
    private Long id;
    private String name;

    public static CountryRestModel fromDomain(Country Country){
        return CountryRestModel.builder()
                .id(Country.getId())
                .name(Country.getName())
                .build();
    }
}
