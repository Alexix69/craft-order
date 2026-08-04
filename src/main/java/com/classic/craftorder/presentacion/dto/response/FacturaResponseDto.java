package com.classic.craftorder.presentacion.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class FacturaResponseDto {
    private Long id;
    private String numeroFactura;
    private String descripcionMueble;
    private BigDecimal montoTotal;
    private String pdfUrl;
    private OffsetDateTime createdAt;
}
