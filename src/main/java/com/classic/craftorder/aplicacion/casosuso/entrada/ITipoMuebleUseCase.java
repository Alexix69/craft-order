package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.TipoMueble;

import java.util.List;

public interface ITipoMuebleUseCase {

    TipoMueble guardar(TipoMueble tipoMueble);

    TipoMueble actualizar(Long id, TipoMueble tipoMueble);

    void desactivar(Long id);

    void activar(Long id);

    TipoMueble buscarPorId(Long id);

    List<TipoMueble> listarTodos();

    List<TipoMueble> listarActivos();

    PaginaResultado<TipoMueble> listarActivosPaginado(String nombre, int pagina);

    PaginaResultado<TipoMueble> listarTodosPaginado(String nombre, int pagina);
}
