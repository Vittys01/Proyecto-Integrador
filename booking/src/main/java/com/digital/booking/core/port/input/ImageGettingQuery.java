package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Image;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface ImageGettingQuery {

    Page<Image> execute(Data data);

    @lombok.Data
    @Builder
    class Data {
        Integer page;
        Integer size;
    }
}
