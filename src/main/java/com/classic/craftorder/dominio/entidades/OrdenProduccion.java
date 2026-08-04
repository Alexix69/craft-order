package com.classic.craftorder.dominio.entidades;

import java.time.OffsetDateTime;

public class OrdenProduccion {

    private Long id;
    private Long cotizacionId;
    private Long artesanoId;
    private String estadoActual;
    private OffsetDateTime fechaInicio;
    private OffsetDateTime fechaFinalizacion;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public OrdenProduccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(Long cotizacionId) {
        this.cotizacionId = cotizacionId;
    }

    public Long getArtesanoId() {
        return artesanoId;
    }

    public void setArtesanoId(Long artesanoId) {
        this.artesanoId = artesanoId;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public OffsetDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(OffsetDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
