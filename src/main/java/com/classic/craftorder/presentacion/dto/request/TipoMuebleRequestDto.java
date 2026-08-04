package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoMuebleRequestDto {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    private String descripcion;

    @Size(max = 500)
    private String fotoUrl;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal costoBaseMo;
}
