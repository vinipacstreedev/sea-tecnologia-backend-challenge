package com.testeTecnicoBackend.SeaTecnologia.controller;

import com.testeTecnicoBackend.SeaTecnologia.dto.auth.RegisterRequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.testeTecnicoBackend.SeaTecnologia.dto.auth.LoginRequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.dto.auth.LoginResponseDTO;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/me")
    public String me(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return "Usuário logado: " + user.getEmail() + " | Perfil: " + user.getRole();
    }
}