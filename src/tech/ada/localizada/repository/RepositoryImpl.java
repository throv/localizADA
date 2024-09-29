package tech.ada.localizada.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryImpl<T, I> implements Repository<T, I>  {

    protected abstract List<T> getList();

    @Override
    public T save(T entity) {
        Optional<T> existingEntity = findById(getId(entity));
        if(existingEntity.isPresent()) {
            //getList().remove(existingEntity.get());
            int i = getList().indexOf(entity);
            getList().set(i,entity);
        }
        getList().add(entity);
        return entity;
    }

    @Override
    public Optional<T> findById(I id) {
        return getList().stream()
                .filter(entity -> getId(entity).equals(id))
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(getList());
    }

    @Override
    public void delete(I id) {
        getList().removeIf(entity -> getId(entity).equals(id));

    }

    protected abstract I getId(T entity);
}
