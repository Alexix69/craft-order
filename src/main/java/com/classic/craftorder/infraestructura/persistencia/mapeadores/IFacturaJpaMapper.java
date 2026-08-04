package com.classic.craftorder.infraestructura.persistencia.mapeadores;

import com.classic.craftorder.dominio.entidades.Factura;
import com.classic.craftorder.infraestructura.persistencia.jpa.FacturaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFacturaJpaMapper {

    Factura toDominio(FacturaEntity entity);

    FacturaEntity toEntity(Factura dominio);
}
