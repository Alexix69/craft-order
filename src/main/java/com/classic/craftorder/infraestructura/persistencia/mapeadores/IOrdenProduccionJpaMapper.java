package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.infraestructura.persistencia.jpa.OrdenProduccionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrdenProduccionJpaMapper {

    OrdenProduccion toDominio(OrdenProduccionEntity entity);

    OrdenProduccionEntity toEntity(OrdenProduccion dominio);
}
