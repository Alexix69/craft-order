package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepositorio {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> listarTodos();

    void eliminar(Long id);
}
