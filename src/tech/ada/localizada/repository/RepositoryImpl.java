package tech.ada.localizada.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryImpl<T, I> implements Repository<T, I>  {

    protected List<T> entityList = new ArrayList<>();

    @Override
    public T save(T entity) {
        Optional<T> existingEntity = findById(getId(entity));
        if(existingEntity.isPresent()) {
            entityList.remove(existingEntity.get());
        }
        entityList.add(entity);
        return entity;
    }

    @Override
    public Optional<T> findById(I id) {
        return entityList.stream()
                .filter(entity -> getId(entity).equals(id))
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entityList);
    }

    @Override
    public void delete(I id) {
        entityList.removeIf(entity -> getId(entity).equals(id));

    }

    protected abstract I getId(T entity);
}
