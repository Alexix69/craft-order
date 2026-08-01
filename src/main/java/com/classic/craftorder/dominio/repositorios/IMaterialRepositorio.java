package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Material;

import java.util.List;
import java.util.Optional;

public interface IMaterialRepositorio {

    Material guardar(Material material);

    Material actualizar(Material material);

    void desactivar(Long id);

    void activar(Long id);

    Optional<Material> buscarPorId(Long id);

    List<Material> listarTodos();

    List<Material> listarActivos();

    PaginaResultado<Material> listarActivosPaginado(String nombre, int pagina, int tamanio);

    PaginaResultado<Material> listarTodosPaginado(String nombre, int pagina, int tamanio);
}
