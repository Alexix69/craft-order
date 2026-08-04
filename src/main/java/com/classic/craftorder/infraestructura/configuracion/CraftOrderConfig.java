package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.aplicacion.casosuso.impl.UsuarioUseCaseImpl;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.UsuarioRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IUsuarioJpaRepositorio;
import com.classic.craftorder.presentacion.mapeadores.IUsuarioDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CraftOrderConfig {

    // ---- Usuario ----

    @Bean
    public IUsuarioJpaMapper usuarioJpaMapper() {
        return Mappers.getMapper(IUsuarioJpaMapper.class);
    }

    @Bean
    public IUsuarioDtoMapper usuarioDtoMapper() {
        return Mappers.getMapper(IUsuarioDtoMapper.class);
    }

    @Bean
    public IUsuarioRepositorio usuarioRepositorio(IUsuarioJpaRepositorio jpaRepositorio,
                                                   IUsuarioJpaMapper mapper) {
        return new UsuarioRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    public IUsuarioUseCase usuarioUseCase(IUsuarioRepositorio repositorio) {
        return new UsuarioUseCaseImpl(repositorio);
    }
}
