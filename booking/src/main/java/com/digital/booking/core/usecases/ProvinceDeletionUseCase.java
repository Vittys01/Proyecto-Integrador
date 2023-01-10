package com.digital.booking.core.usecases;

import com.digital.booking.core.port.input.ProvinceDeletionCommand;
import com.digital.booking.core.port.output.ProvinceRepository;
import org.springframework.stereotype.Component;

@Component
public class ProvinceDeletionUseCase implements ProvinceDeletionCommand {

    private final ProvinceRepository provinceRepository;

    public ProvinceDeletionUseCase(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public void execute(Long id) {
        provinceRepository.deleteById(id);
    }
}
