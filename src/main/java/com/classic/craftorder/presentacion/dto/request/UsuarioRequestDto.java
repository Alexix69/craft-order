package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UsuarioRequestDto {

    @NotBlank
    private String nombre;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String passwordHash;

    @NotBlank
    @Pattern(regexp = "^(ADMIN|ARTESANO)$", message = "El rol debe ser ADMIN o ARTESANO")
    private String rol;
}
