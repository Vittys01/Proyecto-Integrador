package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Image;
import org.springframework.data.domain.Page;

public interface ImageRepository extends CommonDaoRepository<Image> {

    default Page<Image> getImagesByName(String title, Integer page, Integer size) {
        throw new UnsupportedOperationException();
    }
}
