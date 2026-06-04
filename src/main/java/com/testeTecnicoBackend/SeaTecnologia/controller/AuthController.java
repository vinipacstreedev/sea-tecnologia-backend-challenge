package com.testeTecnicoBackend.SeaTecnologia.controller;

import com.testeTecnicoBackend.SeaTecnologia.dto.auth.RegisterRequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.testeTecnicoBackend.SeaTecnologia.dto.auth.LoginRequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.dto.auth.LoginResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequestDTO request) {
        authService.register(request);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO request) {
        return authService.login(request);
    }
}