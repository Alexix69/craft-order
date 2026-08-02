package com.classic.craftorder.aplicacion.casosuso.entrada;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Cotizacion;

public interface ICotizacionUseCase {

    Cotizacion calcularYPreparar(Cotizacion cotizacion);

    Cotizacion confirmar(Cotizacion cotizacion);

    Cotizacion buscarPorToken(String token);

    PaginaResultado<Cotizacion> listarPorEstadoPaginado(String estado, int pagina);
}
