package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.TipoAcabadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoAcabadoJpaRepositorio extends JpaRepository<TipoAcabadoEntity, Long> {

    Optional<TipoAcabadoEntity> findByTipo(String tipo);
}
