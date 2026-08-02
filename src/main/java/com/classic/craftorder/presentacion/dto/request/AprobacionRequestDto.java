package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AprobacionRequestDto {

    @NotNull
    private BigDecimal costoAprobado;
}
