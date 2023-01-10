package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.CharacteristicJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.CharacteristicJpaRepository;
import com.digital.booking.core.domain.Characteristic;
import com.digital.booking.core.port.output.CharacteristicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CharacteristicJpaAdapter implements CharacteristicRepository {

    private final CharacteristicJpaRepository characteristicJpaRepository;

    public CharacteristicJpaAdapter(CharacteristicJpaRepository characteristicJpaRepository) {
        this.characteristicJpaRepository = characteristicJpaRepository;
    }

    @Override
    public Characteristic save(Characteristic characteristic) {
        return CharacteristicJpaEntity.toDomain(characteristicJpaRepository.save(CharacteristicJpaEntity.fromDomain(characteristic)));
    }

    @Override
    public Page<Characteristic> getPaginated(Integer page, Integer size) {
        return characteristicJpaRepository.findAll(PageRequest.of(page, size)).map(CharacteristicJpaEntity::toDomain);
    }

    @Override
    public Optional<Characteristic> getEntityById(Long id) {

        return characteristicJpaRepository.findById(id).map(CharacteristicJpaEntity::toDomain);
    }

    @Override
    public Characteristic patch(Characteristic characteristic) {
        return CharacteristicJpaEntity.toDomain(characteristicJpaRepository.save(CharacteristicJpaEntity.fromDomain(characteristic)));
    }

    @Override
    public void deleteById(Long id) {
        characteristicJpaRepository.deleteById(id);
    }

}
