package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.ICotizacionUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IOrdenProduccionUseCase;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.presentacion.dto.request.AprobacionRequestDto;
import com.classic.craftorder.presentacion.dto.request.CotizacionRequestDto;
import com.classic.craftorder.presentacion.dto.request.RechazoRequestDto;
import com.classic.craftorder.presentacion.dto.response.CotizacionResponseDto;
import com.classic.craftorder.presentacion.dto.response.PaginaResponseDto;
import com.classic.craftorder.presentacion.mapeadores.CotizacionDtoEnriquecedor;
import com.classic.craftorder.presentacion.mapeadores.ICotizacionDtoMapper;
import com.classic.craftorder.presentacion.mapeadores.PaginaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cotizaciones")
@RequiredArgsConstructor
public class CotizacionController {

    private final ICotizacionUseCase cotizacionUseCase;
    private final ICotizacionDtoMapper dtoMapper;
    private final CotizacionDtoEnriquecedor enriquecedor;
    private final IOrdenProduccionUseCase ordenProduccionUseCase;

    @PostMapping("/calcular")
    public ResponseEntity<CotizacionResponseDto> calcular(
            @Valid @RequestBody CotizacionRequestDto dto) {
        Cotizacion cotizacion = dtoMapper.toDominio(dto);
        Cotizacion calculada = cotizacionUseCase.calcularYPreparar(cotizacion);
        return ResponseEntity.ok(enriquecedor.toResponse(calculada));
    }

    @PostMapping
    public ResponseEntity<CotizacionResponseDto> confirmar(
            @Valid @RequestBody CotizacionRequestDto dto) {
        Cotizacion cotizacion = dtoMapper.toDominio(dto);
        Cotizacion calculada = cotizacionUseCase.calcularYPreparar(cotizacion);
        Cotizacion confirmada = cotizacionUseCase.confirmar(calculada);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enriquecedor.toResponse(confirmada));
    }

    @GetMapping("/seguimiento/{token}")
    public ResponseEntity<CotizacionResponseDto> seguimiento(
            @PathVariable String token) {
        return ResponseEntity.ok(
                enriquecedor.toResponse(cotizacionUseCase.buscarPorToken(token)));
    }

    @GetMapping("/buscar/{token}")
    public ResponseEntity<Map<String, Object>> buscarPorToken(
            @PathVariable String token) {
        try {
            Cotizacion cotizacion = cotizacionUseCase.buscarPorToken(token);
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("cotizacionId", cotizacion.getId());
            resultado.put("estado", cotizacion.getEstado());

            try {
                OrdenProduccion orden = ordenProduccionUseCase
                        .buscarPorCotizacionId(cotizacion.getId());
                resultado.put("tieneOrden", true);
                resultado.put("ordenId", orden.getId());
            } catch (Exception e) {
                resultado.put("tieneOrden", false);
                resultado.put("ordenId", null);
            }

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<PaginaResponseDto<CotizacionResponseDto>> listar(
            @RequestParam(defaultValue = "PENDIENTE") String estado,
            @RequestParam(defaultValue = "0") int page) {
        PaginaResultado<Cotizacion> resultado =
                cotizacionUseCase.listarPorEstadoPaginado(estado, page);
        return ResponseEntity.ok(
                PaginaMapper.toResponse(resultado, enriquecedor::toResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotizacionResponseDto> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                enriquecedor.toResponse(cotizacionUseCase.buscarPorId(id)));
    }

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<CotizacionResponseDto> aprobar(
            @PathVariable Long id,
            @Valid @RequestBody AprobacionRequestDto dto) {
        return ResponseEntity.ok(
                enriquecedor.toResponse(cotizacionUseCase.aprobar(id, dto.getCostoAprobado())));
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<CotizacionResponseDto> rechazar(
            @PathVariable Long id,
            @Valid @RequestBody RechazoRequestDto dto) {
        return ResponseEntity.ok(
                enriquecedor.toResponse(cotizacionUseCase.rechazar(id, dto.getMotivoRechazo())));
    }

    @PostMapping("/pagar/{token}")
    public ResponseEntity<CotizacionResponseDto> pagar(
            @PathVariable String token) {
        return ResponseEntity.ok(
                enriquecedor.toResponse(cotizacionUseCase.pagar(token)));
    }
}
