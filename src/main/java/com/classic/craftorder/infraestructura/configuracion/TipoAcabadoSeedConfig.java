package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.dominio.entidades.TipoAcabado;
import com.classic.craftorder.dominio.repositorios.ITipoAcabadoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
public class TipoAcabadoSeedConfig {

    @Bean
    public CommandLineRunner seedTiposAcabado(ITipoAcabadoRepositorio tipoAcabadoRepositorio) {
        return args -> {
            Map<String, BigDecimal> defaults = Map.of(
                    "LACA", new BigDecimal("15.00"),
                    "PINTURA", new BigDecimal("10.00"),
                    "NATURAL", new BigDecimal("0.00")
            );
            defaults.forEach((tipo, porcentaje) -> {
                if (tipoAcabadoRepositorio.buscarPorTipo(tipo).isEmpty()) {
                    TipoAcabado tipoAcabado = new TipoAcabado();
                    tipoAcabado.setTipo(tipo);
                    tipoAcabado.setPorcentaje(porcentaje);
                    tipoAcabadoRepositorio.guardar(tipoAcabado);
                }
            });
        };
    }
}
