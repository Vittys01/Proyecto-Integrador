package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Booking;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

public interface BookingCreationCommand {
    Booking execute(Data data);

    @lombok.Data
    @Builder
    class Data{
        private Time checkinTime;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Long userId;
        private Long rentalId;
    }
}
