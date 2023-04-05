package com.olmez.mya.repo;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.olmez.mya.model.BaseObject;

@NoRepositoryBean
public interface BaseObjectRepository<T extends BaseObject> extends JpaRepository<T, Long> {

    @Override
    @Query("SELECT t FROM #{#entityName} t WHERE t.deleted = false")
    List<T> findAll();

    @Query("SELECT COUNT(t) FROM #{#entityName} t WHERE t.deleted = false")
    long countAll();

    /**
     * It sets 1 to deleted field instead of delete from database
     * 
     * @param object
     */
    default void deleted(T object) {
        object.setDeleted(true);
        save(object);
    }

    default void deletedAll(Iterable<T> entities) {
        entities.forEach(obj -> obj.setDeleted(true));
        saveAll(entities);
    }

    default T reload(T object) throws UnexpectedException {
        if (object == null) {
            return null;
        }
        if (object.getId() == null) {
            return object;
        }
        Optional<T> obj = findById(object.getId());
        if (!obj.isPresent()) {
            throw new UnexpectedException(
                    String.format("Failed reloading %s id =%d", object.getClass().getSimpleName(), object.getId()));
        }
        return obj.get();
    }

    default T getById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<T> obj = findById(id);
        if (!obj.isPresent()) {
            return null;
        }

        T baseObject = obj.get();
        return baseObject.isDeleted() ? null : baseObject;
    }

}
