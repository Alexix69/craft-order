package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.infraestructura.persistencia.jpa.TipoMuebleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITipoMuebleJpaMapper {

    TipoMueble toDominio(TipoMuebleEntity entity);

    TipoMuebleEntity toEntity(TipoMueble dominio);
}
