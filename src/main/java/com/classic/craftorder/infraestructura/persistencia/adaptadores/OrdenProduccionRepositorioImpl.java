package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.dominio.repositorios.IOrdenProduccionRepositorio;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IOrdenProduccionJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IOrdenProduccionJpaRepositorio;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class OrdenProduccionRepositorioImpl implements IOrdenProduccionRepositorio {

    private final IOrdenProduccionJpaRepositorio jpaRepositorio;
    private final IOrdenProduccionJpaMapper mapper;

    public OrdenProduccionRepositorioImpl(IOrdenProduccionJpaRepositorio jpaRepositorio,
                                           IOrdenProduccionJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public OrdenProduccion guardar(OrdenProduccion orden) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(orden)));
    }

    @Override
    public Optional<OrdenProduccion> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public Optional<OrdenProduccion> buscarPorCotizacionId(Long cotizacionId) {
        return jpaRepositorio.findByCotizacionId(cotizacionId).map(mapper::toDominio);
    }

    @Override
    public List<OrdenProduccion> listarActivas() {
        OffsetDateTime limite = OffsetDateTime.now().minusHours(72);
        return jpaRepositorio.findAllActivas(limite)
            .stream()
            .limit(50)
            .map(mapper::toDominio)
            .toList();
    }

    @Override
    public List<OrdenProduccion> listarPorArtesano(Long artesanoId) {
        return jpaRepositorio.findByArtesanoId(artesanoId).stream().map(mapper::toDominio).toList();
    }

    @Override
    public List<OrdenProduccion> listarPorEstado(String estado) {
        return jpaRepositorio.findByEstadoActual(estado).stream().map(mapper::toDominio).toList();
    }
}
