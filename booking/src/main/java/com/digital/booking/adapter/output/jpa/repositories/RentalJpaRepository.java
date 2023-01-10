package com.digital.booking.adapter.output.jpa.repositories;

import com.digital.booking.adapter.output.jpa.entities.RentalJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.specification.RentalJpaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalJpaRepository extends JpaRepository<RentalJpaEntity, Long>, JpaSpecificationExecutor<RentalJpaEntity> {
    Page<RentalJpaEntity> findByNameContains(String name, Pageable pageable);

    Page<RentalJpaEntity> findAll(Specification<RentalJpaEntity> rentalJpaEntitySpecification, Pageable pageable);
}
