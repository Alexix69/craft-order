package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class MaterialSeedConfig {

    @Bean
    public CommandLineRunner seedMateriales(IMaterialUseCase materialUseCase,
                                             IMaterialRepositorio materialRepositorio) {
        return args -> {
            List<Object[]> datos = List.of(
                new Object[]{"Roble",     850.00},
                new Object[]{"Cedro",     620.00},
                new Object[]{"Pino",      380.00},
                new Object[]{"Nogal",    1200.00},
                new Object[]{"Caoba",     950.00},
                new Object[]{"Eucalipto", 420.00},
                new Object[]{"Laurel",    530.00},
                new Object[]{"Teca",     1450.00}
            );

            datos.forEach(d -> {
                String nombre = (String) d[0];
                boolean existe = materialRepositorio.listarTodos()
                    .stream()
                    .anyMatch(m -> m.getNombre().equalsIgnoreCase(nombre));
                if (!existe) {
                    Material mat = new Material();
                    mat.setNombre(nombre);
                    mat.setPrecioPorM3(new BigDecimal(d[1].toString()));
                    mat.setActivo(true);
                    materialUseCase.guardar(mat);
                }
            });
        };
    }
}
