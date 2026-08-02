package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.CotizacionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICotizacionJpaRepositorio extends JpaRepository<CotizacionEntity, Long> {

    Optional<CotizacionEntity> findByToken(String token);

    Page<CotizacionEntity> findByEstado(String estado, Pageable pageable);
}
