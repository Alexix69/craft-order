package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMaterialJpaRepositorio extends JpaRepository<MaterialEntity, Long> {

    List<MaterialEntity> findByActivoTrue();

    Page<MaterialEntity> findByActivoTrue(Pageable pageable);

    Page<MaterialEntity> findByNombreContainingIgnoreCaseAndActivoTrue(
            String nombre, Pageable pageable);

    Page<MaterialEntity> findAll(Pageable pageable);

    Page<MaterialEntity> findByNombreContainingIgnoreCase(
            String nombre, Pageable pageable);
}
