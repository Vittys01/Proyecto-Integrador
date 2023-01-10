package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.port.input.RentalSearchByIdCommand;
import com.digital.booking.core.port.output.RentalRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RentalSearchByIdUseCase implements RentalSearchByIdCommand {

    private final RentalRepository rentalRepository;

    public RentalSearchByIdUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Optional<Rental> execute(Long id) {
        return rentalRepository.getEntityById(id);
    }

}
