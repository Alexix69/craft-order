package com.classic.craftorder.aplicacion.servicios;

import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.entidades.Factura;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

@Component
public class FacturaPdfService {

    @Value("${taller.nombre}")
    private String tallerNombre;

    @Value("${taller.ruc}")
    private String tallerRuc;

    @Value("${taller.direccion}")
    private String tallerDireccion;

    @Value("${taller.telefono}")
    private String tallerTelefono;

    public byte[] generarBytes(Factura factura, Cotizacion cotizacion) {
        return generarPdf(factura, cotizacion);
    }

    private byte[] generarPdf(Factura factura, Cotizacion cotizacion) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc, PageSize.A4);
            doc.setMargins(40, 50, 40, 50);

            // Fuentes
            PdfFont fontBold = PdfFontFactory.createFont(
                StandardFonts.HELVETICA_BOLD);
            PdfFont fontRegular = PdfFontFactory.createFont(
                StandardFonts.HELVETICA);

            // Colores
            DeviceRgb colorPrimario = new DeviceRgb(139, 94, 60);
            DeviceRgb colorSecundario = new DeviceRgb(44, 36, 22);
            DeviceRgb colorClaro = new DeviceRgb(245, 240, 235);

            // CABECERA
            Table cabecera = new Table(UnitValue.createPercentArray(
                new float[]{60, 40}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginBottom(20);

            Cell celdaTaller = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(tallerNombre)
                    .setFont(fontBold).setFontSize(16)
                    .setFontColor(colorSecundario))
                .add(new Paragraph("RUC: " + tallerRuc)
                    .setFont(fontRegular).setFontSize(9)
                    .setFontColor(ColorConstants.DARK_GRAY))
                .add(new Paragraph(tallerDireccion)
                    .setFont(fontRegular).setFontSize(9)
                    .setFontColor(ColorConstants.DARK_GRAY))
                .add(new Paragraph(tallerTelefono)
                    .setFont(fontRegular).setFontSize(9)
                    .setFontColor(ColorConstants.DARK_GRAY));
            cabecera.addCell(celdaTaller);

            Cell celdaFactura = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Paragraph("FACTURA")
                    .setFont(fontBold).setFontSize(20)
                    .setFontColor(colorPrimario))
                .add(new Paragraph("N°: " + factura.getNumeroFactura())
                    .setFont(fontBold).setFontSize(11)
                    .setFontColor(colorSecundario))
                .add(new Paragraph("Fecha: " +
                    factura.getCreatedAt().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .setFont(fontRegular).setFontSize(9)
                    .setFontColor(ColorConstants.DARK_GRAY));
            cabecera.addCell(celdaFactura);

            doc.add(cabecera);

            doc.add(new LineSeparator(new SolidLine(1f))
                .setStrokeColor(colorPrimario)
                .setMarginBottom(15));

            // DATOS DEL CLIENTE
            doc.add(new Paragraph("DATOS DEL CLIENTE")
                .setFont(fontBold).setFontSize(10)
                .setFontColor(colorPrimario)
                .setMarginBottom(6));

            Table tablaCliente = new Table(UnitValue.createPercentArray(
                new float[]{30, 70}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginBottom(20);

            agregarFilaTabla(tablaCliente, "Nombre:",
                cotizacion.getNombreCliente(), fontBold, fontRegular, colorClaro);
            agregarFilaTabla(tablaCliente, "Correo:",
                cotizacion.getCorreoCliente(), fontBold, fontRegular, null);
            agregarFilaTabla(tablaCliente, "Teléfono:",
                cotizacion.getTelefonoCliente(), fontBold, fontRegular, colorClaro);

            doc.add(tablaCliente);

            // DETALLE DEL PEDIDO
            doc.add(new Paragraph("DETALLE DEL PEDIDO")
                .setFont(fontBold).setFontSize(10)
                .setFontColor(colorPrimario)
                .setMarginBottom(6));

            Table tablaDetalle = new Table(UnitValue.createPercentArray(
                new float[]{30, 70}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginBottom(20);

            agregarFilaTabla(tablaDetalle, "Descripción:",
                factura.getDescripcionMueble(),
                fontBold, fontRegular, colorClaro);

            doc.add(tablaDetalle);

            // TOTAL
            Table tablaTotal = new Table(UnitValue.createPercentArray(
                new float[]{70, 30}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginBottom(30);

            Cell celdaEtiqueta = new Cell()
                .setBackgroundColor(colorSecundario)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("TOTAL PAGADO")
                    .setFont(fontBold).setFontSize(12)
                    .setFontColor(ColorConstants.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT));
            tablaTotal.addCell(celdaEtiqueta);

            Cell celdaMonto = new Cell()
                .setBackgroundColor(colorPrimario)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("$" + factura.getMontoTotal()
                    .setScale(2, RoundingMode.HALF_UP))
                    .setFont(fontBold).setFontSize(14)
                    .setFontColor(ColorConstants.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));
            tablaTotal.addCell(celdaMonto);

            doc.add(tablaTotal);

            // PIE DE PÁGINA
            doc.add(new LineSeparator(new SolidLine(0.5f))
                .setStrokeColor(ColorConstants.LIGHT_GRAY)
                .setMarginBottom(8));

            doc.add(new Paragraph(
                "Gracias por confiar en " + tallerNombre +
                ". Este documento es comprobante de su pago.")
                .setFont(fontRegular).setFontSize(8)
                .setFontColor(ColorConstants.GRAY)
                .setTextAlignment(TextAlignment.CENTER));

            doc.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(
                "Error al generar el PDF de la factura: " + e.getMessage());
        }
    }

    private void agregarFilaTabla(Table tabla, String etiqueta, String valor,
                                   PdfFont fontBold, PdfFont fontRegular,
                                   DeviceRgb colorFondo) {
        Cell celdaEtiqueta = new Cell()
            .add(new Paragraph(etiqueta)
                .setFont(fontBold).setFontSize(9))
            .setBorder(Border.NO_BORDER);
        Cell celdaValor = new Cell()
            .add(new Paragraph(valor != null ? valor : "—")
                .setFont(fontRegular).setFontSize(9))
            .setBorder(Border.NO_BORDER);

        if (colorFondo != null) {
            celdaEtiqueta.setBackgroundColor(colorFondo);
            celdaValor.setBackgroundColor(colorFondo);
        }

        tabla.addCell(celdaEtiqueta);
        tabla.addCell(celdaValor);
    }
}
