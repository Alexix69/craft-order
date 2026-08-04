package com.classic.craftorder.aplicacion.casosuso.impl;

import com.classic.craftorder.aplicacion.casosuso.entrada.IOrdenProduccionUseCase;
import com.classic.craftorder.aplicacion.servicios.CorreoService;
import com.classic.craftorder.aplicacion.servicios.FacturaPdfService;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.Factura;
import com.classic.craftorder.dominio.entidades.HistorialOrden;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.entidades.OrdenProduccion;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.dominio.repositorios.ICotizacionRepositorio;
import com.classic.craftorder.dominio.repositorios.IFacturaRepositorio;
import com.classic.craftorder.dominio.repositorios.IHistorialOrdenRepositorio;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import com.classic.craftorder.dominio.repositorios.IOrdenProduccionRepositorio;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;
import com.classic.craftorder.dominio.repositorios.IUsuarioRepositorio;

import java.time.OffsetDateTime;
import java.util.List;

public class OrdenProduccionUseCaseImpl implements IOrdenProduccionUseCase {

    private static final List<String> ETAPAS = List.of(
        "ASIGNADO", "CORTE", "ENSAMBLADO",
        "ACABADOS", "CONTROL_CALIDAD", "LISTO_ENTREGA");

    private final IOrdenProduccionRepositorio ordenRepositorio;
    private final IHistorialOrdenRepositorio historialRepositorio;
    private final IFacturaRepositorio facturaRepositorio;
    private final ICotizacionRepositorio cotizacionRepositorio;
    private final ITipoMuebleRepositorio tipoMuebleRepositorio;
    private final IMaterialRepositorio materialRepositorio;
    private final IUsuarioRepositorio usuarioRepositorio;
    private final FacturaPdfService facturaPdfService;
    private final CorreoService correoService;

    public OrdenProduccionUseCaseImpl(IOrdenProduccionRepositorio ordenRepositorio,
                                       IHistorialOrdenRepositorio historialRepositorio,
                                       IFacturaRepositorio facturaRepositorio,
                                       ICotizacionRepositorio cotizacionRepositorio,
                                       ITipoMuebleRepositorio tipoMuebleRepositorio,
                                       IMaterialRepositorio materialRepositorio,
                                       IUsuarioRepositorio usuarioRepositorio,
                                       FacturaPdfService facturaPdfService,
                                       CorreoService correoService) {
        this.ordenRepositorio = ordenRepositorio;
        this.historialRepositorio = historialRepositorio;
        this.facturaRepositorio = facturaRepositorio;
        this.cotizacionRepositorio = cotizacionRepositorio;
        this.tipoMuebleRepositorio = tipoMuebleRepositorio;
        this.materialRepositorio = materialRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.facturaPdfService = facturaPdfService;
        this.correoService = correoService;
    }

    @Override
    public OrdenProduccion asignar(Long cotizacionId, Long artesanoId,
                                    Long realizadoPor) {
        Cotizacion cotizacion = cotizacionRepositorio
            .buscarPorId(cotizacionId)
            .orElseThrow(() ->
                new RuntimeException("Cotización no encontrada"));

        if (!"PAGADA".equals(cotizacion.getEstado())) {
            throw new RuntimeException(
                "Solo se pueden asignar cotizaciones en estado PAGADA");
        }

        if (ordenRepositorio.buscarPorCotizacionId(cotizacionId).isPresent()) {
            throw new RuntimeException(
                "Esta cotización ya tiene una orden asignada");
        }

        OrdenProduccion orden = new OrdenProduccion();
        orden.setCotizacionId(cotizacionId);
        orden.setArtesanoId(artesanoId);
        orden.setEstadoActual("ASIGNADO");
        orden.setFechaInicio(OffsetDateTime.now());
        OrdenProduccion guardada = ordenRepositorio.guardar(orden);

        registrarHistorial(guardada.getId(), "CAMBIO_ETAPA",
            null, "ASIGNADO", null, realizadoPor);

        return guardada;
    }

    @Override
    public OrdenProduccion cambiarEstado(Long ordenId, String estadoNuevo,
                                          String motivo, Long realizadoPor) {
        OrdenProduccion orden = buscarPorId(ordenId);

        if ("LISTO_ENTREGA".equals(orden.getEstadoActual())) {
            throw new RuntimeException(
                "Una orden en LISTO_ENTREGA no puede cambiar de estado");
        }

        int indiceActual = ETAPAS.indexOf(orden.getEstadoActual());
        int indiceNuevo = ETAPAS.indexOf(estadoNuevo);

        if (indiceNuevo < 0) {
            throw new RuntimeException("Estado inválido: " + estadoNuevo);
        }

        Usuario realizador = usuarioRepositorio.buscarPorId(realizadoPor)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if ("ARTESANO".equals(realizador.getRol())) {
            if (indiceNuevo != indiceActual + 1) {
                throw new RuntimeException(
                    "Solo puedes avanzar una etapa a la vez");
            }
        }

        boolean esRetroceso = indiceNuevo < indiceActual;

        if (esRetroceso && (motivo == null || motivo.isBlank())) {
            throw new RuntimeException(
                "El motivo es obligatorio para retroceder una etapa");
        }

        String estadoAnterior = orden.getEstadoActual();
        orden.setEstadoActual(estadoNuevo);

        if ("LISTO_ENTREGA".equals(estadoNuevo)) {
            orden.setFechaFinalizacion(OffsetDateTime.now());
            OrdenProduccion guardada = ordenRepositorio.guardar(orden);
            registrarHistorial(ordenId, "CAMBIO_ETAPA",
                estadoAnterior, estadoNuevo, motivo, realizadoPor);
            generarFactura(guardada);
            return guardada;
        }

        if ("ASIGNADO".equals(estadoAnterior) && "CORTE".equals(estadoNuevo)) {
            Cotizacion cotizacion = cotizacionRepositorio
                .buscarPorId(orden.getCotizacionId())
                .orElseThrow();
            correoService.enviarInicioProduccion(
                cotizacion.getCorreoCliente(),
                cotizacion.getNombreCliente(),
                cotizacion.getToken());
        }

        OrdenProduccion guardada = ordenRepositorio.guardar(orden);
        registrarHistorial(ordenId, "CAMBIO_ETAPA",
            estadoAnterior, estadoNuevo, motivo, realizadoPor);
        return guardada;
    }

