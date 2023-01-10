package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Image;
import com.digital.booking.core.port.input.ImageGettingQuery;
import com.digital.booking.core.port.output.ImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ImageGettingUseCase implements ImageGettingQuery {

    private final ImageRepository imageRepository;

    public ImageGettingUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Page<Image> execute(Data data) {
        Integer page = data.getPage() == null ? 0 : data.getPage();
        Integer size = data.getSize() == null || data.getSize() < 10 ? 10 : data.getSize();
        return imageRepository.getPaginated(page, size);
    }
}
