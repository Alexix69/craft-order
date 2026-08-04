package com.classic.craftorder.infraestructura.configuracion;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class TipoMuebleSeedConfig {

    @Bean
    public CommandLineRunner seedTiposMueble(ITipoMuebleUseCase tipoMuebleUseCase,
                                              ITipoMuebleRepositorio tipoMuebleRepositorio) {
        return args -> {
            List<Object[]> datos = List.of(
                new Object[]{"Mesa de Comedor",
                    "Mesa artesanal de madera maciza para comedor familiar.",
                    "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=600",
                    320.00},
                new Object[]{"Silla Rústica",
                    "Silla de madera torneada a mano con acabado natural.",
                    "https://images.unsplash.com/photo-1506439773649-6e0eb8cfb237?w=600",
                    180.00},
                new Object[]{"Cama Matrimonial",
                    "Cama de madera sólida con cabecero tallado artesanalmente.",
                    "https://images.unsplash.com/photo-1540574163026-643ea20ade25?w=600",
                    650.00},
                new Object[]{"Estantería de Pared",
                    "Estantería flotante de madera para decoración y almacenamiento.",
                    "https://images.unsplash.com/photo-1524758631624-e2822e304c36?w=600",
                    210.00},
                new Object[]{"Escritorio de Trabajo",
                    "Escritorio amplio con cajones laterales y superficie lisa.",
                    "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e?w=600",
                    480.00},
                new Object[]{"Armario Ropero",
                    "Armario de dos puertas con espejo y espacio interior organizado.",
                    "https://images.unsplash.com/photo-1598928506311-c55ded91a20c?w=600",
                    820.00},
                new Object[]{"Sofá de Madera",
                    "Sofá con estructura de madera maciza y cojines tapizados.",
                    "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=600",
                    550.00},
                new Object[]{"Mesita de Noche",
                    "Mesita compacta con cajón y balda para dormitorio.",
                    "https://images.unsplash.com/photo-1567016432779-094069958ea5?w=600",
                    145.00},
                new Object[]{"Banco de Entrada",
                    "Banco de madera para recibidor con almacenamiento inferior.",
                    "https://images.unsplash.com/photo-1550581190-9c1c48d21d6c?w=600",
                    195.00},
                new Object[]{"Vitrina Exhibidora",
                    "Vitrina con puertas de vidrio y estructura de madera tallada.",
                    "https://images.unsplash.com/photo-1524758631624-e2822e304c36?w=600",
                    710.00},
                new Object[]{"Mesa de Centro",
                    "Mesa baja para sala con diseño minimalista en madera.",
                    "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e?w=600",
                    260.00},
                new Object[]{"Sillón Individual",
                    "Sillón de madera con asiento tapizado, ideal para lectura.",
                    "https://images.unsplash.com/photo-1506439773649-6e0eb8cfb237?w=600",
                    390.00},
                new Object[]{"Cómoda de Cajones",
                    "Cómoda de cinco cajones con manijas de madera torneada.",
                    "https://images.unsplash.com/photo-1598928506311-c55ded91a20c?w=600",
                    430.00},
                new Object[]{"Repisa Flotante",
                    "Repisa de madera maciza para colgar en pared, varios tamaños.",
                    "https://images.unsplash.com/photo-1567016432779-094069958ea5?w=600",
                    95.00},
                new Object[]{"Baúl de Madera",
                    "Baúl artesanal con herrajes metálicos, ideal para almacenamiento.",
                    "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=600",
                    280.00}
            );

            datos.forEach(d -> {
                String nombre = (String) d[0];
                boolean existe = tipoMuebleRepositorio.listarTodos()
                    .stream()
                    .anyMatch(t -> t.getNombre().equalsIgnoreCase(nombre));
                if (!existe) {
                    TipoMueble tm = new TipoMueble();
                    tm.setNombre(nombre);
                    tm.setDescripcion((String) d[1]);
                    tm.setFotoUrl((String) d[2]);
                    tm.setCostoBaseMo(new BigDecimal(d[3].toString()));
                    tm.setActivo(true);
                    tipoMuebleUseCase.guardar(tm);
                }
            });
        };
    }
}