    @Override
    public OrdenProduccion reasignarArtesano(Long ordenId,
                                              Long nuevoArtesanoId,
                                              String motivo,
                                              Long realizadoPor) {
        if (motivo == null || motivo.isBlank()) {
            throw new RuntimeException(
                "El motivo es obligatorio para reasignar un artesano");
        }

        OrdenProduccion orden = buscarPorId(ordenId);

        if ("LISTO_ENTREGA".equals(orden.getEstadoActual())) {
            throw new RuntimeException(
                "No se puede reasignar una orden ya finalizada");
        }

        String artesanoAnterior = String.valueOf(orden.getArtesanoId());
        orden.setArtesanoId(nuevoArtesanoId);
        OrdenProduccion guardada = ordenRepositorio.guardar(orden);

        registrarHistorial(ordenId, "REASIGNACION",
            artesanoAnterior,
            String.valueOf(nuevoArtesanoId),
            motivo, realizadoPor);

        return guardada;
    }

    @Override
    public OrdenProduccion buscarPorId(Long id) {
        return ordenRepositorio.buscarPorId(id)
            .orElseThrow(() ->
                new RuntimeException("Orden no encontrada con id: " + id));
    }

    @Override
    public OrdenProduccion buscarPorCotizacionId(Long cotizacionId) {
        return ordenRepositorio.buscarPorCotizacionId(cotizacionId)
            .orElseThrow(() ->
                new RuntimeException(
                    "No existe orden para la cotización: " + cotizacionId));
    }

    @Override
    public List<OrdenProduccion> listarActivas() {
        return ordenRepositorio.listarActivas();
    }

    @Override
    public List<OrdenProduccion> listarPorArtesano(Long artesanoId) {
        return ordenRepositorio.listarPorArtesano(artesanoId);
    }

    @Override
    public List<HistorialOrden> listarHistorial(Long ordenId) {
        return historialRepositorio.listarPorOrden(ordenId);
    }

    private void registrarHistorial(Long ordenId, String tipoEvento,
                                     String valorAnterior, String valorNuevo,
                                     String motivo, Long realizadoPor) {
        HistorialOrden h = new HistorialOrden();
        h.setOrdenId(ordenId);
        h.setTipoEvento(tipoEvento);
        h.setValorAnterior(valorAnterior);
        h.setValorNuevo(valorNuevo);
        h.setMotivo(motivo);
        h.setRealizadoPor(realizadoPor);
        historialRepositorio.guardar(h);
    }

    private void generarFactura(OrdenProduccion orden) {
        Cotizacion cotizacion = cotizacionRepositorio
            .buscarPorId(orden.getCotizacionId())
            .orElseThrow();

        int ultimoNumero = facturaRepositorio.obtenerUltimoNumero();
        String numeroFactura = String.format("FAC-%04d", ultimoNumero + 1);

        String nombreTipoMueble = tipoMuebleRepositorio
            .buscarPorId(cotizacion.getTipoMuebleId())
            .map(TipoMueble::getNombre)
            .orElse("Mueble");
        String nombreMaterial = materialRepositorio
            .buscarPorId(cotizacion.getMaterialId())
            .map(Material::getNombre)
            .orElse("material no disponible");

        String descripcion = String.format(
            "%s en %s, %.0f×%.0f×%.0f cm, acabado %s",
            nombreTipoMueble,
            nombreMaterial,
            cotizacion.getAltoCm(),
            cotizacion.getAnchoCm(),
            cotizacion.getProfundidadCm(),
            cotizacion.getTipoAcabado());

        Factura factura = new Factura();
        factura.setOrdenId(orden.getId());
        factura.setNumeroFactura(numeroFactura);
        factura.setDescripcionMueble(descripcion);
        factura.setMontoTotal(cotizacion.getCostoAprobado());

        Factura guardada = facturaRepositorio.guardar(factura);

        try {
            String pdfUrl = facturaPdfService.generarYSubir(guardada, cotizacion);
            guardada.setPdfUrl(pdfUrl);
            facturaRepositorio.guardar(guardada);
        } catch (Exception e) {
            System.err.println("[FacturaService] Error al generar PDF: "
                + e.getMessage());
        }

        correoService.enviarListoEntrega(
            cotizacion.getCorreoCliente(),
            cotizacion.getNombreCliente(),
            cotizacion.getToken());
    }
}
