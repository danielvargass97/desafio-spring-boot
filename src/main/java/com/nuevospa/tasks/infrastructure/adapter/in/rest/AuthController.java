package com.nuevospa.tasks.infrastructure.adapter.in.rest;

import com.nuevospa.tasks.application.port.in.LoginUseCase;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.api.AuthApi;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.model.LoginRequest;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController implements AuthApi {

    private final LoginUseCase loginUseCase;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        String token = loginUseCase.login(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setType("Bearer");
        response.setUsername(loginRequest.getUsername());

        return ResponseEntity.ok(response);
    }
}