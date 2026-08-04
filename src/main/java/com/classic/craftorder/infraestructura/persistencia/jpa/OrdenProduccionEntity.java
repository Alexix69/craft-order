package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "orden_produccion", uniqueConstraints = {
    @UniqueConstraint(name = "uk_orden_produccion_cotizacion_id",
                      columnNames = "cotizacion_id")
})
@Data
public class OrdenProduccionEntity extends AuditoriaBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cotizacion_id", nullable = false)
    private Long cotizacionId;

    @Column(name = "artesano_id", nullable = false)
    private Long artesanoId;

    @Column(name = "estado_actual", nullable = false, length = 40)
    private String estadoActual;

    @Column(name = "fecha_inicio")
    private OffsetDateTime fechaInicio;

    @Column(name = "fecha_finalizacion")
    private OffsetDateTime fechaFinalizacion;
}
