package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.Category;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean status;

    private String image;

    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Formula(value = "(SELECT COUNT(*) FROM rentals WHERE rentals.category_id=id)")
    Long rentalCount;

    public static CategoryJpaEntity fromDomain(Category category){
        CategoryJpaEntityBuilder builder =  CategoryJpaEntity.builder()
                .name(category.getName())
                .status(category.getStatus())
                .createdAt(category.getCreatedAt())
                .description(category.getDescription())
                .image(category.getImage());
        if(category.getId() != null) builder.id(category.getId());
        return builder.build();
    }

    public static Category toDomain(CategoryJpaEntity categoryJpaEntity){
        return Category.builder()
                .id(categoryJpaEntity.getId())
                .name(categoryJpaEntity.getName())
                .status(categoryJpaEntity.getStatus())
                .image(categoryJpaEntity.getImage())
                .description(categoryJpaEntity.getDescription())
                .createdAt(categoryJpaEntity.getCreatedAt())
                .rentalsQuantity(categoryJpaEntity.getRentalCount())
                .build();
    }
}
