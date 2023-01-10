package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.Province;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provinces")
public class ProvinceJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    private CountryJpaEntity country;

    public static ProvinceJpaEntity fromDomain(Province province){
        return ProvinceJpaEntity.builder()
                .id(province.getId())
                .name(province.getName())
                .country(CountryJpaEntity.fromDomain(province.getCountry()))
                .build();
    }

    public static Province toDomain(ProvinceJpaEntity province){
        return Province.builder()
                .id(province.getId())
                .name(province.getName())
                .country(CountryJpaEntity.toDomain(province.getCountry()))
                .build();
    }
}
