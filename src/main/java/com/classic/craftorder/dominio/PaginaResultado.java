package com.classic.craftorder.dominio;

import java.util.List;

public class PaginaResultado<T> {

    private List<T> contenido;
    private int paginaActual;
    private int totalPaginas;
    private long totalElementos;
    private boolean esPrimera;
    private boolean esUltima;

    public List<T> getContenido() {
        return contenido;
    }

    public void setContenido(List<T> contenido) {
        this.contenido = contenido;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public boolean isEsPrimera() {
        return esPrimera;
    }

    public void setEsPrimera(boolean esPrimera) {
        this.esPrimera = esPrimera;
    }

    public boolean isEsUltima() {
        return esUltima;
    }

    public void setEsUltima(boolean esUltima) {
        this.esUltima = esUltima;
    }
}
