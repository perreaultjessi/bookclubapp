package com.bookclub.service.dao;

public interface GenericCrudDao<E, K>
{
    void add(E entity);
    void update(E entity);
    boolean remove(K key);
    List<E> list(K key);
    E find(K key);
}

