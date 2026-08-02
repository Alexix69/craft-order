package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.presentacion.dto.request.CotizacionRequestDto;
import com.classic.craftorder.presentacion.dto.response.CotizacionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICotizacionDtoMapper {

    Cotizacion toDominio(CotizacionRequestDto dto);

    CotizacionResponseDto toResponse(Cotizacion dominio);
}
