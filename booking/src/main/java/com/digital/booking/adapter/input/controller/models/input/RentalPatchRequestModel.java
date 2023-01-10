package com.digital.booking.adapter.input.controller.models.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
public class RentalPatchRequestModel implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long cityId;
    private Integer distance;

}
