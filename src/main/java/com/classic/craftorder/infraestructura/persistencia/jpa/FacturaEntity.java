package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "factura", uniqueConstraints = {
    @UniqueConstraint(name = "uk_factura_orden_id",
                      columnNames = "orden_id"),
    @UniqueConstraint(name = "uk_factura_numero",
                      columnNames = "numero_factura")
})
@Data
public class FacturaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_id", nullable = false)
    private Long ordenId;

    @Column(name = "numero_factura", nullable = false, length = 30)
    private String numeroFactura;

    @Column(name = "descripcion_mueble", nullable = false,
            columnDefinition = "TEXT")
    private String descripcionMueble;

    @Column(name = "monto_total", nullable = false,
            precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "pdf_url", length = 500)
    private String pdfUrl;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
