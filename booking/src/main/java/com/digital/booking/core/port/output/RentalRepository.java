package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RentalRepository extends CommonDaoRepository<Rental>{

    default Page<Rental> getCategoriesByName(String name, Integer page, Integer size){throw new UnsupportedOperationException();}

    default Page<Rental> findByFilters(String checkIn, String checkOut, Long city, Long category, Integer page, Integer size, String order, String currentUser) {
        throw new UnsupportedOperationException();
    }
}
