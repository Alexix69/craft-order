package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.aplicacion.casosuso.entrada.ICotizacionUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoAcabadoUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.aplicacion.casosuso.impl.CotizacionUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.MaterialUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.TipoAcabadoUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.TipoMuebleUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.UsuarioUseCaseImpl;
import com.classic.craftorder.dominio.repositorios.ICotizacionRepositorio;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoAcabadoRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.CotizacionRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.MaterialRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.TipoAcabadoRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.TipoMuebleRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.UsuarioRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ICotizacionJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IMaterialJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoAcabadoJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoMuebleJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.ICotizacionJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IMaterialJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.ITipoAcabadoJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.ITipoMuebleJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IUsuarioJpaRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CraftOrderConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUsuarioRepositorio usuarioRepositorio(IUsuarioJpaRepositorio jpaRepositorio,
                                                   IUsuarioJpaMapper mapper) {
        return new UsuarioRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    public IUsuarioUseCase usuarioUseCase(IUsuarioRepositorio repositorio, BCryptPasswordEncoder encoder) {
        return new UsuarioUseCaseImpl(repositorio, encoder);
    }

    @Bean
    public ITipoMuebleRepositorio tipoMuebleRepositorio(ITipoMuebleJpaRepositorio jpaRepositorio,
                                                         ITipoMuebleJpaMapper mapper) {
        return new TipoMuebleRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    public ITipoMuebleUseCase tipoMuebleUseCase(ITipoMuebleRepositorio repositorio) {
        return new TipoMuebleUseCaseImpl(repositorio);
    }

    @Bean
    public IMaterialRepositorio materialRepositorio(IMaterialJpaRepositorio jpaRepositorio,
                                                     IMaterialJpaMapper mapper) {
        return new MaterialRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    public IMaterialUseCase materialUseCase(IMaterialRepositorio repositorio) {
        return new MaterialUseCaseImpl(repositorio);
    }

    @Bean
    public ITipoAcabadoRepositorio tipoAcabadoRepositorio(ITipoAcabadoJpaRepositorio jpaRepositorio,
                                                           ITipoAcabadoJpaMapper mapper) {
        return new TipoAcabadoRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    public ITipoAcabadoUseCase tipoAcabadoUseCase(ITipoAcabadoRepositorio repositorio) {
        return new TipoAcabadoUseCaseImpl(repositorio);
    }

    @Bean
    public ICotizacionRepositorio cotizacionRepositorio(
            ICotizacionJpaRepositorio jpaRepositorio,
            ICotizacionJpaMapper mapper) {
        return new CotizacionRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    public ICotizacionUseCase cotizacionUseCase(
            ICotizacionRepositorio cotizacionRepositorio,
            ITipoMuebleRepositorio tipoMuebleRepositorio,
            IMaterialRepositorio materialRepositorio,
            ITipoAcabadoRepositorio tipoAcabadoRepositorio) {
        return new CotizacionUseCaseImpl(
                cotizacionRepositorio,
                tipoMuebleRepositorio,
                materialRepositorio,
                tipoAcabadoRepositorio);
    }
}
