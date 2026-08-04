package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CotizacionResponseDto {

    private Long id;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private Long tipoMuebleId;
    private String tipoMuebleNombre;
    private Long materialId;
    private String materialNombre;
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
    private String motivoRechazo;
    private String token;
    private OffsetDateTime createdAt;
    private List<HistorialOrdenResponseDto> historialOrden;
    private String estadoOrden;
}
