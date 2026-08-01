package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.aplicacion.servicios.ImagenService;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.presentacion.dto.request.TipoMuebleRequestDto;
import com.classic.craftorder.presentacion.dto.response.PaginaResponseDto;
import com.classic.craftorder.presentacion.dto.response.TipoMuebleResponseDto;
import com.classic.craftorder.presentacion.mapeadores.ITipoMuebleDtoMapper;
import com.classic.craftorder.presentacion.mapeadores.PaginaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tipos-mueble")
@RequiredArgsConstructor
public class TipoMuebleController {

    private final ITipoMuebleUseCase tipoMuebleUseCase;
    private final ITipoMuebleDtoMapper mapper;
    private final ImagenService imagenService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TipoMuebleResponseDto> guardar(
            @Valid @ModelAttribute TipoMuebleRequestDto requestDto,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {
        if (imagenFile != null && !imagenFile.isEmpty()) {
            requestDto.setFotoUrl(imagenService.subirImagen(imagenFile));
        }
        TipoMueble tipoMueble = tipoMuebleUseCase.guardar(mapper.toDominio(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(tipoMueble));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TipoMuebleResponseDto actualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute TipoMuebleRequestDto requestDto,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {
        if (imagenFile != null && !imagenFile.isEmpty()) {
            requestDto.setFotoUrl(imagenService.subirImagen(imagenFile));
        }
        TipoMueble tipoMueble = tipoMuebleUseCase.actualizar(id, mapper.toDominio(requestDto));
        return mapper.toResponse(tipoMueble);
    }

    @PostMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        tipoMuebleUseCase.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        tipoMuebleUseCase.activar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public TipoMuebleResponseDto buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(tipoMuebleUseCase.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<PaginaResponseDto<TipoMuebleResponseDto>> listarTodos(
            @RequestParam(defaultValue = "") String nombre,
            @RequestParam(defaultValue = "0") int page) {
        PaginaResultado<TipoMueble> resultado =
                tipoMuebleUseCase.listarTodosPaginado(nombre, page);
        return ResponseEntity.ok(
                PaginaMapper.toResponse(resultado, mapper::toResponse));
    }

    @GetMapping("/activos")
    public ResponseEntity<PaginaResponseDto<TipoMuebleResponseDto>> listarActivos(
            @RequestParam(defaultValue = "") String nombre,
            @RequestParam(defaultValue = "0") int page) {
        PaginaResultado<TipoMueble> resultado =
                tipoMuebleUseCase.listarActivosPaginado(nombre, page);
        return ResponseEntity.ok(
                PaginaMapper.toResponse(resultado, mapper::toResponse));
    }
}
