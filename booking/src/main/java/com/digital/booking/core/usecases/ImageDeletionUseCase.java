package com.digital.booking.core.usecases;

import com.digital.booking.core.port.input.ImageDeletionCommand;
import com.digital.booking.core.port.output.ImageRepository;
import org.springframework.stereotype.Component;

@Component
public class ImageDeletionUseCase implements ImageDeletionCommand {

    private final ImageRepository imageRepository;

    public ImageDeletionUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void execute(Long id) {
        imageRepository.deleteById(id);
    }
}
