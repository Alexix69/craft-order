package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RechazoRequestDto {

    @NotBlank
    private String motivoRechazo;
}
