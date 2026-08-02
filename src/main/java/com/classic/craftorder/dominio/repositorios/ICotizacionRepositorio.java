package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Cotizacion;

import java.util.Optional;

public interface ICotizacionRepositorio {

    Cotizacion guardar(Cotizacion cotizacion);

    Optional<Cotizacion> buscarPorId(Long id);

    Optional<Cotizacion> buscarPorToken(String token);

    PaginaResultado<Cotizacion> listarPorEstadoPaginado(String estado, int pagina, int tamanio);
}
