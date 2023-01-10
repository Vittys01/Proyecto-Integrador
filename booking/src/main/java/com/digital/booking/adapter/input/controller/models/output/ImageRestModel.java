package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImageRestModel implements Serializable {

    private Long id;
    private String url;
    private Long rentalId;

    public static ImageRestModel fromDomain(Image image) {
        ImageRestModelBuilder builder = ImageRestModel.builder()
                .id(image.getId())
                .url(image.getUrl());
        if (image.getRental() != null) builder.rentalId(image.getRental().getId());
        return builder.build();
    }
}
