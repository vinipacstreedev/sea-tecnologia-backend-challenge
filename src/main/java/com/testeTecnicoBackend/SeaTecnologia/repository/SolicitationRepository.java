package com.testeTecnicoBackend.SeaTecnologia.repository;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.testeTecnicoBackend.SeaTecnologia.enums.SolicitationStatus;

import java.util.List;
import java.util.UUID;

public interface SolicitationRepository extends JpaRepository<Solicitation, UUID> {

    List<Solicitation> findByClient(User client);

    List<Solicitation> findByStatus(SolicitationStatus status);
}