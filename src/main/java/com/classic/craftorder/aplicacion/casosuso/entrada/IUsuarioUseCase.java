package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.entidades.Usuario;

import java.util.List;

public interface IUsuarioUseCase {

    Usuario guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario buscarPorCorreo(String correo);

    List<Usuario> listarPorRol(String rol);

    void desactivar(Long id);

    void activar(Long id);

    String resetearContrasena(Long id);

    void cambiarContrasena(Long id, String contrasenaNueva);
}
