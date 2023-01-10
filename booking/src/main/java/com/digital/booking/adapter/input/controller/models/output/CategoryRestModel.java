package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.Category;
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
public class CategoryRestModel implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Boolean status;
    private String image;
    private Long quantity;

    public static CategoryRestModel fromDomain(Category category){
        return CategoryRestModel.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.getStatus())
                .image(category.getImage())
                .quantity(category.getRentalsQuantity())
                .build();
    }
}
