package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class CotizacionResponseDto {

    private Long id;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private Long tipoMuebleId;
    private Long materialId;
    private String tipoAcabado;
    private BigDecimal altoCm;
    private BigDecimal anchoCm;
    private BigDecimal profundidadCm;
    private BigDecimal precioMaterialSnap;
    private BigDecimal costoBaseMoSnap;
    private BigDecimal porcentajeAcabadoSnap;
    private BigDecimal costoEstimado;
    private BigDecimal costoAprobado;
    private String estado;
    private String token;
    private OffsetDateTime createdAt;
}
