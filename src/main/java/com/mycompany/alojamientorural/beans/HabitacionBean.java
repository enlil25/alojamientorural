/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.beans;

import com.mycompany.alojamientorural.entidades.Alojamiento;
import com.mycompany.alojamientorural.entidades.Habitacion;
import com.mycompany.alojamientorural.entidades.HabitacionId;
import com.mycompany.alojamientorural.servicios.AlojamientoService;
import com.mycompany.alojamientorural.servicios.HabitacionService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author cesar
 */
@Named("habitacionbean")
@RequestScoped
@Transactional
public class HabitacionBean {
    
    @Inject
    private HabitacionService habitacionService;
    @Inject
    private Habitacion habitacion;
    private String mensaje;
    
    //codigo de alojamiento seleccionado
    private String alojaSel;
    //codigo de habitacion enviado
    private Integer numHabita;
    
    @Inject
    private AlojamientoService alojamientoService;
    
    
    public List<Habitacion> listarHabitaciones(){
        
       return habitacionService.listAll();
      
    }
    
    public String vistaActualiza(HabitacionId id){
        
        habitacion = habitacionService.find(id);
        
        return "actualizarHabitacion";
    }
    
    public String vistaElimina(HabitacionId id){
        
        habitacion = habitacionService.find(id);
        
       return "eliminarHabitacion";    
    }
    
    public void actualizar(){
        
        habitacionService.merge(habitacion);
        mensaje = "habitacion ha sido actualizada";
    }
    
    public String eliminar(){
       
        System.err.println(habitacion.getId().getAlojamiento().getNombre_alojamiento());
        System.err.println(habitacion.getId().getNumero_habitacion());
        habitacionService.remove( habitacionService.merge(habitacion));
        
        mensaje = "habitacion fue eliminada";
        
        return "/confirmacion";
        
    }
    
    
    public List<SelectItem> cargarSelectAlojamientos(){
        List<SelectItem> selectAloja = new ArrayList();
        List<Alojamiento> alojamientos = alojamientoService.listAll();
        for(Alojamiento aloj: alojamientos){
            SelectItem si = new SelectItem();
            si.setLabel(aloj.getNombre_alojamiento());
            si.setValue(aloj.getNombre_alojamiento());
            selectAloja.add(si);
        }
        return selectAloja;
    }
    
    public void crearHabitacion(){
        
        Habitacion busc = habitacionService.find(habitacion.getId());
        if(busc!=null){
            mensaje = "habitacion ya existe";
        }else{
            habitacionService.persist(habitacion);
            mensaje = "habitacion fue creada";
        }
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getAlojaSel() {
        return alojaSel;
    }

    public void setAlojaSel(String alojaSel) {
        this.alojaSel = alojaSel;
    }

    public String getMensaje() {
        return mensaje;
    }

    
    
}
