package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.presentacion.dto.request.MaterialRequestDto;
import com.classic.craftorder.presentacion.dto.response.MaterialResponseDto;
import com.classic.craftorder.presentacion.dto.response.PaginaResponseDto;
import com.classic.craftorder.presentacion.mapeadores.IMaterialDtoMapper;
import com.classic.craftorder.presentacion.mapeadores.PaginaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final IMaterialUseCase materialUseCase;
    private final IMaterialDtoMapper mapper;

    @PostMapping
    public ResponseEntity<MaterialResponseDto> guardar(@Valid @RequestBody MaterialRequestDto requestDto) {
        Material material = materialUseCase.guardar(mapper.toDominio(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(material));
    }

    @PutMapping("/{id}")
    public MaterialResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody MaterialRequestDto requestDto) {
        Material material = materialUseCase.actualizar(id, mapper.toDominio(requestDto));
        return mapper.toResponse(material);
    }

    @PostMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        materialUseCase.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        materialUseCase.activar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public MaterialResponseDto buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(materialUseCase.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<PaginaResponseDto<MaterialResponseDto>> listarTodos(
            @RequestParam(defaultValue = "") String nombre,
            @RequestParam(defaultValue = "0") int page) {
        PaginaResultado<Material> resultado =
                materialUseCase.listarTodosPaginado(nombre, page);
        return ResponseEntity.ok(
                PaginaMapper.toResponse(resultado, mapper::toResponse));
    }

    @GetMapping("/activos")
    public ResponseEntity<PaginaResponseDto<MaterialResponseDto>> listarActivos(
            @RequestParam(defaultValue = "") String nombre,
            @RequestParam(defaultValue = "0") int page) {
        PaginaResultado<Material> resultado =
                materialUseCase.listarActivosPaginado(nombre, page);
        return ResponseEntity.ok(
                PaginaMapper.toResponse(resultado, mapper::toResponse));
    }
}
