package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Image;
import lombok.Builder;

public interface ImagePatchCommand {

    Image execute(ImagePatchCommand.Data data);

    @lombok.Data
    @Builder
    class Data{
        private Long id;
        private String url;
        private Long rentalId;
    }
}
