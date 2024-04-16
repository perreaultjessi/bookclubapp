package com.bookclub.service;

import java.util.List;

public interface GenericCrudDao<E, K> {
    void add(E entity); // Add a new entity
    void update(E entity); // Update an existing entity
    boolean remove(E entity); // Remove an entity
    List<E> list(); // Return a list of objects of type E
    E find(K key); // Return an object of type E by type K value
}
