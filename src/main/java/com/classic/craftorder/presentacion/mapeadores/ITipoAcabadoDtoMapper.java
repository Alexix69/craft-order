package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.TipoAcabado;
import com.classic.craftorder.presentacion.dto.response.TipoAcabadoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITipoAcabadoDtoMapper {

    TipoAcabadoResponseDto toResponse(TipoAcabado dominio);
}
