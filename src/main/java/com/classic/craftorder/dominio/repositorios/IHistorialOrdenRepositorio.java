package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.HistorialOrden;

import java.util.List;
import java.util.Optional;

public interface IHistorialOrdenRepositorio {

    HistorialOrden guardar(HistorialOrden historial);
    List<HistorialOrden> listarPorOrden(Long ordenId);
    Optional<HistorialOrden> buscarUltimoCambioEtapa(Long ordenId);
}
