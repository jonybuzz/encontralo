package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.IdEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public abstract class InMemoryRepository<T extends IdEntity<I>, I> implements ReadOnlyRepository<T, I> {

    protected HashMap<I, T> entidades = new HashMap<>();

    protected abstract void init();

    @SafeVarargs
    protected final void load(T... entidades) {
        for (T elem : entidades) {
            I id = elem.getId();
            if (id == null) {
                throw new IllegalArgumentException("ID no puede ser null.");
            }
            if (this.entidades.containsKey(id)) {
                throw new DataIntegrityViolationException(
                        String.format("La entidad %s con ID %s ya existe.", elem.getClass().getName(), id));
            } else {
                this.entidades.put(id, elem);
            }
        }
    }

    public T getOne(I id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null.");
        }
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
