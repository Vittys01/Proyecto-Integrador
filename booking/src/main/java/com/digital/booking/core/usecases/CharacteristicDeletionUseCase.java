package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Characteristic;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.CharacteristicDeletionCommand;
import com.digital.booking.core.port.output.CharacteristicRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CharacteristicDeletionUseCase implements CharacteristicDeletionCommand {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicDeletionUseCase(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }


    @Override
    public Characteristic execute(Data data) {

        Optional<Characteristic> characteristic = characteristicRepository.getEntityById(data.getId());

        if (characteristic.isEmpty()) {
            throw new BusinessException(ErrorCode.CHARACTERISTIC_NOT_FOUND);
        }

        characteristicRepository.deleteById(data.getId());

        return characteristic.get();
    }
}
