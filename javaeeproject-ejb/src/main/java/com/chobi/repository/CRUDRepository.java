package com.chobi.repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Chobii on 21/09/15.
 */
public interface CRUDRepository {

    <T> T find(Class<T> entityClass, int id);
    <T> T create(T t);
    <T> T update(T t);
    <T> void delete(Class<T> entityClass, int id);
    <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName);
    <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName, int resultLimit);
    <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName, String entityGraph);
    <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName, Map parameters, int resultLimit);
}