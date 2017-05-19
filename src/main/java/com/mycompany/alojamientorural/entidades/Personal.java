/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author cesar
 */
@Entity
public class Personal implements Serializable{
    @Id
    @Column(length = 3)
    private String codigo_p;
    @Column(length = 50)
    private String nombre;
    @Column(length = 45)
    private String direccion;
    @Column(length = 8,unique = true)
    private String NIF;
    
    @OneToOne
    @JoinColumn(name="nombre_aloja",referencedColumnName = "nombre_aloja",nullable = false)
    private Alojamiento alojamiento;

    public Personal() {
    }

    public Personal(String codigo_p, String nombre) {
        this.codigo_p = codigo_p;
        this.nombre = nombre;
    }

    public String getCodigo_p() {
        return codigo_p;
    }

    public void setCodigo_p(String codigo_p) {
        this.codigo_p = codigo_p;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codigo_p);
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
        final Personal other = (Personal) obj;
        return Objects.equals(this.codigo_p, other.codigo_p);
    }

    @Override
    public String toString() {
        return "Personal{" + "codigo_p=" + codigo_p + ", nombre=" + nombre + ", direccion=" + direccion + ", NIF=" + NIF + ", alojamiento=" + alojamiento + '}';
    }
    
    
    
}
