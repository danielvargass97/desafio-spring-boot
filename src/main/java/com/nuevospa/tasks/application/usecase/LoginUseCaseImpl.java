package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.in.LoginUseCase;
import com.nuevospa.tasks.application.port.out.UserRepositoryPort;
import com.nuevospa.tasks.domain.exception.InvalidCredentialsException;
import com.nuevospa.tasks.domain.model.User;
import com.nuevospa.tasks.infrastructure.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(String username, String password) {
        User user = userRepositoryPort.findByUsername(username)
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user.getUsername());
    }
}