package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "historial_orden")
@Data
public class HistorialOrdenEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_id", nullable = false)
    private Long ordenId;

    @Column(name = "tipo_evento", nullable = false, length = 30)
    private String tipoEvento;

    @Column(name = "valor_anterior", length = 60)
    private String valorAnterior;

    @Column(name = "valor_nuevo", length = 60)
    private String valorNuevo;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "realizado_por")
    private Long realizadoPor;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
