package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoAcabadoPorcentajeRequestDto {

    @NotNull
    @DecimalMin("0")
    @DecimalMax("100")
    private BigDecimal porcentaje;
}
