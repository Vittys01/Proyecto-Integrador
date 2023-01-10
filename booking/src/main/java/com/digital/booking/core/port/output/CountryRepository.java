package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Country;
import org.springframework.data.domain.Page;

public interface CountryRepository extends CommonDaoRepository<Country>{

    default Page<Country> getCategoriesByName(String name, Integer page, Integer size){throw new UnsupportedOperationException();}
}
