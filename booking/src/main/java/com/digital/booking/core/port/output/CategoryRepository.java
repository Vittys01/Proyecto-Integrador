package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Category;
import org.springframework.data.domain.Page;

public interface CategoryRepository extends CommonDaoRepository<Category>{

    default Page<Category> getCategoriesByName(String name, Integer page, Integer size){throw new UnsupportedOperationException();}
}
