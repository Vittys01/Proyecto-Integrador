package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.CategoryJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.CategoryJpaRepository;
import com.digital.booking.core.domain.Category;
import com.digital.booking.core.port.output.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryJpaAdapter implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryJpaAdapter(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Page<Category> getCategoriesByName(String name, Integer page, Integer size) {
        return categoryJpaRepository.findByNameContains(name, PageRequest.of(page, size)).map(CategoryJpaEntity::toDomain);
    }

    @Override
    public Category save(Category category) {
        return CategoryJpaEntity.toDomain(categoryJpaRepository.save(CategoryJpaEntity.fromDomain(category)));
    }

    @Override
    public Page<Category> getPaginated(Integer page, Integer size) {
        return categoryJpaRepository.findAll(PageRequest.of(page, size)).map(CategoryJpaEntity::toDomain);
    }

    @Override
    public Category patch(Category category) {
        return CategoryJpaEntity.toDomain(categoryJpaRepository.save(CategoryJpaEntity.fromDomain(category)));
    }

    @Override
    public void deleteById(Long id) {
        categoryJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getEntityById(Long id) {
        return categoryJpaRepository.findById(id).map(CategoryJpaEntity::toDomain);
    }
}
