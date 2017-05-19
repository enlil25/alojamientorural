/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author cesar
 */
@Entity
public class Habitacion implements Serializable{
    
    @EmbeddedId
    private HabitacionId id;
    @Column(length = 18)
    private String tipo; 
    @Column(name = "banio")
    private boolean banio;
    private Float precio;

    public Habitacion() {
        //añadido recientemente
        id = new HabitacionId();
    }

    public Habitacion(HabitacionId id) {
        this.id = id;
    }

    public HabitacionId getId() {
        return id;
    }

    public void setId(HabitacionId id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isBanio() {
        return banio;
    }

    public void setBanio(boolean banio) {
        this.banio = banio;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Habitacion other = (Habitacion) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
