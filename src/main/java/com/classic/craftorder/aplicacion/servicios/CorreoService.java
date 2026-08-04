package com.classic.craftorder.aplicacion.servicios;

import com.resend.Resend;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Base64;

@Component
public class CorreoService {

    private final Resend resend;

    @Value("${resend.from}")
    private String from;

    @Value("${app.url.base}")
    private String appBaseUrl;

    public CorreoService(Resend resend) {
        this.resend = resend;
    }

    /**
     * Admin aprueba — envía link de pago al cliente.
     */
    public void enviarAprobacion(String correo, String nombre,
                                  BigDecimal costoAprobado, String token) {
        String linkPago = appBaseUrl + "/pagar/" + token;
        String html = """
            <h2>¡Tu cotización fue aprobada!</h2>
            <p>Hola %s,</p>
            <p>El costo aprobado para tu mueble es: <strong>$%s</strong></p>
            <p>Para proceder con el pago, haz click en el siguiente enlace:</p>
            <a href="%s" style="background:#8B5E3C;color:white;padding:12px 24px;
               text-decoration:none;border-radius:6px;display:inline-block">
              Proceder al pago
            </a>
            <p>También puedes consultar el estado de tu cotización en cualquier
               momento usando tu link de seguimiento:<br>
               <a href="%s">%s</a></p>
            """.formatted(
                nombre,
                costoAprobado.setScale(2, java.math.RoundingMode.HALF_UP),
                linkPago,
                appBaseUrl + "/seguimiento/" + token,
                appBaseUrl + "/seguimiento/" + token
            );
        enviar(correo, "Tu cotización fue aprobada — Classic Mueblería", html);
    }

    /**
     * Admin rechaza — envía motivo al cliente.
     */
    public void enviarRechazo(String correo, String nombre,
                               String motivo, String token) {
        String linkSeguimiento = appBaseUrl + "/seguimiento/" + token;
        String html = """
            <h2>Tu cotización no pudo ser procesada</h2>
            <p>Hola %s,</p>
            <p>Lamentablemente tu cotización no pudo ser aprobada.</p>
            <p><strong>Motivo:</strong> %s</p>
            <p>Si tienes dudas, puedes consultar el estado aquí:<br>
               <a href="%s">%s</a></p>
            <p>Puedes solicitar una nueva cotización cuando lo desees.</p>
            """.formatted(nombre, motivo, linkSeguimiento, linkSeguimiento);
        enviar(correo, "Actualización sobre tu cotización — Classic Mueblería", html);
    }

    /**
     * Cliente paga — confirmación de pago recibido.
     */
    public void enviarConfirmacionPago(String correo, String nombre, String token) {
        String linkSeguimiento = appBaseUrl + "/seguimiento/" + token;
        String html = """
            <h2>¡Hemos recibido tu pago!</h2>
            <p>Hola %s,</p>
            <p>Tu pago fue procesado exitosamente. Tu orden está en cola
               para ser asignada a uno de nuestros artesanos.</p>
            <p>Puedes consultar el estado de tu orden en cualquier momento:<br>
               <a href="%s">%s</a></p>
            """.formatted(nombre, linkSeguimiento, linkSeguimiento);
        enviar(correo, "Pago recibido — Classic Mueblería", html);
    }

    /**
     * Admin asigna artesano — orden entró a producción.
     */
    public void enviarInicioProduccion(String correo, String nombre, String token) {
        String linkSeguimiento = appBaseUrl + "/seguimiento/" + token;
        String html = """
            <h2>¡Tu mueble entró a producción!</h2>
            <p>Hola %s,</p>
            <p>Uno de nuestros artesanos ha comenzado a trabajar en tu mueble.</p>
            <p>Recuerda que puedes seguir el avance en tiempo real usando
               tu link de seguimiento. Este link te mostrará en qué etapa
               de fabricación se encuentra tu mueble en cada momento:</p>
            <a href="%s" style="background:#8B5E3C;color:white;padding:12px 24px;
               text-decoration:none;border-radius:6px;display:inline-block">
              Ver estado de mi orden
            </a>
            <p style="color:#666;font-size:14px">
              O copia este enlace en tu navegador:<br>%s
            </p>
            """.formatted(nombre, linkSeguimiento, linkSeguimiento);
        enviar(correo, "Tu mueble está en producción — Classic Mueblería", html);
    }

    /**
     * Artesano marca LISTO_ENTREGA — mueble listo para retirar.
     * Adjunta la factura en PDF cuando está disponible.
     */
    public void enviarListoEntrega(String correo, String nombre, String token,
                                    byte[] pdfBytes, String numeroFactura) {
        String linkSeguimiento = appBaseUrl + "/seguimiento/" + token;
        String parrafoAdjunto = pdfBytes != null
            ? "<p>Adjuntamos tu factura <strong>" + numeroFactura
                + "</strong> a este correo.</p>"
            : "";
        String html = """
            <h2>¡Tu mueble está listo!</h2>
            <p>Hola %s,</p>
            <p>Tu mueble ha pasado el control de calidad y está listo
               para ser retirado en nuestro taller.</p>
            %s
            <p>Consulta los detalles de tu orden aquí:<br>
               <a href="%s">%s</a></p>
            <p>¡Gracias por confiar en Classic Mueblería!</p>
            """.formatted(nombre, parrafoAdjunto, linkSeguimiento, linkSeguimiento);

        try {
            CreateEmailOptions.Builder builder = CreateEmailOptions.builder()
                .from(from)
                .to(correo)
                .subject("Tu mueble está listo para retirar — Classic Mueblería")
                .html(html);

            if (pdfBytes != null) {
                Attachment attachment = Attachment.builder()
                    .fileName(numeroFactura + ".pdf")
                    .content(Base64.getEncoder().encodeToString(pdfBytes))
                    .build();
                builder.attachments(attachment);
            }

            resend.emails().send(builder.build());
        } catch (Exception e) {
            System.err.println("[CorreoService] Error al enviar correo a "
                + correo + ": " + e.getMessage());
        }
    }

    private void enviar(String to, String subject, String html) {
        try {
            CreateEmailOptions request = CreateEmailOptions.builder()
                .from(from)
                .to(to)
                .subject(subject)
                .html(html)
                .build();
            resend.emails().send(request);
        } catch (Exception e) {
            System.err.println("[CorreoService] Error al enviar correo a "
                + to + ": " + e.getMessage());
        }
    }
}
