package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Characteristic;
import com.digital.booking.core.port.input.CharacteristicGettingQuery;
import com.digital.booking.core.port.output.CharacteristicRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CharacteristicGettingQueryUseCase implements CharacteristicGettingQuery {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicGettingQueryUseCase(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public Page<Characteristic> execute(Data data) {
        return characteristicRepository.getPaginated(data.getPage(), data.getSize());
    }
}
