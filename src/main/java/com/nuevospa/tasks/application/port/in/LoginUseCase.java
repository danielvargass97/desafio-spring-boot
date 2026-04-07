package com.nuevospa.tasks.application.port.in;

import com.nuevospa.tasks.domain.model.User;

public interface LoginUseCase {
    String login(String username, String password);
}