/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author cesar
 */
@Embeddable
public class HabitacionId implements Serializable {
    
    @Column(name="numero_habita")
    private Integer numero_habitacion;
    
    @ManyToOne
    @JoinColumn(name = "nombre_aloja")
    private Alojamiento alojamiento;

    public HabitacionId() {
        //añadido recientemente
        alojamiento = new Alojamiento();
    }
    
    
    public HabitacionId(Integer numero_habitacion, Alojamiento alojamiento) {
        this.numero_habitacion = numero_habitacion;
        this.alojamiento = alojamiento;
    }

    public Integer getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(Integer numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final HabitacionId other = (HabitacionId) obj;
        if (!Objects.equals(this.numero_habitacion, other.numero_habitacion)) {
            return false;
        }
        return Objects.equals(this.alojamiento, other.alojamiento);
    }
    
    
    
    
}
