package com.classic.craftorder.dominio.entidades;

import java.time.OffsetDateTime;

public class HistorialOrden {

    private Long id;
    private Long ordenId;
    private String tipoEvento;
    private String valorAnterior;
    private String valorNuevo;
    private String motivo;
    private Long realizadoPor;
    private OffsetDateTime createdAt;

    public HistorialOrden() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(Long realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
