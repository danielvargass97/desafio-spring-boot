package com.nuevospa.tasks.application.port.out;

import com.nuevospa.tasks.domain.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
    Optional<User> findByUUID(UUID uuid);
}