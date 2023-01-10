package com.digital.booking.core.usecases;

import com.digital.booking.core.port.input.RentalDeletionCommand;
import com.digital.booking.core.port.output.RentalRepository;
import org.springframework.stereotype.Component;

@Component
public class RentalDeletionUseCase implements RentalDeletionCommand {

    private final RentalRepository rentalRepository;

    public RentalDeletionUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void execute(Long id) {
        rentalRepository.deleteById(id);
    }
}
