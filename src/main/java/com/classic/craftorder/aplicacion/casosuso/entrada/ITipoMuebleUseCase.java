package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.entidades.TipoMueble;

import java.util.List;

public interface ITipoMuebleUseCase {

    TipoMueble guardar(TipoMueble tipoMueble);

    TipoMueble actualizar(Long id, TipoMueble tipoMueble);

    void desactivar(Long id);

    TipoMueble buscarPorId(Long id);

    List<TipoMueble> listarTodos();

    List<TipoMueble> listarActivos();
}
