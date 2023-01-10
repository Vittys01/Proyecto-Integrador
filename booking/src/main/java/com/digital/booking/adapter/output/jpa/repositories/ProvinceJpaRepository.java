package com.digital.booking.adapter.output.jpa.repositories;

import com.digital.booking.adapter.output.jpa.entities.ProvinceJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceJpaRepository extends JpaRepository<ProvinceJpaEntity, Long> {
    Page<ProvinceJpaEntity> findByNameContains(String name, Pageable pageable);
}
