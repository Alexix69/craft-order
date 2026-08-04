package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.UsuarioEntity;
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
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return jpaRepositorio.findByCorreo(correo).map(mapper::toDominio);
    }

    @Override
    public List<Usuario> listarPorRol(String rol) {
        return jpaRepositorio.findByRol(rol).stream().map(mapper::toDominio).toList();
    }

    @Override
    public void desactivar(Long id) {
        UsuarioEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        entity.setActivo(false);
        jpaRepositorio.save(entity);
    }

    @Override
    public void activar(Long id) {
        UsuarioEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        entity.setActivo(true);
        jpaRepositorio.save(entity);
    }

    @Override
    public void resetearContrasena(Long id, String contrasenaNuevaHash) {
        UsuarioEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        entity.setContrasena(contrasenaNuevaHash);
        entity.setPrimerLogin(true);
        jpaRepositorio.save(entity);
    }
}
