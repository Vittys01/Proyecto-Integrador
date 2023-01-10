package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.Country;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class CountryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public static CountryJpaEntity fromDomain(Country Country){
        CountryJpaEntity.CountryJpaEntityBuilder builder =  CountryJpaEntity.builder()
                .name(Country.getName());
        if(Country.getId() != null) builder.id(Country.getId());
        return builder.build();
    }

    public static Country toDomain(CountryJpaEntity CountryJpaEntity){
        return Country.builder()
                .id(CountryJpaEntity.getId())
                .name(CountryJpaEntity.getName())
                .build();
    }
}
