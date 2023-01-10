package com.digital.booking.core.usecases;


import com.digital.booking.core.domain.Booking;
import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.domain.User;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.EntityNotFoundException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.BookingCreationCommand;
import com.digital.booking.core.port.output.BookingRepository;
import com.digital.booking.core.port.output.RentalRepository;
import com.digital.booking.core.port.output.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class BookingCreationUseCase  implements BookingCreationCommand {

    private final UserRepository userJpaAdapter;
    private final BookingRepository bookingJpaAdapter;

    private final RentalRepository rentalJpaAdapter;

    public BookingCreationUseCase(UserRepository userJpaAdapter, BookingRepository bookingJpaAdapter, RentalRepository rentalJpaAdapter) {
        this.userJpaAdapter = userJpaAdapter;
        this.bookingJpaAdapter = bookingJpaAdapter;
        this.rentalJpaAdapter = rentalJpaAdapter;
    }

    @Override
    public Booking execute(Data data) {
        if(data.getCheckIn().isAfter(data.getCheckOut())) throw new BusinessException(ErrorCode.BAD_ARRIVAL_DATES);
        Optional<User> user = userJpaAdapter.getEntityById(data.getUserId());
        Optional<Rental> rental = rentalJpaAdapter.getEntityById(data.getRentalId());
        if(user.isEmpty()) throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
        if(rental.isEmpty()) throw new EntityNotFoundException(ErrorCode.RENTAL_NOT_FOUND);
        Booking booking = Booking.builder()
                .checkInTime(data.getCheckinTime())
                .checkIn(data.getCheckIn())
                .checkOut(data.getCheckOut())
                .status(true)
                .user(user.get())
                .rental(rental.get())
                .createdAt(LocalDate.now())
                .updatedAt(null)
                .build();
        Optional<Booking> existenceBooking = bookingJpaAdapter.getBookingActiveByDateRange(booking);
        if( existenceBooking.isPresent() && existenceBooking.get().getStatus()){
            throw new BusinessException(ErrorCode.RENTAL_RESERVED);
        }
        return bookingJpaAdapter.save(booking);
    }
}
