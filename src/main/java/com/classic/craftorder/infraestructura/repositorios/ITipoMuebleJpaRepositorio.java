package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.TipoMuebleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITipoMuebleJpaRepositorio extends JpaRepository<TipoMuebleEntity, Long> {

    List<TipoMuebleEntity> findByActivoTrue();

    Page<TipoMuebleEntity> findByActivoTrue(Pageable pageable);

    Page<TipoMuebleEntity> findByNombreContainingIgnoreCaseAndActivoTrue(
            String nombre, Pageable pageable);

    Page<TipoMuebleEntity> findAll(Pageable pageable);

    Page<TipoMuebleEntity> findByNombreContainingIgnoreCase(
            String nombre, Pageable pageable);
}
