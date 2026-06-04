package com.testeTecnicoBackend.SeaTecnologia.service;

import com.testeTecnicoBackend.SeaTecnologia.dto.auth.RegisterRequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.enums.Role;
import com.testeTecnicoBackend.SeaTecnologia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(request.password())
                .role(Role.CLIENT)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }
}