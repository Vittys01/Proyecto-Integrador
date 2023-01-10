package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Category;
import com.digital.booking.core.domain.City;
import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.RentalPatchCommand;
import com.digital.booking.core.port.output.RentalRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RentalPatchUseCase implements RentalPatchCommand {

    private final RentalRepository rentalRepository;

    public RentalPatchUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental execute(Data data) {
        if(data.getId() == null) throw new BusinessException(ErrorCode.MISSING_CATEGORY_ID);
        Optional<Rental> productFound = rentalRepository.getEntityById(data.getId());
        if(productFound.isEmpty()) throw new BusinessException(ErrorCode.CATEGORY_TO_UPDATE_NOT_FOUND);
        Rental product = Rental.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .category(Category.builder().id(data.getCategoryId()).build())
                .city(City.builder().id(data.getCityId()).build())
                .distance(data.getDistance())
                .build();
        return rentalRepository.save(product);
    }
}
