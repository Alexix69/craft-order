package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.entidades.Usuario;

import java.util.List;

public interface IUsuarioUseCase {

    Usuario guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    List<Usuario> listarTodos();

    void eliminar(Long id);
}
