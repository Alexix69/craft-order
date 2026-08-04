package com.classic.craftorder.dominio.entidades;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Cotizacion {

    private Long id;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private Long tipoMuebleId;
    private Long materialId;
    private String tipoAcabado;
    private BigDecimal altoCm;
    private BigDecimal anchoCm;
    private BigDecimal profundidadCm;
    private BigDecimal precioMaterialSnap;
    private BigDecimal costoBaseMoSnap;
    private BigDecimal porcentajeAcabadoSnap;
    private BigDecimal costoEstimado;
    private BigDecimal costoAprobado;
    private String estado;
    private String motivoRechazo;
    private String token;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Cotizacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public Long getTipoMuebleId() {
        return tipoMuebleId;
    }

    public void setTipoMuebleId(Long tipoMuebleId) {
        this.tipoMuebleId = tipoMuebleId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getTipoAcabado() {
        return tipoAcabado;
    }

    public void setTipoAcabado(String tipoAcabado) {
        this.tipoAcabado = tipoAcabado;
    }

    public BigDecimal getAltoCm() {
        return altoCm;
    }

    public void setAltoCm(BigDecimal altoCm) {
        this.altoCm = altoCm;
    }

    public BigDecimal getAnchoCm() {
        return anchoCm;
    }

    public void setAnchoCm(BigDecimal anchoCm) {
        this.anchoCm = anchoCm;
    }

    public BigDecimal getProfundidadCm() {
        return profundidadCm;
    }

    public void setProfundidadCm(BigDecimal profundidadCm) {
        this.profundidadCm = profundidadCm;
    }

    public BigDecimal getPrecioMaterialSnap() {
        return precioMaterialSnap;
    }

    public void setPrecioMaterialSnap(BigDecimal precioMaterialSnap) {
        this.precioMaterialSnap = precioMaterialSnap;
    }

    public BigDecimal getCostoBaseMoSnap() {
        return costoBaseMoSnap;
    }

    public void setCostoBaseMoSnap(BigDecimal costoBaseMoSnap) {
        this.costoBaseMoSnap = costoBaseMoSnap;
    }

    public BigDecimal getPorcentajeAcabadoSnap() {
        return porcentajeAcabadoSnap;
    }

    public void setPorcentajeAcabadoSnap(BigDecimal porcentajeAcabadoSnap) {
        this.porcentajeAcabadoSnap = porcentajeAcabadoSnap;
    }

    public BigDecimal getCostoEstimado() {
        return costoEstimado;
    }

    public void setCostoEstimado(BigDecimal costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public BigDecimal getCostoAprobado() {
        return costoAprobado;
    }

    public void setCostoAprobado(BigDecimal costoAprobado) {
        this.costoAprobado = costoAprobado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
