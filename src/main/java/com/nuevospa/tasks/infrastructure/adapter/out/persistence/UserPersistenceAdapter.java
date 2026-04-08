package com.nuevospa.tasks.infrastructure.adapter.out.persistence;

import com.nuevospa.tasks.application.port.out.UserRepositoryPort;
import com.nuevospa.tasks.domain.model.User;
import com.nuevospa.tasks.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(this::toModel);
    }

    private User toModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getFullName()
        );
    }
}