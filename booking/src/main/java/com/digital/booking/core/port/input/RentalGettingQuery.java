package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Rental;
import lombok.Builder;
import org.springframework.data.domain.Page;

public interface RentalGettingQuery {

    Page<Rental> execute(Data data);

    @lombok.Data
    @Builder
    class Data{
        String checkIn;
        String checkOut;
        Long city;
        Long category;
        Integer page;
        Integer size;
        String order;
        String currentUser;
    }
}
