package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

public class UsuarioUseCaseImpl implements IUsuarioUseCase {

    private static final int TAMANIO_PAGINA = 20;
    private static final String ROL_ARTESANO = "ARTESANO";
    private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String LETRAS_MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITOS = "0123456789";

    private final IUsuarioRepositorio usuarioRepositorio;
    private final BCryptPasswordEncoder encoder;
    private final SecureRandom random = new SecureRandom();

    public UsuarioUseCaseImpl(IUsuarioRepositorio usuarioRepositorio, BCryptPasswordEncoder encoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.encoder = encoder;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario.getId() == null) {
            if (usuario.getPrimerLogin() == null) {
                usuario.setPrimerLogin(true);
            }
            if (usuario.getActivo() == null) {
                usuario.setActivo(true);
            }
        }
        usuario.setContrasena(encoder.encode(usuario.getContrasena()));
        return usuarioRepositorio.guardar(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepositorio.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

    @Override
    public List<Usuario> listarPorRol(String rol) {
        return usuarioRepositorio.listarPorRol(rol);
    }

    @Override
    public void desactivar(Long id) {
        usuarioRepositorio.desactivar(id);
    }

    @Override
    public void activar(Long id) {
        usuarioRepositorio.activar(id);
    }

    @Override
    public String resetearContrasena(Long id) {
        String temporal = generarContrasenaTemporal();
        usuarioRepositorio.resetearContrasena(id, encoder.encode(temporal));
        return temporal;
    }

    @Override
    public void cambiarContrasena(Long id, String contrasenaNueva) {
        Usuario usuario = buscarPorId(id);
        usuario.setContrasena(encoder.encode(contrasenaNueva));
        usuario.setPrimerLogin(false);
        usuarioRepositorio.guardar(usuario);
    }

    @Override
    public PaginaResultado<Usuario> listarArtesanosPaginado(
            String busqueda, String campoBusqueda, Boolean activo, int pagina) {
        return usuarioRepositorio.listarPorRolPaginado(
                ROL_ARTESANO, busqueda, campoBusqueda, activo, pagina, TAMANIO_PAGINA);
    }

    private String generarContrasenaTemporal() {
        int longitud = 10;
        StringBuilder temporal = new StringBuilder();
        temporal.append(LETRAS_MAYUSCULAS.charAt(random.nextInt(LETRAS_MAYUSCULAS.length())));
        for (int i = 1; i < longitud - 1; i++) {
            temporal.append(LETRAS_MINUSCULAS.charAt(random.nextInt(LETRAS_MINUSCULAS.length())));
        }
        temporal.append(DIGITOS.charAt(random.nextInt(DIGITOS.length())));
        return temporal.toString();
    }
}
