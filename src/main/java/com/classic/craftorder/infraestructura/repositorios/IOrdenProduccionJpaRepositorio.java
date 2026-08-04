package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.OrdenProduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrdenProduccionJpaRepositorio
        extends JpaRepository<OrdenProduccionEntity, Long> {

    List<OrdenProduccionEntity> findByEstadoActual(String estadoActual);

    List<OrdenProduccionEntity> findByArtesanoId(Long artesanoId);

    Optional<OrdenProduccionEntity> findByCotizacionId(Long cotizacionId);

    @Query("SELECT o FROM OrdenProduccionEntity o " +
            "WHERE o.estadoActual != 'LISTO_ENTREGA' " +
            "OR (o.estadoActual = 'LISTO_ENTREGA' " +
            "    AND o.updatedAt >= :limite)")
    List<OrdenProduccionEntity> findAllActivas(
            @Param("limite") OffsetDateTime limite);
}
