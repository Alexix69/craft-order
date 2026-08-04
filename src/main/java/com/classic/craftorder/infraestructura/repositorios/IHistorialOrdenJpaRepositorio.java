package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.HistorialOrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHistorialOrdenJpaRepositorio
        extends JpaRepository<HistorialOrdenEntity, Long> {

    List<HistorialOrdenEntity> findByOrdenIdOrderByCreatedAtAsc(Long ordenId);

    @Query("SELECT h FROM HistorialOrdenEntity h " +
           "WHERE h.ordenId = :ordenId " +
           "AND h.tipoEvento = 'CAMBIO_ETAPA' " +
           "ORDER BY h.createdAt DESC")
    List<HistorialOrdenEntity> findUltimoCambioEtapa(
        @Param("ordenId") Long ordenId);
}
