package com.classic.craftorder.dominio.entidades;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Factura {

    private Long id;
    private Long ordenId;
    private String numeroFactura;
    private String descripcionMueble;
    private BigDecimal montoTotal;
    private String pdfUrl;
    private OffsetDateTime createdAt;

    public Factura() {
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

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getDescripcionMueble() {
        return descripcionMueble;
    }

    public void setDescripcionMueble(String descripcionMueble) {
        this.descripcionMueble = descripcionMueble;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
