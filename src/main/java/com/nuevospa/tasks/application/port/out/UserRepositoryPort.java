package com.nuevospa.tasks.application.port.out;

import com.nuevospa.tasks.domain.model.User;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
}