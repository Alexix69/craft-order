package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.TipoMueble;

import java.util.List;
import java.util.Optional;

public interface ITipoMuebleRepositorio {

    TipoMueble guardar(TipoMueble tipoMueble);

    TipoMueble actualizar(TipoMueble tipoMueble);

    void desactivar(Long id);

    Optional<TipoMueble> buscarPorId(Long id);

    List<TipoMueble> listarTodos();

    List<TipoMueble> listarActivos();
}
