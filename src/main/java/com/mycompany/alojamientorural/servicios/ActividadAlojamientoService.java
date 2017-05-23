/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import com.mycompany.alojamientorural.entidades.Alojamiento_actividad;
import com.mycompany.alojamientorural.entidades.Alojamiento_actividad_PK;
import javax.ejb.Stateless;

/**
 *
 * @author cesar
 */
@Stateless
public class ActividadAlojamientoService extends AbstractService<Alojamiento_actividad,Alojamiento_actividad_PK>{
    
    public ActividadAlojamientoService() {
        super(Alojamiento_actividad.class);
    }
    
}
