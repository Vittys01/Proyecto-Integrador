package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Category;
import com.digital.booking.core.port.input.CategoryCreationCommand;
import com.digital.booking.core.port.output.CategoryRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CategoryCreationUseCase implements CategoryCreationCommand {

    private final CategoryRepository categoryRepository;

    public CategoryCreationUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(Data data) {
        Category category = Category.builder()
                .name(data.getName())
                .status(true)
                .description(data.getDescription())
                .image(data.getImage())
                .createdAt(Instant.now())
                .build();
        return categoryRepository.save(category);
    }
}
