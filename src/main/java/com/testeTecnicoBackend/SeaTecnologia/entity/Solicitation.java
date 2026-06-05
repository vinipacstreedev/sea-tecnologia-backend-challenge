package com.testeTecnicoBackend.SeaTecnologia.entity;

import com.testeTecnicoBackend.SeaTecnologia.enums.Priority;
import com.testeTecnicoBackend.SeaTecnologia.enums.ServiceType;
import com.testeTecnicoBackend.SeaTecnologia.enums.SolicitationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solicitations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SolicitationStatus status;

    @Column(nullable = false)
    private Integer currentStep;

    // STEP 1
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private String title;

    @Column(length = 1000)
    private String description;

    // STEP 2
    private String cep;
    private String number;
    private String complement;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    // STEP 3
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate preferredDate;

    private BigDecimal estimatedValue;

    private Boolean termsAccepted;

    // Auditoria
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime submittedAt;
    private LocalDateTime analyzedAt;

    @ManyToOne
    @JoinColumn(name = "analyzed_by")
    private User analyzedBy;

    @Column(length = 1000)
    private String analysisComment;
}