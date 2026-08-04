package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.TipoMuebleEntity;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoMuebleJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.ITipoMuebleJpaRepositorio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TipoMuebleRepositorioImpl implements ITipoMuebleRepositorio {

    private final ITipoMuebleJpaRepositorio jpaRepositorio;
    private final ITipoMuebleJpaMapper mapper;

    public TipoMuebleRepositorioImpl(ITipoMuebleJpaRepositorio jpaRepositorio, ITipoMuebleJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public TipoMueble guardar(TipoMueble tipoMueble) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(tipoMueble)));
    }

    @Override
    public TipoMueble actualizar(TipoMueble tipoMueble) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(tipoMueble)));
    }

    @Override
    public void desactivar(Long id) {
        TipoMuebleEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        entity.setActivo(false);
        jpaRepositorio.save(entity);
    }

    @Override
    public Optional<TipoMueble> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public List<TipoMueble> listarTodos() {
        return jpaRepositorio.findAll().stream().map(mapper::toDominio).toList();
    }

    @Override
    public List<TipoMueble> listarActivos() {
        return jpaRepositorio.findByActivoTrue().stream().map(mapper::toDominio).toList();
    }
}
