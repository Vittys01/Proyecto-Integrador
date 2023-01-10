package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.ProvinceJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.ProvinceJpaRepository;

import com.digital.booking.core.domain.Province;
import com.digital.booking.core.port.output.ProvinceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProvinceJpaAdapter implements ProvinceRepository {

    private final ProvinceJpaRepository provinceJpaRepository;

    public ProvinceJpaAdapter(ProvinceJpaRepository provinceJpaRepository) {
        this.provinceJpaRepository = provinceJpaRepository;
    }

    @Override
    public Province save(Province province) {
        return ProvinceJpaEntity.toDomain(provinceJpaRepository.save(ProvinceJpaEntity.fromDomain(province)));
    }

    @Override
    public Page<Province> getPaginated(Integer page, Integer size) {
        return provinceJpaRepository.findAll(PageRequest.of(page, size)).map(ProvinceJpaEntity::toDomain);
    }

    @Override
    public Province patch(Province Province) {
        return ProvinceJpaEntity.toDomain(provinceJpaRepository.save(ProvinceJpaEntity.fromDomain(Province)));
    }

    @Override
    public void deleteById(Long id) {
        provinceJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Province> getEntityById(Long id) {
        return provinceJpaRepository.findById(id).map(ProvinceJpaEntity::toDomain);
    }
}
