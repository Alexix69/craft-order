package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.ITipoAcabadoUseCase;
import com.classic.craftorder.presentacion.dto.request.TipoAcabadoPorcentajeRequestDto;
import com.classic.craftorder.presentacion.dto.response.TipoAcabadoResponseDto;
import com.classic.craftorder.presentacion.mapeadores.ITipoAcabadoDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-acabado")
@RequiredArgsConstructor
public class TipoAcabadoController {

    private final ITipoAcabadoUseCase tipoAcabadoUseCase;
    private final ITipoAcabadoDtoMapper mapper;

    @GetMapping
    public List<TipoAcabadoResponseDto> listarTodos() {
        return tipoAcabadoUseCase.listarTodos().stream().map(mapper::toResponse).toList();
    }

    @PostMapping("/{tipo}/porcentaje")
    public TipoAcabadoResponseDto actualizarPorcentaje(@PathVariable String tipo,
                                                         @Valid @RequestBody TipoAcabadoPorcentajeRequestDto requestDto) {
        return mapper.toResponse(tipoAcabadoUseCase.actualizarPorcentaje(tipo, requestDto.getPorcentaje()));
    }
}
