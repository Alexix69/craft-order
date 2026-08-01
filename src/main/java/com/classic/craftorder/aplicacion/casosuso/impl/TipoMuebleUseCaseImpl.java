package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;

import java.util.List;
import java.util.NoSuchElementException;

public class TipoMuebleUseCaseImpl implements ITipoMuebleUseCase {

    private static final int TAMANIO_PAGINA = 20;

    private final ITipoMuebleRepositorio tipoMuebleRepositorio;

    public TipoMuebleUseCaseImpl(ITipoMuebleRepositorio tipoMuebleRepositorio) {
        this.tipoMuebleRepositorio = tipoMuebleRepositorio;
    }

    @Override
    public TipoMueble guardar(TipoMueble tipoMueble) {
        if (tipoMueble.getActivo() == null) {
            tipoMueble.setActivo(true);
        }
        return tipoMuebleRepositorio.guardar(tipoMueble);
    }

    @Override
    public TipoMueble actualizar(Long id, TipoMueble tipoMueble) {
        TipoMueble existente = tipoMuebleRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        tipoMueble.setId(id);
        tipoMueble.setActivo(existente.getActivo());
        tipoMueble.setCreatedAt(existente.getCreatedAt());
        return tipoMuebleRepositorio.actualizar(tipoMueble);
    }

    @Override
    public void desactivar(Long id) {
        tipoMuebleRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        tipoMuebleRepositorio.desactivar(id);
    }

    @Override
    public void activar(Long id) {
        tipoMuebleRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        tipoMuebleRepositorio.activar(id);
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

    @Override
    public PaginaResultado<TipoMueble> listarActivosPaginado(String nombre, int pagina) {
        return tipoMuebleRepositorio.listarActivosPaginado(nombre, pagina, TAMANIO_PAGINA);
    }

    @Override
    public PaginaResultado<TipoMueble> listarTodosPaginado(String nombre, int pagina) {
        return tipoMuebleRepositorio.listarTodosPaginado(nombre, pagina, TAMANIO_PAGINA);
    }
}
