package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoMuebleUseCase;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.presentacion.dto.request.TipoMuebleRequestDto;
import com.classic.craftorder.presentacion.dto.response.TipoMuebleResponseDto;
import com.classic.craftorder.presentacion.mapeadores.ITipoMuebleDtoMapper;
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
@RequestMapping("/api/tipos-mueble")
@RequiredArgsConstructor
public class TipoMuebleController {

    private final ITipoMuebleUseCase tipoMuebleUseCase;
    private final ITipoMuebleDtoMapper mapper;

    @PostMapping
    public ResponseEntity<TipoMuebleResponseDto> guardar(@Valid @RequestBody TipoMuebleRequestDto requestDto) {
        TipoMueble tipoMueble = tipoMuebleUseCase.guardar(mapper.toDominio(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(tipoMueble));
    }

    @PutMapping("/{id}")
    public TipoMuebleResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody TipoMuebleRequestDto requestDto) {
        TipoMueble tipoMueble = tipoMuebleUseCase.actualizar(id, mapper.toDominio(requestDto));
        return mapper.toResponse(tipoMueble);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        tipoMuebleUseCase.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public TipoMuebleResponseDto buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(tipoMuebleUseCase.buscarPorId(id));
    }

    @GetMapping
    public List<TipoMuebleResponseDto> listarTodos() {
        return tipoMuebleUseCase.listarTodos().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/activos")
    public List<TipoMuebleResponseDto> listarActivos() {
        return tipoMuebleUseCase.listarActivos().stream().map(mapper::toResponse).toList();
    }
}
