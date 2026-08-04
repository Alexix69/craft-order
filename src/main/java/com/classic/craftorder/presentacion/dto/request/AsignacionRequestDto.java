package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsignacionRequestDto {
    @NotNull
    private Long artesanoId;
}
