package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.presentacion.dto.response.PaginaResponseDto;

import java.util.function.Function;

public class PaginaMapper {

    private PaginaMapper() {
    }

    public static <D, R> PaginaResponseDto<R> toResponse(
            PaginaResultado<D> pagina,
            Function<D, R> mapper) {
        PaginaResponseDto<R> dto = new PaginaResponseDto<>();
        dto.setContenido(pagina.getContenido().stream().map(mapper).toList());
        dto.setPaginaActual(pagina.getPaginaActual());
        dto.setTotalPaginas(pagina.getTotalPaginas());
        dto.setTotalElementos(pagina.getTotalElementos());
        dto.setEsPrimera(pagina.isEsPrimera());
        dto.setEsUltima(pagina.isEsUltima());
        return dto;
    }
}
