package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMaterialJpaRepositorio extends JpaRepository<MaterialEntity, Long> {

    List<MaterialEntity> findByActivoTrue();
}
