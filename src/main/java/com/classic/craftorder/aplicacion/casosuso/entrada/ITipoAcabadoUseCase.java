package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.entidades.TipoAcabado;

import java.math.BigDecimal;
import java.util.List;

public interface ITipoAcabadoUseCase {

    List<TipoAcabado> listarTodos();

    TipoAcabado actualizarPorcentaje(String tipo, BigDecimal porcentaje);
}
