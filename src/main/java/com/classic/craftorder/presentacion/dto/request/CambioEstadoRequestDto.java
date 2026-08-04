package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CambioEstadoRequestDto {
    @NotBlank
    private String estadoNuevo;
    private String motivo;
}
