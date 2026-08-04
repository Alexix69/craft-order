package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.HistorialOrden;
import com.classic.craftorder.dominio.repositorios.IHistorialOrdenRepositorio;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IHistorialOrdenJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IHistorialOrdenJpaRepositorio;

import java.util.List;
import java.util.Optional;

public class HistorialOrdenRepositorioImpl implements IHistorialOrdenRepositorio {

    private final IHistorialOrdenJpaRepositorio jpaRepositorio;
    private final IHistorialOrdenJpaMapper mapper;

    public HistorialOrdenRepositorioImpl(IHistorialOrdenJpaRepositorio jpaRepositorio,
                                          IHistorialOrdenJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public HistorialOrden guardar(HistorialOrden historial) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(historial)));
    }

    @Override
    public List<HistorialOrden> listarPorOrden(Long ordenId) {
        return jpaRepositorio.findByOrdenIdOrderByCreatedAtAsc(ordenId)
                .stream().map(mapper::toDominio).toList();
    }

    @Override
    public Optional<HistorialOrden> buscarUltimoCambioEtapa(Long ordenId) {
        return jpaRepositorio.findUltimoCambioEtapa(ordenId)
                .stream().findFirst().map(mapper::toDominio);
    }
}
