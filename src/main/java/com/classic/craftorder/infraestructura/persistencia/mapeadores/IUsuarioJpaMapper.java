package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.infraestructura.persistencia.jpa.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioJpaMapper {

    Usuario toDominio(UsuarioEntity entity);

    UsuarioEntity toEntity(Usuario dominio);
}
