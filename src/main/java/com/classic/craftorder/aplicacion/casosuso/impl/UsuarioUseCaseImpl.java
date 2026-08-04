package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;

import java.util.List;
import java.util.Optional;

public class UsuarioUseCaseImpl implements IUsuarioUseCase {

    private final IUsuarioRepositorio usuarioRepositorio;

    public UsuarioUseCaseImpl(IUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepositorio.guardar(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepositorio.buscarPorId(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepositorio.listarTodos();
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepositorio.eliminar(id);
    }
}
