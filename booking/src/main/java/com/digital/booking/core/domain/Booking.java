package com.digital.booking.core.domain;


import com.digital.booking.adapter.output.jpa.entities.BookingJpaEntity;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.sql.Time;
import java.time.LocalDate;

@Value
@Builder
public class Booking {

    @With
    Long id;

    Time checkInTime;

    LocalDate checkIn;

    LocalDate checkOut;

    User user;

    Rental rental;

    Boolean status;

    LocalDate createdAt;

    LocalDate updatedAt;


}
