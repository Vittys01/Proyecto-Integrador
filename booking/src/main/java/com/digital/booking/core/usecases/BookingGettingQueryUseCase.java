package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Booking;
import com.digital.booking.core.port.input.BookingGettingQuery;
import com.digital.booking.core.port.output.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookingGettingQueryUseCase implements BookingGettingQuery {

    private final BookingRepository bookingJpaAdapter;

    public BookingGettingQueryUseCase(BookingRepository bookingJpaAdapter) {
        this.bookingJpaAdapter = bookingJpaAdapter;
    }

    @Override
    public Page<Booking> getBookingsByUserId(Long userId, Integer page, Integer size) {
        return bookingJpaAdapter.getBookingByUserId(userId, page, size);
    }
}
