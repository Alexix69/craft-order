package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.presentacion.dto.request.TipoMuebleRequestDto;
import com.classic.craftorder.presentacion.dto.response.TipoMuebleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITipoMuebleDtoMapper {

    TipoMueble toDominio(TipoMuebleRequestDto requestDto);

    TipoMuebleResponseDto toResponse(TipoMueble dominio);
}
