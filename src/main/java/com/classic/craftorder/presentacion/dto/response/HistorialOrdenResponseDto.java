package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class HistorialOrdenResponseDto {
    private String tipoEvento;
    private String valorAnterior;
    private String valorNuevo;
    private String motivo;
    private OffsetDateTime createdAt;
}
