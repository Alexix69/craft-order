package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.Factura;
import com.classic.craftorder.dominio.repositorios.IFacturaRepositorio;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IFacturaJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IFacturaJpaRepositorio;

import java.util.Optional;

public class FacturaRepositorioImpl implements IFacturaRepositorio {

    private final IFacturaJpaRepositorio jpaRepositorio;
    private final IFacturaJpaMapper mapper;

    public FacturaRepositorioImpl(IFacturaJpaRepositorio jpaRepositorio,
                                   IFacturaJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public Factura guardar(Factura factura) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(factura)));
    }

    @Override
    public Optional<Factura> buscarPorOrdenId(Long ordenId) {
        return jpaRepositorio.findByOrdenId(ordenId).map(mapper::toDominio);
    }

    @Override
    public int obtenerUltimoNumero() {
        return jpaRepositorio.findMaxNumeroFactura();
    }
}
