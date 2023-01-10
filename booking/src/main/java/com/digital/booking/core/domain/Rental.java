package com.digital.booking.core.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.Set;

@Value
@Builder
public class Rental {

    @With
    Long id;
    String name;
    String description;
    Category category;
    City city;
    Integer distance;
    @With
    List<Image> imageList;
    Set<Characteristic> characteristics;
    Set<Booking> bookings;
}
