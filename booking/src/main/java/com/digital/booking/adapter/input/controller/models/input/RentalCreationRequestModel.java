package com.digital.booking.adapter.input.controller.models.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RentalCreationRequestModel implements Serializable {

    private String name;
    private String description;
    private Long categoryId;
    private Long cityId;
    private Integer distance;
    private Set<String> images;
    private Set<CharacteristicPatchRequestModel> characteristics;
}
