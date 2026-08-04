package com.classic.craftorder.dominio.repositorios;

import com.classic.craftorder.dominio.entidades.Factura;

import java.util.Optional;

public interface IFacturaRepositorio {

    Factura guardar(Factura factura);
    Optional<Factura> buscarPorOrdenId(Long ordenId);
    int obtenerUltimoNumero();
}
