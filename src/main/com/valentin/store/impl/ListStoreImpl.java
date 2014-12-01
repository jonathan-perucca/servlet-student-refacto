package com.valentin.store.impl;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.valentin.domain.User;
import com.valentin.store.Store;

import java.util.Iterator;
import java.util.List;

public class ListStoreImpl<E extends User, K extends Long> implements Store<E, K>{

    private List<E> store;
    private Long increment;

    private static Store listStore;

    public ListStoreImpl(List store) {
        this.increment = 0L;
        this.store = store;
    }

    @Override
    public E get(final K key) {
        Optional<E> element = getFirst(key);
        return element.orNull();
    }

    @Override
    public E save(final E entity) {
        return updateEntity(entity) ? entity : createEntity(entity);
    }

    @Override
    public boolean delete(final K key) {
        E entity = get(key);
        if(!Optional.of(entity).isPresent()) {
            return false; // Unknown entity
        }
        return store.remove(entity);
    }

    @Override
    public int count() {
        return store.size();
    }

    @Override
    public List<E> getAll() {
        return ImmutableList.copyOf(store);
    }

    /**
     * Does store contains the parametered entity
     * @param entity
     * @return
     *     if store contains entity, returns true
     *     if entity is null, null is returned
     */
    private boolean exists(E entity) {
        return Optional.of(FluentIterable.from(store).contains(entity)).orNull();
    }

    /**
     * Retrieve first element in store matching with same key
     * @param key
     * @return
     */
    private Optional<E> getFirst(final K key) {
        return FluentIterable.from(store).filter(new Predicate<E>() {
            @Override
            public boolean apply(E e) {
                return key == e.getId();
            }
        }).first();
    }

    /**
     * Create entity into store
     * @param entity
     * @return entity with id field loaded
     */
    private E createEntity(E entity) {
        if(entity.getId() == null) {
            entity.setId(++increment);
        }
        store.add(entity);
        return entity;
    }

    /**
     * If entity exists in store, it updates it
     * @param entity
     * @return true if it already exists and as been updated
     */
    private boolean updateEntity(final E entity) {
        if(this.exists(entity)) { // Update use case
            E element = getFirst((K) entity.getId()).get();

            element.setNom(entity.getNom());
            element.setGenre(entity.getGenre());
            element.setBirthday(entity.getBirthday());

            // TODO : prefer use a map to use "put" function
            store.remove(entity);
            store.add(element);
            return true;
        }
        return false;
    }

    public static Store newInstance() {
        return new ListStoreImpl(Lists.newArrayList());
    }
}
