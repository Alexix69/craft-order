package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IFacturaJpaRepositorio
        extends JpaRepository<FacturaEntity, Long> {

    Optional<FacturaEntity> findByOrdenId(Long ordenId);

    @Query("SELECT COALESCE(MAX(" +
           "CAST(SUBSTRING(f.numeroFactura, 5) AS int)" +
           "), 0) FROM FacturaEntity f")
    int findMaxNumeroFactura();
}
