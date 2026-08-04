package com.classic.craftorder.dominio.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private Long id;
    private String nombre;
    private String email;
    private String passwordHash;
    private String rol;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
