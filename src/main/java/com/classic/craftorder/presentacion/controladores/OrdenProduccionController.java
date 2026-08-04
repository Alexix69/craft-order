package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IOrdenProduccionUseCase;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.presentacion.dto.request.AsignacionRequestDto;
import com.classic.craftorder.presentacion.dto.request.CambioEstadoRequestDto;
import com.classic.craftorder.presentacion.dto.request.ReasignacionRequestDto;
import com.classic.craftorder.presentacion.dto.response.FacturaResponseDto;
import com.classic.craftorder.presentacion.dto.response.HistorialOrdenResponseDto;
import com.classic.craftorder.presentacion.dto.response.OrdenProduccionResponseDto;
import com.classic.craftorder.presentacion.mapeadores.IOrdenProduccionDtoMapper;
import com.classic.craftorder.presentacion.mapeadores.OrdenProduccionDtoEnriquecedor;
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

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenProduccionController {

    private final IOrdenProduccionUseCase ordenUseCase;
    private final OrdenProduccionDtoEnriquecedor enriquecedor;
    private final IOrdenProduccionDtoMapper dtoMapper;

    @GetMapping
    public ResponseEntity<List<OrdenProduccionResponseDto>> listarActivas() {
        return ResponseEntity.ok(
            ordenUseCase.listarActivas().stream()
                .map(enriquecedor::toResponse).toList());
    }

    @GetMapping("/artesano/{artesanoId}")
    public ResponseEntity<List<OrdenProduccionResponseDto>> listarPorArtesano(
            @PathVariable Long artesanoId) {
        return ResponseEntity.ok(
            ordenUseCase.listarPorArtesano(artesanoId).stream()
                .map(enriquecedor::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenProduccionResponseDto> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            enriquecedor.toResponse(ordenUseCase.buscarPorId(id)));
    }

    @PostMapping("/asignar")
    public ResponseEntity<OrdenProduccionResponseDto> asignar(
            @Valid @RequestBody AsignacionRequestDto dto,
            @RequestParam Long cotizacionId,
            @RequestParam Long realizadoPor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            enriquecedor.toResponse(
                ordenUseCase.asignar(
                    cotizacionId, dto.getArtesanoId(), realizadoPor)));
    }

    @PostMapping("/{id}/estado")
    public ResponseEntity<OrdenProduccionResponseDto> cambiarEstado(
            @PathVariable Long id,
            @Valid @RequestBody CambioEstadoRequestDto dto,
            @RequestParam Long realizadoPor) {
        return ResponseEntity.ok(
            enriquecedor.toResponse(
                ordenUseCase.cambiarEstado(
                    id, dto.getEstadoNuevo(),
                    dto.getMotivo(), realizadoPor)));
    }

    @PostMapping("/{id}/reasignar")
    public ResponseEntity<OrdenProduccionResponseDto> reasignar(
            @PathVariable Long id,
            @Valid @RequestBody ReasignacionRequestDto dto,
            @RequestParam Long realizadoPor) {
        return ResponseEntity.ok(
            enriquecedor.toResponse(
                ordenUseCase.reasignarArtesano(
                    id, dto.getNuevoArtesanoId(),
                    dto.getMotivo(), realizadoPor)));
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<HistorialOrdenResponseDto>> historial(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            ordenUseCase.listarHistorial(id).stream()
                .map(dtoMapper::toResponse).toList());
    }

    @GetMapping("/{id}/factura")
    public ResponseEntity<FacturaResponseDto> factura(
            @PathVariable Long id) {
        OrdenProduccion orden = ordenUseCase.buscarPorId(id);
        return ResponseEntity.ok(
            dtoMapper.toResponse(
                enriquecedor.obtenerFactura(orden.getId())));
    }
}
