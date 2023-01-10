package com.digital.booking.adapter.output.jpa;

import com.digital.booking.adapter.output.jpa.entities.UserJpaEntity;
import com.digital.booking.adapter.output.jpa.repositories.UserJpaRepository;
import com.digital.booking.core.domain.User;
import com.digital.booking.core.port.output.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserJpaAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        System.out.println(user);
        return UserJpaEntity.toDomain(userJpaRepository.save(UserJpaEntity.fromDomain(user)));
    }

    @Override
    public Page<User> getPaginated(Integer page, Integer size) {
        return UserRepository.super.getPaginated(page, size);
    }

    @Override
    public Optional<User> getEntityById(Long id) {
        return userJpaRepository.findById(id).map(UserJpaEntity::toDomain);
    }

    @Override
    public User patch(User user) {
        return UserRepository.super.patch(user);
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserJpaEntity::toDomain);
    }
}
