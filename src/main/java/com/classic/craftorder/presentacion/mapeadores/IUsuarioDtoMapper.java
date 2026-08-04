package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.presentacion.dto.request.UsuarioRequestDto;
import com.classic.craftorder.presentacion.dto.response.UsuarioResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface IUsuarioDtoMapper {

    Usuario aDominio(UsuarioRequestDto requestDto);

    UsuarioResponseDto aResponseDto(Usuario dominio);
}
