/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import com.mycompany.alojamientorural.entidades.Actividad;
import com.mycompany.alojamientorural.entidades.Alojamiento;
import com.mycompany.alojamientorural.entidades.Alojamiento_actividad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author cesar
 */
@Stateless
public class AlojamientoService extends AbstractService<Alojamiento, String> {

    public AlojamientoService() {
        super(Alojamiento.class);
    }

//    public List<Actividad> buscarActividadesPorAlojamiento(String codaloja) {
//        List<Actividad> actividades = new ArrayList();
//
//        TypedQuery<Alojamiento_actividad> typedQquery = em.createQuery("select p from Alojamiento_actividad p where p.id.alojamiento.nombre_alojamiento = :codaloja", Alojamiento_actividad.class);
//        typedQquery.setParameter("codaloja", codaloja);
//        List<Alojamiento_actividad> listado_aloj_act = typedQquery.getResultList();
//        for (Alojamiento_actividad alac : listado_aloj_act) {
//            Actividad actbusc = em.find(Actividad.class, alac.getId().getActividad().getCodigo_activ());
//            if (actbusc != null) {
//                actividades.add(actbusc);
//            }
//        }
//        return actividades;
//    }
    
    public List<Alojamiento_actividad> buscarAsignacionesPorAlojamiento(String codaloja) {
        List<Alojamiento_actividad> listado_aloj_act = null;
        TypedQuery<Alojamiento_actividad> typedQquery = em.createQuery("select p from Alojamiento_actividad p where p.id.alojamiento.nombre_alojamiento = :codaloja", Alojamiento_actividad.class);
        listado_aloj_act = typedQquery.setParameter("codaloja", codaloja).getResultList();
       
        return listado_aloj_act;
    }

}
