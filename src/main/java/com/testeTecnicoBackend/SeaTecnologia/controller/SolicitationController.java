package com.testeTecnicoBackend.SeaTecnologia.controller;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.service.SolicitationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}
