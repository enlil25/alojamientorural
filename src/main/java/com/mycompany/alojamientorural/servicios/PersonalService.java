/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import com.mycompany.alojamientorural.entidades.Personal;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author cesar
 */
@Named("personalservice")
public class PersonalService extends AbstractService<Personal, String> {

    public PersonalService() {
        super(Personal.class);
    }

    public List<Personal> listarPersonalPorAlojamiento(String nombrealoja) {

        List<Personal> listado = em.createQuery("select p from Personal p where p.alojamiento.nombre_alojamiento = :nombrealoja", Personal.class)
                .setParameter("nombrealoja", nombrealoja).getResultList();

        return listado;
    }

}
