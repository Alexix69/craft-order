package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.presentacion.dto.request.UsuarioRequestDto;
import com.classic.craftorder.presentacion.dto.response.UsuarioResponseDto;
import com.classic.craftorder.presentacion.mapeadores.IUsuarioDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioUseCase usuarioUseCase;
    private final IUsuarioDtoMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> guardar(@Valid @RequestBody UsuarioRequestDto requestDto) {
        Usuario usuario = usuarioUseCase.guardar(mapper.toDominio(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(usuario));
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(usuarioUseCase.buscarPorId(id));
    }

    @GetMapping
    public List<UsuarioResponseDto> listarTodos() {
        return usuarioUseCase.listarTodos().stream().map(mapper::toResponse).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
