package com.classic.craftorder.dominio.entidades;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Material {

    private Long id;
    private String nombre;
    private BigDecimal precioPorM3;
    private Boolean activo;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Material() {
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

    public BigDecimal getPrecioPorM3() {
        return precioPorM3;
    }

    public void setPrecioPorM3(BigDecimal precioPorM3) {
        this.precioPorM3 = precioPorM3;
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
