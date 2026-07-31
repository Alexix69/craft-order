package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UsuarioResponseDto {

    private Long id;
    private String nombre;
    private String correo;
    private String rol;
    private Boolean activo;
    private OffsetDateTime createdAt;
}
