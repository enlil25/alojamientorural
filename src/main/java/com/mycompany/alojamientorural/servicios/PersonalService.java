/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import com.mycompany.alojamientorural.entidades.Personal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author cesar
 */
@Named("personalservice")
@Stateless
public class PersonalService extends AbstractService<Personal, String> {

    public PersonalService() {
        super(Personal.class);
    }

    public List<Personal> listarPersonalPorAlojamiento(String nombrealoja) {

        List<Personal> listado = em.createQuery("select p from Personal p where p.alojamiento.nombre_alojamiento = :nombrealoja", Personal.class)
                .setParameter("nombrealoja", nombrealoja).getResultList();

        return listado;
    }

    public Personal buscarPersonalPorNIF(String nif) {

        Personal personal = null;
        try {
            personal = em.createQuery("select p from Personal p where p.NIF = :nif", Personal.class).setParameter("nif", nif).getSingleResult();

        } catch (NoResultException e) {

            personal = null;

        } catch (NonUniqueResultException e) {

            personal = find(nif);
        }

        return personal;
    }

}
