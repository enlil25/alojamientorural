/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author cesar
 */
@Embeddable
public class Alojamiento_actividad_PK implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "nombre_aloja",referencedColumnName = "nombre_aloja")
    private Alojamiento alojamiento;
    @ManyToOne
    @JoinColumn(name="codigo_activ",referencedColumnName = "codigo_activ")
    private Actividad actividad;

    public Alojamiento_actividad_PK() {
    }

    public Alojamiento_actividad_PK(Alojamiento alojamiento, Actividad actividad) {
        this.alojamiento = alojamiento;
        this.actividad = actividad;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.alojamiento);
        hash = 17 * hash + Objects.hashCode(this.actividad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alojamiento_actividad_PK other = (Alojamiento_actividad_PK) obj;
        if (!Objects.equals(this.alojamiento, other.alojamiento)) {
            return false;
        }
        return Objects.equals(this.actividad, other.actividad);
    }
    
    
}
