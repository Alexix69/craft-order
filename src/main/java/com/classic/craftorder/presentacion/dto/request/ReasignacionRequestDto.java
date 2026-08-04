package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReasignacionRequestDto {
    @NotNull
    private Long nuevoArtesanoId;
    @NotBlank
    private String motivo;
}
