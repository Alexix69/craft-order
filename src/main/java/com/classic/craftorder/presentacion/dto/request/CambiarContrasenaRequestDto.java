package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CambiarContrasenaRequestDto {

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{6,}$",
            message = "La contraseña debe tener al menos 6 caracteres, una mayúscula y un número"
    )
    private String contrasenaNueva;
}
