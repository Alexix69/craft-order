package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

@Data
public class LoginResponseDto {

    private Long id;
    private String nombre;
    private String correo;
    private String rol;
}
