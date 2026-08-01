package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;

import java.util.List;
import java.util.NoSuchElementException;

public class MaterialUseCaseImpl implements IMaterialUseCase {

    private static final int TAMANIO_PAGINA = 20;

    private final IMaterialRepositorio materialRepositorio;

    public MaterialUseCaseImpl(IMaterialRepositorio materialRepositorio) {
        this.materialRepositorio = materialRepositorio;
    }

    @Override
    public Material guardar(Material material) {
        if (material.getActivo() == null) {
            material.setActivo(true);
        }
        return materialRepositorio.guardar(material);
    }

    @Override
    public Material actualizar(Long id, Material material) {
        Material existente = materialRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        material.setId(id);
        material.setActivo(existente.getActivo());
        material.setCreatedAt(existente.getCreatedAt());
        return materialRepositorio.actualizar(material);
    }

    @Override
    public void desactivar(Long id) {
        materialRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        materialRepositorio.desactivar(id);
    }

    @Override
    public void activar(Long id) {
        materialRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        materialRepositorio.activar(id);
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

    @Override
    public PaginaResultado<Material> listarActivosPaginado(String nombre, int pagina) {
        return materialRepositorio.listarActivosPaginado(nombre, pagina, TAMANIO_PAGINA);
    }

    @Override
    public PaginaResultado<Material> listarTodosPaginado(String nombre, int pagina) {
        return materialRepositorio.listarTodosPaginado(nombre, pagina, TAMANIO_PAGINA);
    }
}
