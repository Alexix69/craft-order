package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.infraestructura.persistencia.jpa.CotizacionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICotizacionJpaMapper {

    Cotizacion toDominio(CotizacionEntity entity);

    CotizacionEntity toEntity(Cotizacion dominio);
}
