package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.domain.exception.InvalidCredentialsException;
import com.nuevospa.tasks.domain.model.User;
import com.nuevospa.tasks.application.port.out.UserRepositoryPort;
import com.nuevospa.tasks.infrastructure.config.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        User user = new User(UUID.randomUUID(), "admin", "hashedPassword", "admin@test.com", "Administrator");

        when(userRepositoryPort.findByUsername("admin")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);
        when(jwtService.generateToken("admin")).thenReturn("mocked-token");

        String token = loginUseCase.login("admin", "password");

        assertEquals("mocked-token", token);
        verify(jwtService).generateToken("admin");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepositoryPort.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () ->
                loginUseCase.login("unknown", "password"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsWrong() {
        User user = new User(UUID.randomUUID(), "admin", "hashedPassword", "admin@test.com", "Administrator");

        when(userRepositoryPort.findByUsername("admin")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", "hashedPassword")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () ->
                loginUseCase.login("admin", "wrongpassword"));
    }
}