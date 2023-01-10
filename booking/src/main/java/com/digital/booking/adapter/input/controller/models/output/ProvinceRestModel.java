package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.Province;
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

public class ProvinceRestModel implements Serializable {
    private Long id;
    private String name;
    private CountryRestModel country;

    public static ProvinceRestModel fromDomain(Province province) {
        return ProvinceRestModel.builder()
                .id(province.getId())
                .name(province.getName())
                .country(CountryRestModel.fromDomain(province.getCountry()))
                .build();
    }
}
