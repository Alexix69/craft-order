package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.ICotizacionUseCase;
import com.classic.craftorder.aplicacion.servicios.CorreoService;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.entidades.TipoAcabado;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.repositorios.ICotizacionRepositorio;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoAcabadoRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class CotizacionUseCaseImpl implements ICotizacionUseCase {

    private static final int TAMANIO_PAGINA = 20;
    private static final BigDecimal CM3_A_M3 = new BigDecimal("1000000");

    private final ICotizacionRepositorio cotizacionRepositorio;
    private final ITipoMuebleRepositorio tipoMuebleRepositorio;
    private final IMaterialRepositorio materialRepositorio;
    private final ITipoAcabadoRepositorio tipoAcabadoRepositorio;
    private final CorreoService correoService;

    public CotizacionUseCaseImpl(ICotizacionRepositorio cotizacionRepositorio,
                                  ITipoMuebleRepositorio tipoMuebleRepositorio,
                                  IMaterialRepositorio materialRepositorio,
                                  ITipoAcabadoRepositorio tipoAcabadoRepositorio,
                                  CorreoService correoService) {
        this.cotizacionRepositorio = cotizacionRepositorio;
        this.tipoMuebleRepositorio = tipoMuebleRepositorio;
        this.materialRepositorio = materialRepositorio;
        this.tipoAcabadoRepositorio = tipoAcabadoRepositorio;
        this.correoService = correoService;
    }

    @Override
    public Cotizacion calcularYPreparar(Cotizacion cotizacion) {
        TipoMueble tipoMueble = tipoMuebleRepositorio
                .buscarPorId(cotizacion.getTipoMuebleId())
                .orElseThrow(() -> new RuntimeException("Tipo de mueble no disponible"));

        if (!tipoMueble.getActivo()) {
            throw new RuntimeException("INACTIVO:" + tipoMueble.getNombre());
        }

        Material material = materialRepositorio
                .buscarPorId(cotizacion.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material no disponible"));

        if (!material.getActivo()) {
            throw new RuntimeException("INACTIVO:" + material.getNombre());
        }

        TipoAcabado acabado = tipoAcabadoRepositorio
                .buscarPorTipo(cotizacion.getTipoAcabado())
                .orElseThrow(() -> new RuntimeException("Tipo de acabado inválido"));

        cotizacion.setPrecioMaterialSnap(material.getPrecioPorM3());
        cotizacion.setCostoBaseMoSnap(tipoMueble.getCostoBaseMo());
        cotizacion.setPorcentajeAcabadoSnap(acabado.getPorcentaje());

        BigDecimal volumenM3 = cotizacion.getAltoCm()
                .multiply(cotizacion.getAnchoCm())
                .multiply(cotizacion.getProfundidadCm())
                .divide(CM3_A_M3, 6, RoundingMode.HALF_UP);

        BigDecimal costoMaterial = volumenM3.multiply(material.getPrecioPorM3());

        BigDecimal costoMo = tipoMueble.getCostoBaseMo();

        BigDecimal costoAcabado = costoMo
                .multiply(acabado.getPorcentaje())
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

        cotizacion.setCostoEstimado(
                costoMaterial.add(costoMo).add(costoAcabado)
                        .setScale(2, RoundingMode.HALF_UP));

        return cotizacion;
    }

    @Override
    public Cotizacion confirmar(Cotizacion cotizacion) {
        cotizacion.setToken(UUID.randomUUID().toString()
                .replace("-", "").substring(0, 32));
        cotizacion.setEstado("PENDIENTE");
        cotizacion.setCostoAprobado(null);
        cotizacion.setMotivoRechazo(null);
        return cotizacionRepositorio.guardar(cotizacion);
    }

    @Override
    public Cotizacion buscarPorToken(String token) {
        return cotizacionRepositorio.buscarPorToken(token)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
    }

    @Override
    public PaginaResultado<Cotizacion> listarPorEstadoPaginado(String estado, int pagina) {
        return cotizacionRepositorio
                .listarPorEstadoPaginado(estado, pagina, TAMANIO_PAGINA);
    }

    @Override
    public Cotizacion aprobar(Long id, BigDecimal costoAprobado) {
        Cotizacion cotizacion = buscarPorId(id);

        if (!"PENDIENTE".equals(cotizacion.getEstado())) {
            throw new RuntimeException("Solo se pueden aprobar cotizaciones pendientes");
        }

        cotizacion.setEstado("APROBADA");
        cotizacion.setCostoAprobado(costoAprobado);
        Cotizacion actualizada = cotizacionRepositorio.guardar(cotizacion);

        correoService.enviarAprobacion(
                actualizada.getCorreoCliente(),
                actualizada.getNombreCliente(),
                actualizada.getCostoAprobado(),
                actualizada.getToken());

        return actualizada;
    }

    @Override
    public Cotizacion rechazar(Long id, String motivoRechazo) {
        Cotizacion cotizacion = buscarPorId(id);

        if (!"PENDIENTE".equals(cotizacion.getEstado())) {
            throw new RuntimeException("Solo se pueden rechazar cotizaciones pendientes");
        }

        cotizacion.setEstado("RECHAZADA");
        cotizacion.setMotivoRechazo(motivoRechazo);
        Cotizacion actualizada = cotizacionRepositorio.guardar(cotizacion);

        correoService.enviarRechazo(
                actualizada.getCorreoCliente(),
                actualizada.getNombreCliente(),
                actualizada.getMotivoRechazo(),
                actualizada.getToken());

        return actualizada;
    }

    @Override
    public Cotizacion pagar(String token) {
        Cotizacion cotizacion = cotizacionRepositorio.buscarPorToken(token)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        if (!"APROBADA".equals(cotizacion.getEstado())) {
            throw new RuntimeException("ESTADO:" + cotizacion.getEstado());
        }

        cotizacion.setEstado("PAGADA");
        Cotizacion actualizada = cotizacionRepositorio.guardar(cotizacion);

        correoService.enviarConfirmacionPago(
                actualizada.getCorreoCliente(),
                actualizada.getNombreCliente(),
                actualizada.getToken());

        return actualizada;
    }

    @Override
    public Cotizacion buscarPorId(Long id) {
        return cotizacionRepositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
    }
}
