package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Province;
import org.springframework.data.domain.Page;

public interface ProvinceRepository extends CommonDaoRepository<Province>{

    default Page<Province> getCategoriesByName(String name, Integer page, Integer size){throw new UnsupportedOperationException();}
}
