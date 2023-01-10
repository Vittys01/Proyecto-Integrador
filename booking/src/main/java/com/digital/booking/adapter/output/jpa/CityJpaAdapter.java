package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.CityJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.CityJpaRepository;
import com.digital.booking.core.domain.City;
import com.digital.booking.core.domain.Province;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.output.CityRepository;
import com.digital.booking.core.port.output.ProvinceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CityJpaAdapter implements CityRepository {

    private final CityJpaRepository cityJpaRepository;

    private final ProvinceRepository provinceRepository;

    public CityJpaAdapter(CityJpaRepository cityJpaRepository, ProvinceJpaAdapter provinceJpaAdapter) {
        this.cityJpaRepository = cityJpaRepository;
        this.provinceRepository = provinceJpaAdapter;
    }

    @Override
    public City save(City city) {
        Optional<Province> province = provinceRepository.getEntityById(city.getProvince().getId());

        if (province.isEmpty()){
            throw new BusinessException(ErrorCode.MISSING_PROVINCE_ID);
        }

        City cityCreate = City.builder()
                .name(city.getName())
                .province(province.get())
                .build();

        return CityJpaEntity.toDomain(cityJpaRepository.save(CityJpaEntity.fromDomain(cityCreate)));
    }

    @Override
    public Page<City> getPaginated(Integer page, Integer size) {
        return cityJpaRepository.findAll(PageRequest.of(page, size)).map(CityJpaEntity::toDomain);
    }

    @Override
    public City patch(City city) {
        Optional<Province> province = provinceRepository.getEntityById(city.getProvince().getId());
        if (province.isEmpty()){
            throw new BusinessException(ErrorCode.MISSING_PROVINCE_ID);
        }

        City cityPatch = City.builder()
                .id(city.getId())
                .name(city.getName())
                .province(province.get())
                .build();


        return CityJpaEntity.toDomain(cityJpaRepository.save(CityJpaEntity.fromDomain(cityPatch)));
    }

    @Override
    public void deleteById(Long id) {
        cityJpaRepository.deleteById(id);
    }

    @Override
    public Optional<City> getEntityById(Long id) {
        return cityJpaRepository.findById(id).map(CityJpaEntity::toDomain);
    }
}
