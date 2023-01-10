package com.digital.booking.adapter.output.jpa.entities;


import com.digital.booking.core.domain.Booking;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class BookingJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in_time")
    private Time checkInTime;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = false)
    private UserJpaEntity user;

    @ManyToOne
    @JsonManagedReference
    private RentalJpaEntity rental;

    private Boolean status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;


    public static Booking toDomain(BookingJpaEntity booking) {
        return Booking.builder().id(booking.getId())
                .checkInTime(booking.getCheckInTime())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .user(UserJpaEntity.toDomain(booking.getUser()))
                .rental(RentalJpaEntity.toDomain(booking.getRental()))
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .build();
    }
    public static Booking toDomainNoRental(BookingJpaEntity booking) {
        return Booking.builder().id(booking.getId())
                .checkInTime(booking.getCheckInTime())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .user(UserJpaEntity.toDomain(booking.getUser()))
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .build();
    }

    public static BookingJpaEntity fromDomain(Booking booking) {
        return BookingJpaEntity.builder().id(booking.getId())
                .checkInTime(booking.getCheckInTime())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .user(UserJpaEntity.fromDomain(booking.getUser()))
                .rental(RentalJpaEntity.fromDomain(booking.getRental()))
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getCreatedAt())
                .build();
    }

}
