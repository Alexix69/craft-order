package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.infraestructura.persistencia.jpa.MaterialEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMaterialJpaMapper {

    Material toDominio(MaterialEntity entity);

    MaterialEntity toEntity(Material dominio);
}
