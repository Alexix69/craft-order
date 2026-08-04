package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.OrdenProduccion;

import java.util.List;
import java.util.Optional;

public interface IOrdenProduccionRepositorio {

    OrdenProduccion guardar(OrdenProduccion orden);
    Optional<OrdenProduccion> buscarPorId(Long id);
    Optional<OrdenProduccion> buscarPorCotizacionId(Long cotizacionId);
    List<OrdenProduccion> listarActivas();
    List<OrdenProduccion> listarPorArtesano(Long artesanoId);
    List<OrdenProduccion> listarPorEstado(String estado);
}
