package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class RentalJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne()
    @JsonIgnore
    private CategoryJpaEntity category;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityJpaEntity city;

    private Integer distance;

    @OneToMany(mappedBy = "rental", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageJpaEntity> imageList;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "rental_characteristic", joinColumns = @JoinColumn(name = "rental_id", foreignKey = @ForeignKey(name = "fk_rental_id")), inverseJoinColumns = @JoinColumn(name = "characteristic_id", foreignKey = @ForeignKey(name = "fk_characteristic_id")))
    @JsonManagedReference
    private Set<CharacteristicJpaEntity> characteristics = new HashSet<>();

    @OneToMany(mappedBy = "rental", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BookingJpaEntity> bookingSet;


    public static RentalJpaEntity fromDomain(Rental rental) {
        return RentalJpaEntity.builder()
                .id(rental.getId())
                .name(rental.getName())
                .description(rental.getDescription())
                .category(CategoryJpaEntity.fromDomain(rental.getCategory()))
                .city(CityJpaEntity.fromDomain(rental.getCity()))
                .imageList(rental.getImageList().stream().map(ImageJpaEntity::fromDomain).collect(Collectors.toList()))
                .distance(rental.getDistance())
                .characteristics(rental.getCharacteristics().stream().map(CharacteristicJpaEntity::fromDomain).collect(Collectors.toSet()))
                .bookingSet(rental.getBookings() != null ? rental.getBookings().stream().map(BookingJpaEntity::fromDomain).collect(Collectors.toSet()) : Set.of())
                .build();
    }

    public static Rental toDomain(RentalJpaEntity rental) {
        return Rental.builder()
                .id(rental.getId())
                .name(rental.getName())
                .description(rental.getDescription())
                .category(CategoryJpaEntity.toDomain(rental.getCategory()))
                .city(CityJpaEntity.toDomain(rental.getCity()))
                .distance(rental.getDistance())
                .imageList(rental.getImageList().stream().map(ImageJpaEntity::toDomain).collect(Collectors.toList()))
                .characteristics(rental.getCharacteristics().stream().map(CharacteristicJpaEntity::toDomain).collect(Collectors.toSet()))
                .bookings(rental.getBookingSet().stream().map(BookingJpaEntity::toDomainNoRental).collect(Collectors.toSet()))
                .build();
    }
}
