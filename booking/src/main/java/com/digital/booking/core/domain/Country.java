package com.digital.booking.core.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Builder
@Value
public class Country {

    @With
    Long id;
    String name;
}
