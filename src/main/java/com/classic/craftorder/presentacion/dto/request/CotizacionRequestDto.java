package com.classic.craftorder.presentacion.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CotizacionRequestDto {

    @NotBlank
    @Size(max = 120)
    private String nombreCliente;

    @NotBlank
    @Email
    @Size(max = 180)
    private String correoCliente;

    @NotBlank
    @Size(max = 30)
    private String telefonoCliente;

    @NotNull
    private Long tipoMuebleId;

    @NotNull
    private Long materialId;

    @NotBlank
    private String tipoAcabado;

    @NotNull
    @DecimalMin("10.00")
    @DecimalMax("500.00")
    private BigDecimal altoCm;

    @NotNull
    @DecimalMin("10.00")
    @DecimalMax("500.00")
    private BigDecimal anchoCm;

    @NotNull
    @DecimalMin("10.00")
    @DecimalMax("500.00")
    private BigDecimal profundidadCm;
}
