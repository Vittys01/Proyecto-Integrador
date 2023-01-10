package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Category;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.CategoryPatchCommand;
import com.digital.booking.core.port.output.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryPatchUseCase  implements CategoryPatchCommand {

    private final CategoryRepository categoryRepository;

    public CategoryPatchUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(Data data) {
        if(data.getId() == null) throw new BusinessException(ErrorCode.MISSING_CATEGORY_ID);
        Optional<Category> categoryFound = categoryRepository.getEntityById(data.getId());
        if(categoryFound.isEmpty()) throw new BusinessException(ErrorCode.CATEGORY_TO_UPDATE_NOT_FOUND);
        Category category = Category.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .image(data.getImage())
                .status(data.getStatus())
                .build();
        return categoryRepository.patch(category);
    }
}
