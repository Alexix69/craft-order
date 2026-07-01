package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioUseCase {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> listarTodos();

    void eliminar(Long id);
}
