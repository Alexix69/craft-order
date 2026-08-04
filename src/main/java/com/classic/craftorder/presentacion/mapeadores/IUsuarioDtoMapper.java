package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.presentacion.dto.request.UsuarioRequestDto;
import com.classic.craftorder.presentacion.dto.response.UsuarioResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioDtoMapper {

    Usuario toDominio(UsuarioRequestDto requestDto);

    UsuarioResponseDto toResponse(Usuario dominio);
}
