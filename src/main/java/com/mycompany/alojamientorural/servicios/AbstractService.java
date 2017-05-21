/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author cesar
 * @param <T>
 * @param <K>
 */
public abstract class AbstractService<T, K> {

    @Inject
    protected EntityManager em;

    private Class<T> claseEntidad;

    public AbstractService(Class<T> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    public void persist(T entidad) {
        em.persist(entidad);
    }

    public T merge(T entidad) {
        return em.merge(entidad);
    }

    public void remove(T entidad) {
        em.remove(entidad);
    }

    public T find(K key) {
        return em.find(claseEntidad, key);
    }

    public List<T> listAll() {
        List<T> listado = null;

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(claseEntidad);
        Root<T> root = criteriaQuery.from(claseEntidad);
        criteriaQuery.select(root);
        listado = em.createQuery(criteriaQuery).getResultList();

        return listado;
    }

}
