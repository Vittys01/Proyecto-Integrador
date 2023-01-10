package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Characteristic;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.CharacteristicPatchCommand;
import com.digital.booking.core.port.output.CharacteristicRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CharacteristicPatchUseCase implements CharacteristicPatchCommand {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicPatchUseCase(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public Characteristic execute(Data data) {
        if (data.getId() == null) throw new BusinessException(ErrorCode.MISSING_CHARACTERISTIC_ID);
        Optional<Characteristic> characteristicFound = characteristicRepository.getEntityById(data.getId());
        if (characteristicFound.isEmpty()) throw new BusinessException(ErrorCode.CHARACTERISTIC_NOT_FOUND);
        Characteristic characteristic = Characteristic.builder()
                .id(data.getId())
                .title(data.getTitle())
                .icon(data.getIcon())
                .softDelete(characteristicFound.get().getSoftDelete())
                .createTimestamp(characteristicFound.get().getCreateTimestamp())
                .build();
        return characteristicRepository.patch(characteristic);
    }
}
