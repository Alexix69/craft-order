package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.dominio.entidades.Factura;
import com.classic.craftorder.dominio.entidades.HistorialOrden;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.presentacion.dto.response.FacturaResponseDto;
import com.classic.craftorder.presentacion.dto.response.HistorialOrdenResponseDto;
import com.classic.craftorder.presentacion.dto.response.OrdenProduccionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrdenProduccionDtoMapper {
    OrdenProduccionResponseDto toResponse(OrdenProduccion dominio);
    HistorialOrdenResponseDto toResponse(HistorialOrden dominio);
    FacturaResponseDto toResponse(Factura dominio);
}
