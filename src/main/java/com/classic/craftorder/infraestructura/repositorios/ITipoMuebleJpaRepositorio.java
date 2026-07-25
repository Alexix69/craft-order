package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.TipoMuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITipoMuebleJpaRepositorio extends JpaRepository<TipoMuebleEntity, Long> {

    List<TipoMuebleEntity> findByActivoTrue();
}
