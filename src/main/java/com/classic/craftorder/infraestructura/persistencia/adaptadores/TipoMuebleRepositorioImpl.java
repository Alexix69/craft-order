package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.TipoMueble;
import com.classic.craftorder.dominio.repositorios.ITipoMuebleRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.TipoMuebleEntity;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.ITipoMuebleJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.ITipoMuebleJpaRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class TipoMuebleRepositorioImpl implements ITipoMuebleRepositorio {

    private final ITipoMuebleJpaRepositorio jpaRepositorio;
    private final ITipoMuebleJpaMapper mapper;

    public TipoMuebleRepositorioImpl(ITipoMuebleJpaRepositorio jpaRepositorio, ITipoMuebleJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public TipoMueble guardar(TipoMueble tipoMueble) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(tipoMueble)));
    }

    @Override
    public TipoMueble actualizar(TipoMueble tipoMueble) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(tipoMueble)));
    }

    @Override
    public void desactivar(Long id) {
        TipoMuebleEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        entity.setActivo(false);
        jpaRepositorio.save(entity);
    }

    @Override
    public void activar(Long id) {
        TipoMuebleEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TipoMueble no encontrado con id: " + id));
        entity.setActivo(true);
        jpaRepositorio.save(entity);
    }

    @Override
    public Optional<TipoMueble> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public List<TipoMueble> listarTodos() {
        return jpaRepositorio.findAll().stream().map(mapper::toDominio).toList();
    }

    @Override
    public List<TipoMueble> listarActivos() {
        return jpaRepositorio.findByActivoTrue().stream().map(mapper::toDominio).toList();
    }

    @Override
    public PaginaResultado<TipoMueble> listarActivosPaginado(String nombre, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<TipoMuebleEntity> page = (nombre != null && !nombre.isBlank())
                ? jpaRepositorio.findByNombreContainingIgnoreCaseAndActivoTrue(nombre, pageable)
                : jpaRepositorio.findByActivoTrue(pageable);
        return toPagina(page, mapper::toDominio);
    }

    @Override
    public PaginaResultado<TipoMueble> listarTodosPaginado(String nombre, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<TipoMuebleEntity> page = (nombre != null && !nombre.isBlank())
                ? jpaRepositorio.findByNombreContainingIgnoreCase(nombre, pageable)
                : jpaRepositorio.findAll(pageable);
        return toPagina(page, mapper::toDominio);
    }

    private <E, D> PaginaResultado<D> toPagina(Page<E> page, Function<E, D> mapper) {
        PaginaResultado<D> resultado = new PaginaResultado<>();
        resultado.setContenido(page.getContent().stream().map(mapper).toList());
        resultado.setPaginaActual(page.getNumber());
        resultado.setTotalPaginas(page.getTotalPages());
        resultado.setTotalElementos(page.getTotalElements());
        resultado.setEsPrimera(page.isFirst());
        resultado.setEsUltima(page.isLast());
        return resultado;
    }
}
