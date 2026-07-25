package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;

import java.util.List;
import java.util.NoSuchElementException;

public class TipoMuebleUseCaseImpl implements ITipoMuebleUseCase {

    private final ITipoMuebleRepositorio tipoMuebleRepositorio;

    public TipoMuebleUseCaseImpl(ITipoMuebleRepositorio tipoMuebleRepositorio) {
        this.tipoMuebleRepositorio = tipoMuebleRepositorio;
    }

    @Override
    public TipoMueble guardar(TipoMueble tipoMueble) {
        return tipoMuebleRepositorio.guardar(tipoMueble);
    }

    @Override
    public TipoMueble actualizar(Long id, TipoMueble tipoMueble) {
        tipoMuebleRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        tipoMueble.setId(id);
        return tipoMuebleRepositorio.actualizar(tipoMueble);
    }

    @Override
    public void desactivar(Long id) {
        tipoMuebleRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        tipoMuebleRepositorio.desactivar(id);
    }

    @Override
    public TipoMueble buscarPorId(Long id) {
        return tipoMuebleRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
    }

    @Override
    public List<TipoMueble> listarTodos() {
        return tipoMuebleRepositorio.listarTodos();
    }

    @Override
    public List<TipoMueble> listarActivos() {
        return tipoMuebleRepositorio.listarActivos();
    }
}
