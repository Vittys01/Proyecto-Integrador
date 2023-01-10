package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Image;
import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.port.input.ImageCreationCommand;
import com.digital.booking.core.port.output.ImageRepository;
import org.springframework.stereotype.Component;

@Component
public class ImageCreationUseCase implements ImageCreationCommand {

    private final ImageRepository imageRepository;

    public ImageCreationUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image execute(Data data) {
        Image image = Image.builder()
                .url(data.getUrl())
                .rental(Rental.builder().id(data.getRentalId()).build())
                .build();
        return imageRepository.save(image);
    }
}
