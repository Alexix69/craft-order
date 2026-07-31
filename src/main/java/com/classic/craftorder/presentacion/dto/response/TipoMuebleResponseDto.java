package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class TipoMuebleResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String fotoUrl;
    private BigDecimal costoBaseMo;
    private Boolean activo;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
