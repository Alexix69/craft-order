package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.MaterialEntity;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IMaterialJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IMaterialJpaRepositorio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MaterialRepositorioImpl implements IMaterialRepositorio {

    private final IMaterialJpaRepositorio jpaRepositorio;
    private final IMaterialJpaMapper mapper;

    public MaterialRepositorioImpl(IMaterialJpaRepositorio jpaRepositorio, IMaterialJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public Material guardar(Material material) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(material)));
    }

    @Override
    public Material actualizar(Material material) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(material)));
    }

    @Override
    public void desactivar(Long id) {
        MaterialEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        entity.setActivo(false);
        jpaRepositorio.save(entity);
    }

    @Override
    public Optional<Material> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public List<Material> listarTodos() {
        return jpaRepositorio.findAll().stream().map(mapper::toDominio).toList();
    }

    @Override
    public List<Material> listarActivos() {
        return jpaRepositorio.findByActivoTrue().stream().map(mapper::toDominio).toList();
    }
}
