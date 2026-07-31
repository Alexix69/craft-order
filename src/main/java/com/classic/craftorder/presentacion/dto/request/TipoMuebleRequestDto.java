package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoMuebleRequestDto {

    @NotBlank
    private String nombre;

    private String descripcion;

    private String fotoUrl;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal costoBaseMo;

    @NotNull
    private Boolean activo;
}
