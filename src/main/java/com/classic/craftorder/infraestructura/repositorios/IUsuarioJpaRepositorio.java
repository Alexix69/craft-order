package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioJpaRepositorio extends JpaRepository<UsuarioEntity, Long> {
}
