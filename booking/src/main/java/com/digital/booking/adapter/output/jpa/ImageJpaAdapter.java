package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.ImageJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.ImageJpaRepository;
import com.digital.booking.core.domain.Image;
import com.digital.booking.core.port.output.ImageRepository;
import org.springframework.stereotype.Component;

@Component
public class ImageJpaAdapter implements ImageRepository {

    private final ImageJpaRepository imageJpaRepository;

    public ImageJpaAdapter(ImageJpaRepository imageJpaRepository) {
        this.imageJpaRepository = imageJpaRepository;
    }

    @Override
    public Image save(Image image) {
        return ImageJpaEntity.toDomain(imageJpaRepository.save(ImageJpaEntity.fromDomain(image)));
    }

    @Override
    public void deleteById(Long id) {
        imageJpaRepository.deleteById(id);
    }
}
