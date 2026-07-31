package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

@Data
public class ErrorResponseDto {

    private String mensaje;
    private String codigo;
    private String campo;
}
