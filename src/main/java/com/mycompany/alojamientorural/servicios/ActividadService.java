/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import com.mycompany.alojamientorural.entidades.Actividad;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author cesar
 */
public class ActividadService extends AbstractService<Actividad, String> {

    public ActividadService() {
        super(Actividad.class);
    }

    /*
    las busquedas por texto no busca una coincidencia exacta
    sino comienzos de texto,finales de texto , dentro de un texto , 
    omision de minusculas , mayusculas
     */
    public List<Actividad> buscarPorNombre(String nombre) {

        TypedQuery<Actividad> typedQuery = em.createNamedQuery(Actividad.BUSCAR_POR_NOMBRE, Actividad.class).setParameter("nombre", nombre);

        return typedQuery.getResultList();
    }

    public List<Actividad> buscarPorDescripcion(String descripcion) {

        TypedQuery<Actividad> typedQuery = em.createNamedQuery(Actividad.BUSCAR_POR_DESCRIPCION, Actividad.class).setParameter("descripcion", descripcion);

        return typedQuery.getResultList();
    }

    public List<Actividad> buscarPorDificultad(Integer dificultad) {

        TypedQuery<Actividad> typedQuery = em.createNamedQuery(Actividad.BUSCAR_POR_DIFICULTAD, Actividad.class).setParameter("dificultad", dificultad);

        return typedQuery.getResultList();
    }

    public List<Actividad> buscarPorCodigo(String codigo) {

        TypedQuery<Actividad> typedQuery = em.createNamedQuery(Actividad.BUSCAR_POR_CODIGO, Actividad.class).setParameter("codigo", codigo);

        return typedQuery.getResultList();
    }
    
    public List<Actividad> buscarPorOtros(String nombre,String descripcion,Integer dificultad){
       
        TypedQuery<Actividad> typedQuery = em.createNamedQuery(Actividad.BUSCAR_POR_NOMBRE_DESCRIPCION_DIFICULTAD, Actividad.class)
                .setParameter("nombre", nombre)
                .setParameter("descripcion",descripcion)
                .setParameter("dificultad", dificultad);
                

        return typedQuery.getResultList(); 
    }

}
