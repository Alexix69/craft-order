package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.presentacion.dto.request.MaterialRequestDto;
import com.classic.craftorder.presentacion.dto.response.MaterialResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMaterialDtoMapper {

    Material toDominio(MaterialRequestDto requestDto);

    MaterialResponseDto toResponse(Material dominio);
}
