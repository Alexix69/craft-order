package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.infraestructura.persistencia.jpa.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IUsuarioJpaMapper {

    Usuario aDominio(UsuarioEntity entity);

    UsuarioEntity aEntity(Usuario dominio);
}
