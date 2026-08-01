package com.classic.craftorder.infraestructura.repositorios;

import com.classic.craftorder.infraestructura.persistencia.jpa.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUsuarioJpaRepositorio extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByCorreo(String correo);

    List<UsuarioEntity> findByRol(String rol);

    @Query("""
        SELECT u FROM UsuarioEntity u
        WHERE u.rol = :rol
        AND (
            :busqueda IS NULL
            OR (:campoBusqueda = 'nombre'
                AND LOWER(u.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')))
            OR (:campoBusqueda = 'correo'
                AND LOWER(u.correo) LIKE LOWER(CONCAT('%', :busqueda, '%')))
        )
        AND (:activo IS NULL OR u.activo = :activo)
        """)
    Page<UsuarioEntity> buscarPorFiltros(
            @Param("rol") String rol,
            @Param("busqueda") String busqueda,
            @Param("campoBusqueda") String campoBusqueda,
            @Param("activo") Boolean activo,
            Pageable pageable);
}
