package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.BookingJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.BookingJpaRepository;
import com.digital.booking.core.domain.Booking;
import com.digital.booking.core.port.output.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class BookingJpaAdapter implements BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;

    public BookingJpaAdapter(BookingJpaRepository bookingJpaRepository) {
        this.bookingJpaRepository = bookingJpaRepository;
    }

    @Override
    public Booking save(Booking booking) {
        return BookingJpaEntity.toDomain(bookingJpaRepository.save(BookingJpaEntity.fromDomain(booking)));
    }

    @Override
    public Page<Booking> getPaginated(Integer page, Integer size) {
        final Pageable pageable = PageRequest.of(page, size);
        return bookingJpaRepository.findAll(pageable).map(BookingJpaEntity::toDomain);
    }

    @Override
    public Optional<Booking> getEntityById(Long id) {
        return bookingJpaRepository.findById(id).map(BookingJpaEntity::toDomain);
    }

    @Override
    public Booking patch(Booking booking) {
        return BookingJpaEntity.toDomain(bookingJpaRepository.save(BookingJpaEntity.fromDomain(booking)));
    }

    @Override
    public void deleteById(Long id) {
        bookingJpaRepository.save(BookingJpaEntity.builder().id(id).status(false).build());
    }

    @Override
    public Optional<Booking> getBookingActiveByDateRange(Booking booking) {
        Integer daysToMinus = calculateDaysToMinus(booking.getCheckIn(), booking.getCheckOut());
        return bookingJpaRepository.findByRentalIdAndStatusAndCheckInBetweenAndCheckOut(
                        booking.getRental().getId(),
                        booking.getStatus(),
                        booking.getCheckIn(),
                        booking.getCheckOut().minusDays(daysToMinus),
                        booking.getCheckOut())
                .map(BookingJpaEntity::toDomain);
    }

    private Integer calculateDaysToMinus(LocalDate checkIn, LocalDate checkOut){
        return  checkIn.getDayOfYear() % checkOut.getDayOfYear() == 0 ? 0 : 1;
    }


    @Override
    public Page<Booking> getBookingByUserId(Long userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingJpaRepository.findByUserId(userId, pageable).map(BookingJpaEntity::toDomain);
    }
}
