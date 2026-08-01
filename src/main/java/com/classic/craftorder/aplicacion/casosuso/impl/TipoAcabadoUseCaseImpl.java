package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoAcabadoUseCase;
import com.classic.craftorder.dominio.entidades.TipoAcabado;
import com.classic.craftorder.dominio.repositorios.ITipoAcabadoRepositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class TipoAcabadoUseCaseImpl implements ITipoAcabadoUseCase {

    private static final Set<String> TIPOS_VALIDOS = Set.of("LACA", "PINTURA", "NATURAL");

    private final ITipoAcabadoRepositorio tipoAcabadoRepositorio;

    public TipoAcabadoUseCaseImpl(ITipoAcabadoRepositorio tipoAcabadoRepositorio) {
        this.tipoAcabadoRepositorio = tipoAcabadoRepositorio;
    }

    @Override
    public List<TipoAcabado> listarTodos() {
        return tipoAcabadoRepositorio.listarTodos();
    }

    @Override
    public TipoAcabado actualizarPorcentaje(String tipo, BigDecimal porcentaje) {
        if (!TIPOS_VALIDOS.contains(tipo)) {
            throw new RuntimeException("Tipo de acabado inválido: " + tipo);
        }
        return tipoAcabadoRepositorio.actualizarPorcentaje(tipo, porcentaje);
    }
}
