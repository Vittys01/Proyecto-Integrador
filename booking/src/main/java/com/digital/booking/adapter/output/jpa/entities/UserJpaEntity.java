package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.User;
import com.digital.booking.core.domain.enums.Authority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean status;

    @ManyToMany
    @JoinTable( name = "user_has_authorities",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<AuthorityJpaEntity> authorities;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityJpaEntity city;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public static UserJpaEntity fromDomain(User user){
        return UserJpaEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .status(user.getStatus())
                .authorities(user.getAuthorities()
                                .stream()
                                .map(authority -> AuthorityJpaEntity.builder().id(authority.getValue()).build()).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toDomain(UserJpaEntity userJpaEntity){
        return User.builder()
                .id(userJpaEntity.getId())
                .name(userJpaEntity.getName())
                .lastName(userJpaEntity.getLastName())
                .email(userJpaEntity.getEmail())
                .password(userJpaEntity.getPassword())
                .status(userJpaEntity.getStatus())
                .authorities(userJpaEntity.getAuthorities().stream().map(authority -> Authority.getFromValue(authority.getId())).collect(Collectors.toSet()))
                .createdAt(userJpaEntity.getCreatedAt())
                .updatedAt(userJpaEntity.getUpdatedAt())
                .build();
    }
}
