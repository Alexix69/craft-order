package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "cotizacion", uniqueConstraints = {
    @UniqueConstraint(name = "uk_cotizacion_token", columnNames = "token")
})
@Data
public class CotizacionEntity extends AuditoriaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombreCliente;

    @Column(nullable = false, length = 180)
    private String correoCliente;

    @Column(nullable = false, length = 30)
    private String telefonoCliente;

    @Column(nullable = false)
    private Long tipoMuebleId;

    @Column(nullable = false)
    private Long materialId;

    @Column(nullable = false, length = 30)
    private String tipoAcabado;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal altoCm;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal anchoCm;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal profundidadCm;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precioMaterialSnap;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal costoBaseMoSnap;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeAcabadoSnap;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal costoEstimado;

    @Column(precision = 12, scale = 2)
    private BigDecimal costoAprobado;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String motivoRechazo;

    @Column(nullable = false, unique = true, length = 64)
    private String token;
}
