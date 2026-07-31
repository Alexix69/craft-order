package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class MaterialResponseDto {

    private Long id;
    private String nombre;
    private BigDecimal precioPorM3;
    private Boolean activo;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
