package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginaResponseDto<T> {

    private List<T> contenido;
    private int paginaActual;
    private int totalPaginas;
    private long totalElementos;
    private boolean esPrimera;
    private boolean esUltima;
}
