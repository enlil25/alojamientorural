/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.utiles;

import com.mycompany.alojamientorural.entidades.Actividad;
import com.mycompany.alojamientorural.entidades.Personal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cesar
 */
public class GeneradorCodigos {
    
    @PersistenceContext(unitName="alojamiento_rural_PU")
    private EntityManager em;
    
    public String generarCodigoActividad() {

        String codigo = null;
        List<Actividad> actividades = new ArrayList();

        actividades = em.createQuery("select c from Actividad c order by c.codigo_activ desc").getResultList();
        if (actividades.isEmpty()) {
            codigo = "01";
        } else {
            codigo = String.valueOf(Integer.parseInt(actividades.get(0).getCodigo_activ()) + 1);
            // dar formato de 3 digitos
            //ejemplo 2 -> 002 , 40 -> 040
            codigo = String.format("%02d", Integer.parseInt(codigo));
        }

        return codigo;
    }
    
    public String generarCodigoPersonal() {

        String codigo = null;
        List<Personal> actividades = new ArrayList();

        actividades = em.createQuery("select c from Personal c order by c.codigo_p desc").getResultList();
        if (actividades.isEmpty()) {
            codigo = "001";
        } else {
            codigo = String.valueOf(Integer.parseInt(actividades.get(0).getCodigo_p()) + 1);
            // dar formato de 3 digitos
            //ejemplo 2 -> 002 , 40 -> 040
            codigo = String.format("%03d", Integer.parseInt(codigo));
        }

        return codigo;
    }
}
