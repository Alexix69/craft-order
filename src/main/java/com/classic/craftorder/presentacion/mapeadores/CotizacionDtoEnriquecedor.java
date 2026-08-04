package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IOrdenProduccionUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.presentacion.dto.response.CotizacionResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CotizacionDtoEnriquecedor {

    private final ICotizacionDtoMapper mapper;
    private final ITipoMuebleUseCase tipoMuebleUseCase;
    private final IMaterialUseCase materialUseCase;
    private final IOrdenProduccionUseCase ordenProduccionUseCase;
    private final IOrdenProduccionDtoMapper ordenDtoMapper;

    public CotizacionDtoEnriquecedor(ICotizacionDtoMapper mapper,
            ITipoMuebleUseCase tipoMuebleUseCase,
            IMaterialUseCase materialUseCase,
            IOrdenProduccionUseCase ordenProduccionUseCase,
            IOrdenProduccionDtoMapper ordenDtoMapper) {
        this.mapper = mapper;
        this.tipoMuebleUseCase = tipoMuebleUseCase;
        this.materialUseCase = materialUseCase;
        this.ordenProduccionUseCase = ordenProduccionUseCase;
        this.ordenDtoMapper = ordenDtoMapper;
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
        try {
            OrdenProduccion orden =
                    ordenProduccionUseCase.buscarPorCotizacionId(cotizacion.getId());
            dto.setEstadoOrden(orden.getEstadoActual());
            dto.setHistorialOrden(
                    ordenProduccionUseCase.listarHistorial(orden.getId()).stream()
                            .map(ordenDtoMapper::toResponse).toList());
        } catch (Exception e) {
            dto.setEstadoOrden(null);
            dto.setHistorialOrden(null);
        }
        return dto;
    }

    public List<CotizacionResponseDto> toResponseList(List<Cotizacion> cotizaciones) {
        return cotizaciones.stream()
                .map(this::toResponse)
                .toList();
    }
}
