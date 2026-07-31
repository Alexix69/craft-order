package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.entidades.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminSeedConfig {

    @Bean
    public CommandLineRunner seedAdmin(IUsuarioUseCase usuarioUseCase) {
        return args -> {
            try {
                usuarioUseCase.buscarPorCorreo("admin@classic.com");
            } catch (RuntimeException e) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setCorreo("admin@classic.com");
                admin.setContrasena("Admin1234");
                admin.setRol("ADMIN");
                admin.setActivo(true);
                admin.setPrimerLogin(false);
                usuarioUseCase.guardar(admin);
            }
        };
    }
}
