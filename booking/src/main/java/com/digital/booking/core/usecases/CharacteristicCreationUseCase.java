package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Characteristic;
import com.digital.booking.core.port.input.CharacteristicCreationCommand;
import com.digital.booking.core.port.output.CharacteristicRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class CharacteristicCreationUseCase implements CharacteristicCreationCommand {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicCreationUseCase(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public Characteristic execute(Data data) {
        Characteristic characteristic = Characteristic.builder()
                .title(data.getTitle())
                .icon(data.getIcon())
                .createTimestamp(Timestamp.from(Instant.now()))
                .softDelete(Boolean.FALSE)
                .build();

        return characteristicRepository.save(characteristic);
    }
}
