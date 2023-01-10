package com.digital.booking.core.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.Instant;

@Builder
@Value
public class Category {

    @With
    Long id;
    String name;
    String description;
    String image;
    Boolean status;
    Instant createdAt;
    Long rentalsQuantity;
}
