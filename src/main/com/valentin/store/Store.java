package com.valentin.store;

import java.util.List;

public interface Store<E, K> {

    E get(K key);

    E save(E entity);

    boolean delete(K key);

    int count();

    List<E> getAll();
}
