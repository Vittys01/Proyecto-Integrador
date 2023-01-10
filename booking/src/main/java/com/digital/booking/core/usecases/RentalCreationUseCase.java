package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.*;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.RentalCreationCommand;
import com.digital.booking.core.port.output.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RentalCreationUseCase implements RentalCreationCommand {

    private final RentalRepository rentalRepository;

    private final ImageRepository imageRepository;

    private final CharacteristicRepository characteristicRepository;

    private final CityRepository cityRepository;

    private final CategoryRepository categoryRepository;

    public RentalCreationUseCase(RentalRepository rentalRepository, ImageRepository imageRepository, CharacteristicRepository characteristicRepository, CityRepository cityRepository, CategoryRepository categoryRepository) {
        this.rentalRepository = rentalRepository;
        this.imageRepository = imageRepository;
        this.characteristicRepository = characteristicRepository;
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Rental execute(Data data) {
        Set<Characteristic> characteristics = data.getCharacteristics().stream().map(new Function<Characteristic, Characteristic>() {
            @Override
            public Characteristic apply(Characteristic characteristic) {
                return characteristicRepository.getEntityById(characteristic.getId()).orElseThrow(() -> {
                    throw new BusinessException(ErrorCode.CHARACTERISTIC_NOT_FOUND);
                });
            }
        }).collect(Collectors.toSet());

        City city = cityRepository.getEntityById(data.getCityId()).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.CITY_NOT_FOUND);
        });

        Category category = categoryRepository.getEntityById(data.getCategoryId()).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.MISSING_CATEGORY_ID);
        });

        Rental product = Rental.builder()
                .name(data.getName())
                .description(data.getDescription())
                .category(category)
                .city(city)
                .distance(data.getDistance())
                .imageList(new ArrayList<>())
                .characteristics(characteristics)
                .build();
        Rental r1 = rentalRepository.save(product);
        List<Image> images = data.getImages().stream().map(image -> imageRepository.save(Image.builder().url(image.getUrl()).rental(r1).build())).collect(Collectors.toList());

        return r1.withImageList(images);
    }
}
