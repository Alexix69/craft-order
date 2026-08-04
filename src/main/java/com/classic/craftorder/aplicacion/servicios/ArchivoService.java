package com.classic.craftorder.aplicacion.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class ArchivoService {

    private final Cloudinary cloudinary;

    public ArchivoService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String subirImagen(MultipartFile archivo) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                archivo.getBytes(),
                ObjectUtils.asMap(
                    "folder", "craft-order",
                    "resource_type", "image"
                )
            );
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen: " + e.getMessage());
        }
    }

    public String subirArchivo(byte[] contenido, String nombrePublico) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                contenido,
                ObjectUtils.asMap(
                    "folder", "craft-order/facturas",
                    "resource_type", "raw",
                    "public_id", nombrePublico
                )
            );
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException(
                "Error al subir el PDF a Cloudinary: " + e.getMessage());
        }
    }
}
