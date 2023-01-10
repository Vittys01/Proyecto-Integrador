package com.digital.booking.core.usecases;

import com.digital.booking.core.port.input.CategoryDeletionCommand;
import com.digital.booking.core.port.output.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryDeletionUseCase implements CategoryDeletionCommand {

    private final CategoryRepository categoryRepository;

    public CategoryDeletionUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Long id) {
        categoryRepository.deleteById(id);
    }
}
