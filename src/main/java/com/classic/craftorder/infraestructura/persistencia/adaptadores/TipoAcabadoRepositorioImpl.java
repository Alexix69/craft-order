package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.TipoAcabado;
import com.classic.craftorder.dominio.repositorios.ITipoAcabadoRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.TipoAcabadoEntity;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoAcabadoJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.ITipoAcabadoJpaRepositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TipoAcabadoRepositorioImpl implements ITipoAcabadoRepositorio {

    private final ITipoAcabadoJpaRepositorio jpaRepositorio;
    private final ITipoAcabadoJpaMapper mapper;

    public TipoAcabadoRepositorioImpl(ITipoAcabadoJpaRepositorio jpaRepositorio, ITipoAcabadoJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public Optional<TipoAcabado> buscarPorTipo(String tipo) {
        return jpaRepositorio.findByTipo(tipo).map(mapper::toDominio);
    }

    @Override
    public List<TipoAcabado> listarTodos() {
        return jpaRepositorio.findAll().stream().map(mapper::toDominio).toList();
    }

    @Override
    public TipoAcabado actualizarPorcentaje(String tipo, BigDecimal porcentaje) {
        TipoAcabadoEntity entity = jpaRepositorio.findByTipo(tipo)
                .orElseThrow(() -> new NoSuchElementException("Tipo de acabado no encontrado: " + tipo));
        entity.setPorcentaje(porcentaje);
        return mapper.toDominio(jpaRepositorio.save(entity));
    }

    @Override
    public TipoAcabado guardar(TipoAcabado tipoAcabado) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(tipoAcabado)));
    }
}
