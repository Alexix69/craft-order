package com.classic.craftorder.infraestructura.persistencia.adaptadores;

import com.classic.craftorder.dominio.PaginaResultado;
import com.classic.craftorder.dominio.entidades.Material;
import com.classic.craftorder.dominio.repositorios.IMaterialRepositorio;
import com.classic.craftorder.infraestructura.persistencia.jpa.MaterialEntity;
import com.classic.craftorder.infraestructura.persistencia.mapeadores.IMaterialJpaMapper;
import com.classic.craftorder.infraestructura.repositorios.IMaterialJpaRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class MaterialRepositorioImpl implements IMaterialRepositorio {

    private final IMaterialJpaRepositorio jpaRepositorio;
    private final IMaterialJpaMapper mapper;

    public MaterialRepositorioImpl(IMaterialJpaRepositorio jpaRepositorio, IMaterialJpaMapper mapper) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapper = mapper;
    }

    @Override
    public Material guardar(Material material) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(material)));
    }

    @Override
    public Material actualizar(Material material) {
        return mapper.toDominio(jpaRepositorio.save(mapper.toEntity(material)));
    }

    @Override
    public void desactivar(Long id) {
        MaterialEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        entity.setActivo(false);
        jpaRepositorio.save(entity);
    }

    @Override
    public void activar(Long id) {
        MaterialEntity entity = jpaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado con id: " + id));
        entity.setActivo(true);
        jpaRepositorio.save(entity);
    }

    @Override
    public Optional<Material> buscarPorId(Long id) {
        return jpaRepositorio.findById(id).map(mapper::toDominio);
    }

    @Override
    public List<Material> listarTodos() {
        return jpaRepositorio.findAll().stream().map(mapper::toDominio).toList();
    }

    @Override
    public List<Material> listarActivos() {
        return jpaRepositorio.findByActivoTrue().stream().map(mapper::toDominio).toList();
    }

    @Override
    public PaginaResultado<Material> listarActivosPaginado(String nombre, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<MaterialEntity> page = (nombre != null && !nombre.isBlank())
                ? jpaRepositorio.findByNombreContainingIgnoreCaseAndActivoTrue(nombre, pageable)
                : jpaRepositorio.findByActivoTrue(pageable);
        return toPagina(page, mapper::toDominio);
    }

    @Override
    public PaginaResultado<Material> listarTodosPaginado(String nombre, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<MaterialEntity> page = (nombre != null && !nombre.isBlank())
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
