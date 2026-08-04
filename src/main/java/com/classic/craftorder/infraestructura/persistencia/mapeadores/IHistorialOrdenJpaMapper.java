package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.HistorialOrden;
import com.classic.craftorder.infraestructura.persistencia.jpa.HistorialOrdenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IHistorialOrdenJpaMapper {

    HistorialOrden toDominio(HistorialOrdenEntity entity);

    HistorialOrdenEntity toEntity(HistorialOrden dominio);
}
