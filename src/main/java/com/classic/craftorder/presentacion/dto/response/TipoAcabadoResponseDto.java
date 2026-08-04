package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoAcabadoResponseDto {

    private Long id;
    private String tipo;
    private BigDecimal porcentaje;
}
