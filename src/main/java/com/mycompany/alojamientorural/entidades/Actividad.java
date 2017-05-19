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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author cesar
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Actividad.BUSCAR_POR_NOMBRE, query = "select p from Actividad p where p.nombre like '%:nombre%'"),
        @NamedQuery(name = Actividad.BUSCAR_POR_DESCRIPCION, query = "select p from Actividad p where p.descripcion like '%:descripcion%'"),
        @NamedQuery(name = Actividad.BUSCAR_POR_DIFICULTAD, query = "select p from Actividad p where p.nivel_dificultad = :dificultad"),
        @NamedQuery(name = Actividad.BUSCAR_POR_CODIGO, query = "select p from Actividad p where p.codigo_activ = :codigo"),
        @NamedQuery(name = Actividad.BUSCAR_POR_NOMBRE_DESCRIPCION_DIFICULTAD, query = "select p from Actividad p where ( p.nombre like :nombre and p.descripcion like :descripcion and p.nivel_dificultad=:dificultad )")
        })    
public class Actividad implements Serializable {

    @Id
    @Column(length = 2)
    private String codigo_activ;
    @Column(length = 30)
    private String nombre;
    @Column(length = 70)
    private String descripcion;
    @Column(name = "dificultad")
    private Integer nivel_dificultad;

//    @ManyToMany(fetch=FetchType.LAZY , mappedBy = "actividades_multiaventura")
//    private List<Alojamiento> alojamientos;
    public Actividad() {
    }

    public Actividad(String codigo_activ, String nombre) {
        this.codigo_activ = codigo_activ;
        this.nombre = nombre;
    }

    public final static String BUSCAR_POR_NOMBRE = "Actividad.BuscarPorNombre";
    public final static String BUSCAR_POR_DESCRIPCION = "Actividad.BuscarPorDescripcion";
    public final static String BUSCAR_POR_DIFICULTAD = "Actividad.BuscarPorDificultad";
    public final static String BUSCAR_POR_CODIGO = "Actividad.BuscarPorCodigo";
    public final static String BUSCAR_POR_NOMBRE_DESCRIPCION_DIFICULTAD = "Actividad.BuscarPorOtros";

    public String getCodigo_activ() {
        return codigo_activ;
    }

    public void setCodigo_activ(String codigo_activ) {
        this.codigo_activ = codigo_activ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNivel_dificultad() {
        return nivel_dificultad;
    }

    public void setNivel_dificultad(Integer nivel_dificultad) {
        this.nivel_dificultad = nivel_dificultad;
    }

//    public List<Alojamiento> getAlojamientos() {
//        return alojamientos;
//    }
//
//    public void setAlojamientos(List<Alojamiento> alojamientos) {
//        this.alojamientos = alojamientos;
//    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigo_activ);
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
        final Actividad other = (Actividad) obj;
        return Objects.equals(this.codigo_activ, other.codigo_activ);
    }

    @Override
    public String toString() {
        return "Actividad{" + "codigo_activ=" + codigo_activ + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel_dificultad=" + nivel_dificultad + '}';
    }

}
