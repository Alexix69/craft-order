package com.classic.craftorder.dominio.entidades;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TipoMueble {

    private Long id;
    private String nombre;
    private String descripcion;
    private String fotoUrl;
    private BigDecimal costoBaseMo;
    private Boolean activo;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public TipoMueble() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public BigDecimal getCostoBaseMo() {
        return costoBaseMo;
    }

    public void setCostoBaseMo(BigDecimal costoBaseMo) {
        this.costoBaseMo = costoBaseMo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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
