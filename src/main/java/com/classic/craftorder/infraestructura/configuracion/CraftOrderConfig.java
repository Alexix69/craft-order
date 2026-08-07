package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.aplicacion.casosuso.entrada.ICotizacionUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IOrdenProduccionUseCase;
import com.classic.craftorder.aplicacion.servicios.ArchivoService;
import com.classic.craftorder.aplicacion.servicios.CorreoService;
import com.classic.craftorder.aplicacion.servicios.FacturaPdfService;
import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoAcabadoUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.aplicacion.casosuso.impl.CotizacionUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.MaterialUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.OrdenProduccionUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.TipoAcabadoUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.TipoMuebleUseCaseImpl;
import com.classic.craftorder.aplicacion.casosuso.impl.UsuarioUseCaseImpl;
import com.classic.craftorder.dominio.repositorios.ICotizacionRepositorio;
import com.classic.craftorder.dominio.repositorios.IFacturaRepositorio;
import com.classic.craftorder.dominio.repositorios.IHistorialOrdenRepositorio;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import com.classic.craftorder.dominio.repositorios.IOrdenProduccionRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoAcabadoRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.CotizacionRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.FacturaRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.HistorialOrdenRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.MaterialRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.OrdenProduccionRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.TipoAcabadoRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.TipoMuebleRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.adaptadores.UsuarioRepositorioImpl;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ICotizacionJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IFacturaJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IHistorialOrdenJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IMaterialJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IOrdenProduccionJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoAcabadoJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoMuebleJpaMapper;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.ICotizacionJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IFacturaJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IHistorialOrdenJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IMaterialJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IOrdenProduccionJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.ITipoAcabadoJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.ITipoMuebleJpaRepositorio;
import com.classic.craftorder.infraestructura.repositorios.IUsuarioJpaRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CraftOrderConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    IUsuarioRepositorio usuarioRepositorio(IUsuarioJpaRepositorio jpaRepositorio,
            IUsuarioJpaMapper mapper) {
        return new UsuarioRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    IUsuarioUseCase usuarioUseCase(
            IUsuarioRepositorio repositorio,
            BCryptPasswordEncoder encoder,
            IOrdenProduccionRepositorio ordenProduccionRepositorio) {
        return new UsuarioUseCaseImpl(repositorio, encoder,
                ordenProduccionRepositorio);
    }

    @Bean
    ITipoMuebleRepositorio tipoMuebleRepositorio(ITipoMuebleJpaRepositorio jpaRepositorio,
            ITipoMuebleJpaMapper mapper) {
        return new TipoMuebleRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    ITipoMuebleUseCase tipoMuebleUseCase(ITipoMuebleRepositorio repositorio) {
        return new TipoMuebleUseCaseImpl(repositorio);
    }

    @Bean
    IMaterialRepositorio materialRepositorio(IMaterialJpaRepositorio jpaRepositorio,
            IMaterialJpaMapper mapper) {
        return new MaterialRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    IMaterialUseCase materialUseCase(IMaterialRepositorio repositorio) {
        return new MaterialUseCaseImpl(repositorio);
    }

    @Bean
    ITipoAcabadoRepositorio tipoAcabadoRepositorio(ITipoAcabadoJpaRepositorio jpaRepositorio,
            ITipoAcabadoJpaMapper mapper) {
        return new TipoAcabadoRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    ITipoAcabadoUseCase tipoAcabadoUseCase(ITipoAcabadoRepositorio repositorio) {
        return new TipoAcabadoUseCaseImpl(repositorio);
    }

    @Bean
    ICotizacionRepositorio cotizacionRepositorio(
            ICotizacionJpaRepositorio jpaRepositorio,
            ICotizacionJpaMapper mapper) {
        return new CotizacionRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    ICotizacionUseCase cotizacionUseCase(
            ICotizacionRepositorio cotizacionRepositorio,
            ITipoMuebleRepositorio tipoMuebleRepositorio,
            IMaterialRepositorio materialRepositorio,
            ITipoAcabadoRepositorio tipoAcabadoRepositorio,
            CorreoService correoService) {
        return new CotizacionUseCaseImpl(
                cotizacionRepositorio,
                tipoMuebleRepositorio,
                materialRepositorio,
                tipoAcabadoRepositorio,
                correoService);
    }

    @Bean
    IOrdenProduccionRepositorio ordenProduccionRepositorio(
            IOrdenProduccionJpaRepositorio jpaRepositorio,
            IOrdenProduccionJpaMapper mapper) {
        return new OrdenProduccionRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    IHistorialOrdenRepositorio historialOrdenRepositorio(
            IHistorialOrdenJpaRepositorio jpaRepositorio,
            IHistorialOrdenJpaMapper mapper) {
        return new HistorialOrdenRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    IFacturaRepositorio facturaRepositorio(
            IFacturaJpaRepositorio jpaRepositorio,
            IFacturaJpaMapper mapper) {
        return new FacturaRepositorioImpl(jpaRepositorio, mapper);
    }

    @Bean
    IOrdenProduccionUseCase ordenProduccionUseCase(
            IOrdenProduccionRepositorio ordenRepositorio,
            IHistorialOrdenRepositorio historialRepositorio,
            IFacturaRepositorio facturaRepositorio,
            ICotizacionRepositorio cotizacionRepositorio,
            ITipoMuebleRepositorio tipoMuebleRepositorio,
            IMaterialRepositorio materialRepositorio,
            IUsuarioRepositorio usuarioRepositorio,
            FacturaPdfService facturaPdfService,
            CorreoService correoService,
            ArchivoService archivoService) {
        return new OrdenProduccionUseCaseImpl(
                ordenRepositorio,
                historialRepositorio,
                facturaRepositorio,
                cotizacionRepositorio,
                tipoMuebleRepositorio,
                materialRepositorio,
                usuarioRepositorio,
                facturaPdfService,
                correoService,
                archivoService);
    }
}
