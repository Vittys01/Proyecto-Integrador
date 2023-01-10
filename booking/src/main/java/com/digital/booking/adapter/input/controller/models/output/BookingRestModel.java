package com.digital.booking.adapter.input.controller.models.output;


import com.digital.booking.core.domain.Booking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookingRestModel implements Serializable {

    private Long id;

    private Time checkInTime;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Boolean status;

    private Long userId;

    private RentalRestModel rental;

    private LocalDate createdAt;

    private LocalDate updatedAt;


    public static BookingRestModel fromDomain(Booking booking){
        return BookingRestModel.builder()
                .id(booking.getId())
                .checkInTime(booking.getCheckInTime())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .status(booking.getStatus())
                .userId(booking.getUser().getId())
                .rental(RentalRestModel.fromDomain(booking.getRental()))
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getCreatedAt())
                .build();
    }
    public static BookingRestModel fromDomainNoRental(Booking booking){
        return BookingRestModel.builder()
                .id(booking.getId())
                .checkInTime(booking.getCheckInTime())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .status(booking.getStatus())
                .userId(booking.getUser().getId())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getCreatedAt())
                .build();
    }
}
