package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String rol;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Pendiente hasta que CotizacionEntity exista (relacion x2: creador y aprobador)
    // @OneToMany(mappedBy = "usuarioCreador")
    // private List<CotizacionEntity> cotizacionesCreadas;
    //
    // @OneToMany(mappedBy = "usuarioAprobador")
    // private List<CotizacionEntity> cotizacionesAprobadas;

    // Pendiente hasta que OrdenProduccionEntity exista
    // @OneToMany(mappedBy = "usuario")
    // private List<OrdenProduccionEntity> ordenesProduccion;

    // Pendiente hasta que HistorialEstadoEntity exista
    // @OneToMany(mappedBy = "usuario")
    // private List<HistorialEstadoEntity> historialEstados;

    @PrePersist
    protected void alCrear() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void alActualizar() {
        this.updatedAt = LocalDateTime.now();
    }
}
