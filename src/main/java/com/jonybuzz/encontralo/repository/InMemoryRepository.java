package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.IdEntity;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@NoRepositoryBean
public abstract class InMemoryRepository<T extends IdEntity<I>, I> implements ReadOnlyRepository<T, I> {

    protected HashMap<I, T> entidades = new HashMap<>();

    protected abstract void init();

    @SafeVarargs
    protected final void load(T... entidades) {
        for (T elem : entidades) {
            this.entidades.put(elem.getId(), elem);
        }
    }

    public T getOne(I id) {
        return this.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entidad con ID %s no encontrada.", id)));
    }

    @Override
    public Optional<T> findById(I id) {
        return Optional.ofNullable(entidades.get(id));
    }

    @Override
    public boolean existsById(I id) {
        return entidades.containsKey(id);
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(entidades.values());
    }

    @Override
    public Set<T> findAllById(Iterable<I> ids) {
        Set<T> set = new HashSet<>();
        for (I id :
                ids) {
            Optional<T> encontrado = this.findById(id);
            encontrado.ifPresent(set::add);
        }
        return set;
    }

    @Override
    public long count() {
        return entidades.size();
    }

}
