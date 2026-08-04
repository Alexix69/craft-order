package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.presentacion.dto.request.LoginRequestDto;
import com.classic.craftorder.presentacion.dto.response.LoginResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUsuarioUseCase usuarioUseCase;

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto) {
        Usuario usuario = usuarioUseCase.buscarPorEmail(requestDto.getCorreo());

        if (!usuario.getPasswordHash().equals(requestDto.getContrasena())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setId(usuario.getId());
        responseDto.setNombre(usuario.getNombre());
        responseDto.setCorreo(usuario.getEmail());
        responseDto.setRol(usuario.getRol());
        return responseDto;
    }
}
