package com.digital.booking.adapter.output.jpa.repositories;

import com.digital.booking.adapter.output.jpa.entities.CityJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityJpaRepository extends JpaRepository<CityJpaEntity, Long> {
    Page<CityJpaEntity> findByNameContains(String name, Pageable pageable);
}
