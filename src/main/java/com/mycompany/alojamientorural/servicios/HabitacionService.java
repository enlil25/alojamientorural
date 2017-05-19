/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.servicios;

import com.mycompany.alojamientorural.entidades.Habitacion;
import com.mycompany.alojamientorural.entidades.HabitacionId;

/**
 *
 * @author cesar
 */
public class HabitacionService extends AbstractService<Habitacion,HabitacionId> {
    
    public HabitacionService() {
        super(Habitacion.class);
    }
    
}
