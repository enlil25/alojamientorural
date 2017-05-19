/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author cesar
 */
@Entity
@Table(name = "alojamientoRural")
public class Alojamiento implements Serializable{
    @Id
    @Column(name = "nombre_aloja",length = 30)
    private String nombre_alojamiento;
    @Column(length = 45)
    private String direccion;
    @Column(length = 15)
    private String telefono;
    //@Column(name = "total_habitaciones")
    //@Column(columnDefinition = "integer(2)")
    private Integer total_habitaciones;
    
    //un alojamiento puede tener un personal de contacto
    @OneToOne
    @JoinColumn(name = "contacto",referencedColumnName = "codigo_p",nullable = true)
    private Personal contacto;
       //columnDefinition = "char(30) not null primary key")
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="alojamientoRural_actividad",
//            joinColumns = @JoinColumn(name="nombre_aloja",referencedColumnName = "nombre_aloja"),
//            inverseJoinColumns = @JoinColumn(name = "codigo_activ",referencedColumnName = "codigo_activ"))
//    private List<Actividad> actividades_multiaventura;
    

    public Alojamiento() {
    }

    public Alojamiento(String nombre_alojamiento) {
        this.nombre_alojamiento = nombre_alojamiento;
    }

    public String getNombre_alojamiento() {
        return nombre_alojamiento;
    }

    public void setNombre_alojamiento(String nombre_alojamiento) {
        this.nombre_alojamiento = nombre_alojamiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getTotal_habitaciones() {
        return total_habitaciones;
    }

    public void setTotal_habitaciones(Integer total_habitaciones) {
        this.total_habitaciones = total_habitaciones;
    }

    public Personal getContacto() {
        return contacto;
    }

    public void setContacto(Personal contacto) {
        this.contacto = contacto;
    }

//    public List<Actividad> getActividades_multiaventura() {
//        return actividades_multiaventura;
//    }
//
//    public void setActividades_multiaventura(List<Actividad> actividades_multiaventura) {
//        this.actividades_multiaventura = actividades_multiaventura;
//    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nombre_alojamiento);
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
        final Alojamiento other = (Alojamiento) obj;
        if (!Objects.equals(this.nombre_alojamiento, other.nombre_alojamiento)) {
            return false;
        }
        return true;
    }
    
    
    
}
