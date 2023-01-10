package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.input.controller.models.input.RentalRequestFilter;
import com.digital.booking.adapter.output.jpa.entities.RentalJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.RentalJpaRepository;
import com.digital.booking.adapter.output.jpa.repositories.specification.RentalJpaSpecification;
import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.port.output.RentalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RentalJpaAdapter implements RentalRepository {

    private final RentalJpaRepository rentalJpaRepository;
    private final RentalJpaSpecification rentalJpaSpecification;



    public RentalJpaAdapter(RentalJpaRepository rentalJpaRepository, RentalJpaSpecification rentalJpaSpecification) {
        this.rentalJpaRepository = rentalJpaRepository;
        this.rentalJpaSpecification = rentalJpaSpecification;
    }


    @Override
    public Page<Rental> findByFilters(String checkIn, String checkOut, Long city, Long category, Integer page, Integer size, String order, String currentUser) {

        RentalRequestFilter.RentalRequestFilterBuilder builder = RentalRequestFilter.builder()
                .checkIn(checkIn)
                .checkOut(checkOut)
                .city(city)
                .category(category)
                .order(order)
                .currentUser(currentUser);
        return rentalJpaRepository.findAll(rentalJpaSpecification.readByFilters(builder.build()),PageRequest.of(page,size))
                .map(RentalJpaEntity::toDomain);
    }
    @Override
    public Page<Rental> getCategoriesByName(String name, Integer page, Integer size) {
        return rentalJpaRepository.findByNameContains(name, PageRequest.of(page, size)).map(RentalJpaEntity::toDomain);
    }

    @Override
    public Rental save(Rental Product) {
        return RentalJpaEntity.toDomain(rentalJpaRepository.save(RentalJpaEntity.fromDomain(Product)));
    }

    @Override
    public Page<Rental> getPaginated(Integer page, Integer size) {
        return rentalJpaRepository.findAll(PageRequest.of(page, size)).map(RentalJpaEntity::toDomain);
    }

    @Override
    public Rental patch(Rental Product) {
        return RentalJpaEntity.toDomain(rentalJpaRepository.save(RentalJpaEntity.fromDomain(Product)));
    }

    @Override
    public void deleteById(Long id) {
        rentalJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Rental> getEntityById(Long id) {
        return rentalJpaRepository.findById(id).map(RentalJpaEntity::toDomain);
    }
}
