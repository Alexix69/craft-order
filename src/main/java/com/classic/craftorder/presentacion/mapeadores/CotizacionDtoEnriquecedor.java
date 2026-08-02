package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.presentacion.dto.response.CotizacionResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CotizacionDtoEnriquecedor {

    private final ICotizacionDtoMapper mapper;
    private final ITipoMuebleUseCase tipoMuebleUseCase;
    private final IMaterialUseCase materialUseCase;

    public CotizacionDtoEnriquecedor(ICotizacionDtoMapper mapper,
            ITipoMuebleUseCase tipoMuebleUseCase,
            IMaterialUseCase materialUseCase) {
        this.mapper = mapper;
        this.tipoMuebleUseCase = tipoMuebleUseCase;
        this.materialUseCase = materialUseCase;
    }

    public CotizacionResponseDto toResponse(Cotizacion cotizacion) {
        CotizacionResponseDto dto = mapper.toResponse(cotizacion);
        try {
            dto.setTipoMuebleNombre(
                    tipoMuebleUseCase.buscarPorId(cotizacion.getTipoMuebleId())
                            .getNombre());
        } catch (Exception e) {
            dto.setTipoMuebleNombre("—");
        }
        try {
            dto.setMaterialNombre(
                    materialUseCase.buscarPorId(cotizacion.getMaterialId())
                            .getNombre());
        } catch (Exception e) {
            dto.setMaterialNombre("—");
        }
        return dto;
    }

    public List<CotizacionResponseDto> toResponseList(List<Cotizacion> cotizaciones) {
        return cotizaciones.stream()
                .map(this::toResponse)
                .toList();
    }
}
