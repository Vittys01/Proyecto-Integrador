package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.Image;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class ImageJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rental_id", nullable = false)
    private RentalJpaEntity rental;

    public static ImageJpaEntity fromDomain(Image image) {
        ImageJpaEntityBuilder builder = ImageJpaEntity.builder()
                .url(image.getUrl());
        if (image.getRental() != null) builder.rental(RentalJpaEntity.fromDomain(image.getRental()));
        if (image.getId() != null) builder.id(image.getId());

        return builder.build();
    }

    public static Image toDomain(ImageJpaEntity image) {
        return Image.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }
}
