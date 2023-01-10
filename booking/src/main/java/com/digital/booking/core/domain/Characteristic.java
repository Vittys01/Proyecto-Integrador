package com.digital.booking.core.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.sql.Timestamp;

@Builder
@Value
public class Characteristic {

    @With
    Long id;
    String title;
    String icon;
    Timestamp createTimestamp;
    Boolean softDelete;
}
