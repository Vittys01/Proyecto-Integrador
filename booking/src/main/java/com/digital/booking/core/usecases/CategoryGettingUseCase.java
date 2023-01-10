package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Category;
import com.digital.booking.core.port.input.CategoryGettingQuery;
import com.digital.booking.core.port.output.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoryGettingUseCase implements CategoryGettingQuery {

    private final CategoryRepository categoryRepository;

    public CategoryGettingUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> execute(Data data) {
        Integer page = data.getPage() == null ? 0 : data.getPage();
        Integer size = data.getSize() == null || data.getSize() < 10 ? 10 : data.getSize();
        return categoryRepository.getPaginated(page, size);
    }
}
