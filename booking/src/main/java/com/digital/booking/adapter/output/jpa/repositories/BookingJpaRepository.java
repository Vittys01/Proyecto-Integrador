package com.digital.booking.adapter.output.jpa.repositories;

import com.digital.booking.adapter.output.jpa.entities.BookingJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingJpaEntity, Long>, JpaSpecificationExecutor<BookingJpaEntity> {


    Optional<BookingJpaEntity> findByRentalIdAndStatusAndCheckInBetweenAndCheckOut(Long rentalId,
                                                                                   Boolean status,
                                                                                   LocalDate checkIn,
                                                                                   LocalDate checkInOut,
                                                                                   LocalDate checkOut);

    Page<BookingJpaEntity> findByUserId(Long userId, Pageable pageable);

}
