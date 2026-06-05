package com.testeTecnicoBackend.SeaTecnologia.controller;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.service.SolicitationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step1RequestDTO;
import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/solicitations")
public class SolicitationController {

    private final SolicitationService solicitationService;

    public SolicitationController(SolicitationService solicitationService) {
        this.solicitationService = solicitationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Solicitation create(Authentication authentication) {
        User client = (User) authentication.getPrincipal();

        return solicitationService.createDraft(client);
    }
    @PutMapping("/{id}/step1")
    public Solicitation saveStep1(
            @PathVariable UUID id,
            @RequestBody @Valid Step1RequestDTO request,
            Authentication authentication
    ) {
        User client = (User) authentication.getPrincipal();

        return solicitationService.saveStep1(id, client, request);
    }
}
