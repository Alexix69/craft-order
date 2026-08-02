package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.ICotizacionUseCase;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.presentacion.dto.request.CotizacionRequestDto;
import com.classic.craftorder.presentacion.dto.response.CotizacionResponseDto;
import com.classic.craftorder.presentacion.dto.response.PaginaResponseDto;
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

@RestController
@RequestMapping("/api/cotizaciones")
@RequiredArgsConstructor
public class CotizacionController {

    private final ICotizacionUseCase cotizacionUseCase;
    private final ICotizacionDtoMapper dtoMapper;

    @PostMapping("/calcular")
    public ResponseEntity<CotizacionResponseDto> calcular(
            @Valid @RequestBody CotizacionRequestDto dto) {
        Cotizacion cotizacion = dtoMapper.toDominio(dto);
        Cotizacion calculada = cotizacionUseCase.calcularYPreparar(cotizacion);
        return ResponseEntity.ok(dtoMapper.toResponse(calculada));
    }

    @PostMapping
    public ResponseEntity<CotizacionResponseDto> confirmar(
            @Valid @RequestBody CotizacionRequestDto dto) {
        Cotizacion cotizacion = dtoMapper.toDominio(dto);
        Cotizacion calculada = cotizacionUseCase.calcularYPreparar(cotizacion);
        Cotizacion confirmada = cotizacionUseCase.confirmar(calculada);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(confirmada));
    }

    @GetMapping("/seguimiento/{token}")
    public ResponseEntity<CotizacionResponseDto> seguimiento(
            @PathVariable String token) {
        return ResponseEntity.ok(
                dtoMapper.toResponse(cotizacionUseCase.buscarPorToken(token)));
    }

    @GetMapping
    public ResponseEntity<PaginaResponseDto<CotizacionResponseDto>> listar(
            @RequestParam(defaultValue = "PENDIENTE") String estado,
            @RequestParam(defaultValue = "0") int page) {
        PaginaResultado<Cotizacion> resultado =
                cotizacionUseCase.listarPorEstadoPaginado(estado, page);
        return ResponseEntity.ok(
                PaginaMapper.toResponse(resultado, dtoMapper::toResponse));
    }
}
