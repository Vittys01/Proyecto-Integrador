package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.Rental;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RentalRestModel implements Serializable {

    private Long id;
    private String name;
    private String description;
    private CategoryRentalRestModel category;
    private CityRestModel city;
    private Integer distance;
    private List<ImageRestModel> imageList;
    private Set<CharacteristicResponseModel> characteristics;
    private Set<BookingRestModel> bookingSet;

    public static RentalRestModel fromDomain(Rental product) {
        return RentalRestModel.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(CategoryRentalRestModel.fromDomain(product.getCategory()))
                .city(CityRestModel.fromDomain(product.getCity()))
                .distance(product.getDistance())
                .imageList(product.getImageList().stream().map(ImageRestModel::fromDomain).collect(Collectors.toList()))
                .characteristics(product.getCharacteristics().stream().map(CharacteristicResponseModel::fromDomain).collect(Collectors.toSet()))
                .bookingSet(product.getBookings().stream().map(BookingRestModel::fromDomainNoRental).collect(Collectors.toSet()))
                .build();
    }
}
