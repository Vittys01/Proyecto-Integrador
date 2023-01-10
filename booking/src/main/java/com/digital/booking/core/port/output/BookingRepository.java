package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Booking;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookingRepository extends CommonDaoRepository<Booking> {

    Optional<Booking> getBookingActiveByDateRange(Booking booking);

    Page<Booking> getBookingByUserId(Long userId, Integer page, Integer size);
}
