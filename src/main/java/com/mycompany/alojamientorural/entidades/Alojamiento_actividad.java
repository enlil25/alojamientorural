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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cesar
 */
@Entity
@Table(name = "alojamiento_actividad")
public class Alojamiento_actividad implements Serializable {
    
    @EmbeddedId
    private Alojamiento_actividad_PK id;
    
    @Column(name="dia_semana",length = 12,nullable = true)
    private String diaSemana;

    public Alojamiento_actividad() {
    }

    public Alojamiento_actividad(Alojamiento_actividad_PK id) {
        this.id = id;
    }

    public Alojamiento_actividad_PK getId() {
        return id;
    }

    public void setId(Alojamiento_actividad_PK id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Alojamiento_actividad other = (Alojamiento_actividad) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
