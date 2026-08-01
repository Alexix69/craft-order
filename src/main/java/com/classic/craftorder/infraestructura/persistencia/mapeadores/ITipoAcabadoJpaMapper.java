package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.TipoAcabado;
import com.classic.craftorder.infraestructura.persistencia.jpa.TipoAcabadoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITipoAcabadoJpaMapper {

    TipoAcabado toDominio(TipoAcabadoEntity entity);

    TipoAcabadoEntity toEntity(TipoAcabado dominio);
}
