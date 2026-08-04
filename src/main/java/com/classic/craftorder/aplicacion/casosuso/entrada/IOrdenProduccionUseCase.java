package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.entidades.HistorialOrden;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;

import java.util.List;

public interface IOrdenProduccionUseCase {

    OrdenProduccion asignar(Long cotizacionId, Long artesanoId,
                             Long realizadoPor);
    OrdenProduccion cambiarEstado(Long ordenId, String estadoNuevo,
                                   String motivo, Long realizadoPor);
    OrdenProduccion reasignarArtesano(Long ordenId, Long nuevoArtesanoId,
                                       String motivo, Long realizadoPor);
    OrdenProduccion buscarPorId(Long id);
    OrdenProduccion buscarPorCotizacionId(Long cotizacionId);
    List<OrdenProduccion> listarActivas();
    List<OrdenProduccion> listarPorArtesano(Long artesanoId);
    List<HistorialOrden> listarHistorial(Long ordenId);
}
