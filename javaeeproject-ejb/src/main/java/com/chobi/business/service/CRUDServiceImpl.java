package com.chobi.business.service;

import com.chobi.boundary.logging.BoundaryLogger;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chobii on 21/09/15.
 */

@Singleton
@Local(CRUDService.class)
@Interceptors(BoundaryLogger.class)
public class CRUDServiceImpl implements CRUDService {

    private static final String HINT = "javax.persistence.fetchgraph";

    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> T find(Class<T> entityClass, int id) {
        return em.find(entityClass, id);
    }

    @Override
    public <T> T create(T t) {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    @Override
    public <T> T update(T t) {
        return em.merge(t);
    }

    @Override
    public <T> void delete(Class<T> entityClass, int id) {
        Object reference = em.getReference(entityClass, id);
        em.remove(reference);
    }

    @Override
    public <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName) {
        return em.createNamedQuery(queryName, entityClass)
                 .getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName, String entityGraph, Map parameters) {
        Query query = em.createNamedQuery(queryName, entityClass)
                .setHint(HINT, em.getEntityGraph(entityGraph));
        Set<Map.Entry> params = parameters.entrySet();
        for (Map.Entry entry : params) {
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }

        return query.getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName, String entityGraph) {
        return em.createNamedQuery(queryName, entityClass)
                 .setHint(HINT, em.getEntityGraph(entityGraph))
                 .getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(Class<T> entityClass, String queryName, Map parameters) {
        Set<Map.Entry> params = parameters.entrySet();
        Query query = em.createNamedQuery(queryName, entityClass);
        for (Map.Entry entry : params) {
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }

        return query.getResultList();
    }
}
