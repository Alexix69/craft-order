package com.classic.craftorder.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
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

    // TODO: descomentar cuando CotizacionEntity exista
    // @OneToMany(mappedBy = "fkUsuarioArtesanoEntity")
    // private List<CotizacionEntity> cotizacionesComoArtesano = new ArrayList<>();

    // TODO: descomentar cuando CotizacionEntity exista
    // @OneToMany(mappedBy = "fkUsuarioAprobadorEntity")
    // private List<CotizacionEntity> cotizacionesComoAprobador = new ArrayList<>();

    // TODO: descomentar cuando OrdenProduccionEntity exista
    // @OneToMany(mappedBy = "fkUsuarioEntity")
    // private List<OrdenProduccionEntity> ordenesProduccion = new ArrayList<>();

    // TODO: descomentar cuando HistorialEstadoEntity exista
    // @OneToMany(mappedBy = "fkUsuarioEntity")
    // private List<HistorialEstadoEntity> historialEstados = new ArrayList<>();
}
