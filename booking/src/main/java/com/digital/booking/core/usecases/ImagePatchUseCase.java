package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Image;
import com.digital.booking.core.domain.Rental;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.ImagePatchCommand;
import com.digital.booking.core.port.output.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImagePatchUseCase implements ImagePatchCommand{

    private final ImageRepository imageRepository;

    public ImagePatchUseCase(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image execute(ImagePatchCommand.Data data) {
        if(data.getId() == null) throw new BusinessException(ErrorCode.MISSING_CATEGORY_ID);
        Optional<Image> ImageFound = imageRepository.getEntityById(data.getId());
        if(ImageFound.isEmpty()) throw new BusinessException(ErrorCode.CATEGORY_TO_UPDATE_NOT_FOUND);
        Image image = Image.builder()
                .id(data.getId())
                .url(data.getUrl())
                .rental(Rental.builder().id(data.getRentalId()).build())
                .build();
        return imageRepository.save(image);
    }
}
