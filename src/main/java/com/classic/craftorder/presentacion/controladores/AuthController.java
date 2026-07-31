package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.classic.craftorder.dominio.entidades.Usuario;
import com.classic.craftorder.presentacion.dto.request.LoginRequestDto;
import com.classic.craftorder.presentacion.dto.response.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IUsuarioUseCase usuarioUseCase;
    private final BCryptPasswordEncoder encoder;

    public AuthController(IUsuarioUseCase usuarioUseCase, BCryptPasswordEncoder encoder) {
        this.usuarioUseCase = usuarioUseCase;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto) {
        Usuario usuario = usuarioUseCase.buscarPorCorreo(requestDto.getCorreo());

        if (!encoder.matches(requestDto.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new RuntimeException("Usuario inactivo");
        }

        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setId(usuario.getId());
        responseDto.setNombre(usuario.getNombre());
        responseDto.setCorreo(usuario.getCorreo());
        responseDto.setRol(usuario.getRol());
        responseDto.setPrimerLogin(usuario.getPrimerLogin());
        return responseDto;
    }
}
