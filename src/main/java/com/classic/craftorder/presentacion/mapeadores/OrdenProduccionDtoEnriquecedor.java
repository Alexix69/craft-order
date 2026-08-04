package com.classic.craftorder.presentacion.mapeadores;

import com.classic.craftorder.aplicacion.casosuso.entrada.ICotizacionUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.Factura;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.dominio.repositorios.IFacturaRepositorio;
import com.classic.craftorder.presentacion.dto.response.OrdenProduccionResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdenProduccionDtoEnriquecedor {

    private final IOrdenProduccionDtoMapper mapper;
    private final ICotizacionUseCase cotizacionUseCase;
    private final ITipoMuebleUseCase tipoMuebleUseCase;
    private final IMaterialUseCase materialUseCase;
    private final IUsuarioUseCase usuarioUseCase;
    private final IFacturaRepositorio facturaRepositorio;

    public OrdenProduccionDtoEnriquecedor(IOrdenProduccionDtoMapper mapper,
            ICotizacionUseCase cotizacionUseCase,
            ITipoMuebleUseCase tipoMuebleUseCase,
            IMaterialUseCase materialUseCase,
            IUsuarioUseCase usuarioUseCase,
            IFacturaRepositorio facturaRepositorio) {
        this.mapper = mapper;
        this.cotizacionUseCase = cotizacionUseCase;
        this.tipoMuebleUseCase = tipoMuebleUseCase;
        this.materialUseCase = materialUseCase;
        this.usuarioUseCase = usuarioUseCase;
        this.facturaRepositorio = facturaRepositorio;
    }

    public OrdenProduccionResponseDto toResponse(OrdenProduccion orden) {
        OrdenProduccionResponseDto dto = mapper.toResponse(orden);

        try {
            dto.setArtesanoNombre(
                    usuarioUseCase.buscarPorId(orden.getArtesanoId()).getNombre());
        } catch (Exception e) {
            dto.setArtesanoNombre("—");
        }

        try {
            Cotizacion cotizacion = cotizacionUseCase.buscarPorId(orden.getCotizacionId());
            dto.setNombreCliente(cotizacion.getNombreCliente());
            dto.setTipoAcabado(cotizacion.getTipoAcabado());
            dto.setAltoCm(cotizacion.getAltoCm());
            dto.setAnchoCm(cotizacion.getAnchoCm());
            dto.setProfundidadCm(cotizacion.getProfundidadCm());

            try {
                dto.setTipoMuebleNombre(
                        tipoMuebleUseCase.buscarPorId(cotizacion.getTipoMuebleId()).getNombre());
            } catch (Exception e) {
                dto.setTipoMuebleNombre("—");
            }
            try {
                dto.setMaterialNombre(
                        materialUseCase.buscarPorId(cotizacion.getMaterialId()).getNombre());
            } catch (Exception e) {
                dto.setMaterialNombre("—");
            }
        } catch (Exception e) {
            dto.setNombreCliente("—");
        }

        facturaRepositorio.buscarPorOrdenId(orden.getId())
                .ifPresent(factura -> dto.setPdfUrl(factura.getPdfUrl()));

        return dto;
    }

    public List<OrdenProduccionResponseDto> toResponseList(List<OrdenProduccion> ordenes) {
        return ordenes.stream().map(this::toResponse).toList();
    }

    public Factura obtenerFactura(Long ordenId) {
        return facturaRepositorio.buscarPorOrdenId(ordenId)
                .orElseThrow(() ->
                        new RuntimeException("No existe factura para la orden: " + ordenId));
    }
}
