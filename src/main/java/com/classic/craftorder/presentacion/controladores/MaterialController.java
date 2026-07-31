package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IMaterialUseCase;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.presentacion.dto.request.MaterialRequestDto;
import com.classic.craftorder.presentacion.dto.response.MaterialResponseDto;
import com.classic.craftorder.presentacion.mapeadores.IMaterialDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        materialUseCase.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public MaterialResponseDto buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(materialUseCase.buscarPorId(id));
    }

    @GetMapping
    public List<MaterialResponseDto> listarTodos() {
        return materialUseCase.listarTodos().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/activos")
    public List<MaterialResponseDto> listarActivos() {
        return materialUseCase.listarActivos().stream().map(mapper::toResponse).toList();
    }
}
