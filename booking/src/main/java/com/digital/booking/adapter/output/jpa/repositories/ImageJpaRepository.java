package com.digital.booking.adapter.output.jpa.repositories;

import com.digital.booking.adapter.output.jpa.entities.ImageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpaRepository extends JpaRepository<ImageJpaEntity, Long> {

}
