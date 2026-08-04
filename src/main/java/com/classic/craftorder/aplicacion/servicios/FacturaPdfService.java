package com.classic.craftorder.aplicacion.servicios;

import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.Factura;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

@Component
public class FacturaPdfService {

    private final ArchivoService archivoService;

    public FacturaPdfService(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    public String generarYSubir(Factura factura, Cotizacion cotizacion) {
        byte[] pdf = generarPdf(factura, cotizacion);
        return archivoService.subirArchivo(pdf,
            "factura-" + factura.getNumeroFactura());
    }

    private byte[] generarPdf(Factura factura, Cotizacion cotizacion) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);

            doc.add(new Paragraph("Classic Mueblería")
                .setFontSize(18).setBold());
            doc.add(new Paragraph("FACTURA")
                .setFontSize(14));
            doc.add(new Paragraph("N°: " + factura.getNumeroFactura()));
            doc.add(new Paragraph("Fecha: " +
                factura.getCreatedAt().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));

            doc.add(new Paragraph("\n"));

            doc.add(new Paragraph("CLIENTE").setBold());
            doc.add(new Paragraph("Nombre: " + cotizacion.getNombreCliente()));
            doc.add(new Paragraph("Correo: " + cotizacion.getCorreoCliente()));
            doc.add(new Paragraph("Teléfono: " +
                cotizacion.getTelefonoCliente()));

            doc.add(new Paragraph("\n"));

            doc.add(new Paragraph("DETALLE").setBold());
            doc.add(new Paragraph(factura.getDescripcionMueble()));

            doc.add(new Paragraph("\n"));

            doc.add(new Paragraph("TOTAL PAGADO").setBold());
            doc.add(new Paragraph("$" +
                factura.getMontoTotal().setScale(2, RoundingMode.HALF_UP)));

            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("Estado: PAGADO").setBold());
            doc.add(new Paragraph("Token de seguimiento: " +
                cotizacion.getToken()));

            doc.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(
                "Error al generar el PDF de la factura: " + e.getMessage());
        }
    }
}
