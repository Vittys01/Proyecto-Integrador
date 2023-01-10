package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.CountryJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.CountryJpaRepository;
import com.digital.booking.core.domain.Country;
import com.digital.booking.core.port.output.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryJpaAdapter implements CountryRepository {

    private final CountryJpaRepository countryJpaRepository;

    public CountryJpaAdapter(CountryJpaRepository CountryJpaRepository) {
        this.countryJpaRepository = CountryJpaRepository;
    }

    @Override
    public Page<Country> getCategoriesByName(String name, Integer page, Integer size) {
        return countryJpaRepository.findByNameContains(name, PageRequest.of(page, size)).map(CountryJpaEntity::toDomain);
    }

    @Override
    public Country save(Country country) {
        return CountryJpaEntity.toDomain(countryJpaRepository.save(CountryJpaEntity.fromDomain(country)));
    }

    @Override
    public Page<Country> getPaginated(Integer page, Integer size) {
        return countryJpaRepository.findAll(PageRequest.of(page, size)).map(CountryJpaEntity::toDomain);
    }

    @Override
    public Country patch(Country country) {
        return CountryJpaEntity.toDomain(countryJpaRepository.save(CountryJpaEntity.fromDomain(country)));
    }

    @Override
    public void deleteById(Long id) {
        countryJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Country> getEntityById(Long id) {
        return countryJpaRepository.findById(id).map(CountryJpaEntity::toDomain);
    }
}
