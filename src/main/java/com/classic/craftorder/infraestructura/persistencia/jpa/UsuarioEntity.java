package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@RequiredArgsConstructor
public class UsuarioEntity extends AuditoriaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String rol;

    // TODO: descomentar cuando OrdenProduccionEntity exista
    // @OneToMany(mappedBy = "fkUsuarioEntity")
    // private List<OrdenProduccionEntity> ordenesProduccion = new ArrayList<>();
}
