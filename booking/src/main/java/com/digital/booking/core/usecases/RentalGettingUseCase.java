package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.port.input.RentalGettingQuery;
import com.digital.booking.core.port.output.RentalRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class RentalGettingUseCase implements RentalGettingQuery {

    private RentalRepository rentalRepository;

    public RentalGettingUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Page<Rental> execute(Data data) {
        return rentalRepository.findByFilters(data.getCheckIn(), data.getCheckOut(), data.getCity(), data.getCategory(), data.getPage(), data.getSize(), data.getOrder(), data.getCurrentUser());
    }
}
