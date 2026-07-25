package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.Material;

import java.util.List;
import java.util.Optional;

public interface IMaterialRepositorio {

    Material guardar(Material material);

    Material actualizar(Material material);

    void desactivar(Long id);

    Optional<Material> buscarPorId(Long id);

    List<Material> listarTodos();

    List<Material> listarActivos();
}
