package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Booking;
import org.springframework.data.domain.Page;

public interface BookingGettingQuery {
    Page<Booking> getBookingsByUserId(Long userId, Integer page, Integer size);
}
