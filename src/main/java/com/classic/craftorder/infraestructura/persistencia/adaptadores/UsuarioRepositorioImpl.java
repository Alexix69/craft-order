package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IUsuarioJpaRepositorio;

import java.util.List;
import java.util.Optional;

public class UsuarioRepositorioImpl implements IUsuarioRepositorio {

    private final IUsuarioJpaRepositorio jpaRepositorio;
    private final IUsuarioJpaMapper mapper;

    public UsuarioRepositorioImpl(IUsuarioJpaRepositorio jpaRepositorio, IUsuarioJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return mapper.aDominio(jpaRepositorio.save(mapper.aEntity(usuario)));
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::aDominio);
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepositorio.findAll().stream().map(mapper::aDominio).toList();
    }

    @Override
    public void eliminar(Long id) {
        jpaRepositorio.deleteById(id);
    }
}
