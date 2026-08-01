package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.TipoMueble;

import java.util.List;
import java.util.Optional;

public interface ITipoMuebleRepositorio {

    TipoMueble guardar(TipoMueble tipoMueble);

    TipoMueble actualizar(TipoMueble tipoMueble);

    void desactivar(Long id);

    void activar(Long id);

    Optional<TipoMueble> buscarPorId(Long id);

    List<TipoMueble> listarTodos();

    List<TipoMueble> listarActivos();

    PaginaResultado<TipoMueble> listarActivosPaginado(String nombre, int pagina, int tamanio);

    PaginaResultado<TipoMueble> listarTodosPaginado(String nombre, int pagina, int tamanio);
}
