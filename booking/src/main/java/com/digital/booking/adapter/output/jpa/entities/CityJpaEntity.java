package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.City;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class CityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private ProvinceJpaEntity province;

    public static CityJpaEntity fromDomain(City city) {
        CityJpaEntityBuilder builder = CityJpaEntity.builder()
                .name(city.getName())
                .province(ProvinceJpaEntity.fromDomain(city.getProvince()));
        if (city.getId() != null) builder.id(city.getId());
        return builder.build();
    }

    public static City toDomain(CityJpaEntity city) {
        return City.builder()
                .id(city.getId())
                .name(city.getName())
                .province(ProvinceJpaEntity.toDomain(city.getProvince()))
                .build();
    }
}
