package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.TipoAcabado;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ITipoAcabadoRepositorio {

    Optional<TipoAcabado> buscarPorTipo(String tipo);

    List<TipoAcabado> listarTodos();

    TipoAcabado actualizarPorcentaje(String tipo, BigDecimal porcentaje);

    TipoAcabado guardar(TipoAcabado tipoAcabado);
}
