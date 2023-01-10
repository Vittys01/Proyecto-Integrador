package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Category;
import org.springframework.data.domain.Page;
import lombok.Builder;

public interface CategoryGettingQuery {

    Page<Category> execute(Data data);

    @lombok.Data
    @Builder
    class Data{
        Integer page;
        Integer size;
    }
}
