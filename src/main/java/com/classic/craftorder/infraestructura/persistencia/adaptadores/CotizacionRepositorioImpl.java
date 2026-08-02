package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Cotizacion;
import com.classic.craftorder.dominio.repositorios.ICotizacionRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.CotizacionEntity;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ICotizacionJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.ICotizacionJpaRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.function.Function;

public class CotizacionRepositorioImpl implements ICotizacionRepositorio {

    private final ICotizacionJpaRepositorio jpaRepositorio;
    private final ICotizacionJpaMapper mapper;

    public CotizacionRepositorioImpl(ICotizacionJpaRepositorio jpaRepositorio,
                                      ICotizacionJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public Cotizacion guardar(Cotizacion cotizacion) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(cotizacion)));
    }

    @Override
    public Optional<Cotizacion> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public Optional<Cotizacion> buscarPorToken(String token) {
        return jpaRepositorio.findByToken(token).map(mapper::toDominio);
    }

    @Override
    public PaginaResultado<Cotizacion> listarPorEstadoPaginado(
            String estado, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio,
                Sort.by("createdAt").descending());
        Page<CotizacionEntity> page = jpaRepositorio.findByEstado(estado, pageable);
        return toPagina(page, mapper::toDominio);
    }

    private <E, D> PaginaResultado<D> toPagina(Page<E> page, Function<E, D> mapFn) {
        PaginaResultado<D> resultado = new PaginaResultado<>();
        resultado.setContenido(page.getContent().stream().map(mapFn).toList());
        resultado.setPaginaActual(page.getNumber());
        resultado.setTotalPaginas(page.getTotalPages());
        resultado.setTotalElementos(page.getTotalElements());
        resultado.setEsPrimera(page.isFirst());
        resultado.setEsUltima(page.isLast());
        return resultado;
    }
}
