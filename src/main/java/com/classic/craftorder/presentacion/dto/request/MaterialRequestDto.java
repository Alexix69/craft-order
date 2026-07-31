package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialRequestDto {

    @NotBlank
    private String nombre;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal precioPorM3;

    @NotNull
    private Boolean activo;
}
