package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Material;

import java.util.List;

public interface IMaterialUseCase {

    Material guardar(Material material);

    Material actualizar(Long id, Material material);

    void desactivar(Long id);

    void activar(Long id);

    Material buscarPorId(Long id);

    List<Material> listarTodos();

    List<Material> listarActivos();

    PaginaResultado<Material> listarActivosPaginado(String nombre, int pagina);

    PaginaResultado<Material> listarTodosPaginado(String nombre, int pagina);
}
