package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Rental;

import java.util.Optional;

public interface RentalSearchByIdCommand {

    Optional<Rental> execute(Long id);
}
