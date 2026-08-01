package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepositorio {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorCorreo(String correo);

    List<Usuario> listarPorRol(String rol);

    void desactivar(Long id);

    void activar(Long id);

    void resetearContrasena(Long id, String contrasenaNuevaHash);

    PaginaResultado<Usuario> listarPorRolPaginado(
            String rol, String busqueda, String campoBusqueda,
            Boolean activo, int pagina, int tamanio);
}
