package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;

import java.util.List;
import java.util.NoSuchElementException;

public class MaterialUseCaseImpl implements IMaterialUseCase {

    private final IMaterialRepositorio materialRepositorio;

    public MaterialUseCaseImpl(IMaterialRepositorio materialRepositorio) {
        this.materialRepositorio = materialRepositorio;
    }

    @Override
    public Material guardar(Material material) {
        return materialRepositorio.guardar(material);
    }

    @Override
    public Material actualizar(Long id, Material material) {
        materialRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        material.setId(id);
        return materialRepositorio.actualizar(material);
    }

    @Override
    public void desactivar(Long id) {
        materialRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        materialRepositorio.desactivar(id);
    }

    @Override
    public Material buscarPorId(Long id) {
        return materialRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
    }

    @Override
    public List<Material> listarTodos() {
        return materialRepositorio.listarTodos();
    }

    @Override
    public List<Material> listarActivos() {
        return materialRepositorio.listarActivos();
    }
}
