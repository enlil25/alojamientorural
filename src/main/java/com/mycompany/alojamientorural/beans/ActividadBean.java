/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.beans;

import com.mycompany.alojamientorural.entidades.Actividad;
import com.mycompany.alojamientorural.servicios.ActividadService;
import com.mycompany.alojamientorural.utiles.GeneradorCodigos;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author cesar
 */
@Named("actividadbean")
@RequestScoped
@Transactional
public class ActividadBean {

    @Inject
    private ActividadService actividadService;

    //actividad usada para insertar
    @Inject
    private Actividad actividad;

    @Inject
    private GeneradorCodigos generador;

    //private List<Actividad> actividades;
    private String mensaje;

    private List<Actividad> encontradosBusqueda;

    @PostConstruct
    private void init() {
        //actividades = new ArrayList<>();
    }

    public void crearActividad() {

        actividad.setCodigo_activ(generador.generarCodigoActividad());
        actividadService.persist(actividad);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad creada", "Actividad " + actividad.getNombre() + " creada correctamente"));

        //mensaje = "Actividad creada correctamente";
    }

    public String vistaActualiza(String codigo) {
        //buscamos la actividad y la enviamos a la pagina de edicion
        //Actividad buscado = actividadService.find(codigo);
        actividad = actividadService.find(codigo);
        return "actualizarActividad";
    }

    public String vistaElimina(String codigo) {
        actividad = actividadService.find(codigo);
        return "eliminarActividad";
    }

    public void actualizarActividad() {

        //buscamos la actividad
        actividadService.merge(actividad);
        mensaje = "Actividad fue actualizada";

//        Actividad buscado = actividadService.find();
//        if(buscado!=null){
//            buscado.setDescripcion(actividad.getDescripcion());
//            buscado.setNivel_dificultad(actividad.getNivel_dificultad());
//            buscado.setNombre(actividad.getNombre());
//            actividadService.merge(buscado);
//        }
    }

    public String eliminarActividad() {

        //cuando al entidad esta marcada como detached 
        //para agregarlo nuevamente al contexto de persistencia
        //o la cache de primer nivel
        //puedo hacerlo de 2 formas
        //buscarlo por codigo y el obtenido se encuentra administrado
        // o hacer un merge antes 
        actividadService.remove(actividadService.merge(actividad));
        mensaje = "Actividad fue eliminada de la base de datos";
        return "/confirmacion";
    }

    public List<Actividad> listarActividades() {
        //actividades = actividadService.listAll();
        // System.err.println(actividades.toString());
        //return actividades;
        //return "Actividad/listarActividades";
        return actividadService.listAll();
    }

    public List<Actividad> buscarActividad(String codigo, String nombre, String descripcion, Integer dificultad) {
        List<Actividad> encontrados = null;

        encontrados = actividadService.buscarPorCodigo(codigo);
        if (encontrados.isEmpty()) {
            encontrados = actividadService.buscarPorOtros(nombre, descripcion, dificultad);
        }
        return encontrados;
    }

    public List<Actividad> buscarActividad(Actividad actividad) {
        List<Actividad> encontrados = null;

        // System.err.println(actividad.getNombre() == null ? "nombre es null":"nombre es diferente a null");
        encontrados = actividadService.buscarPorCodigo(actividad.getCodigo_activ());
        if (encontrados.isEmpty()) {
            encontrados = actividadService.buscarPorOtros(actividad.getNombre(), actividad.getDescripcion(), actividad.getNivel_dificultad());
        }
        return encontrados;
    }

    public void buscarActividad() {

        encontradosBusqueda = buscarActividad(actividad);

        //return "home";
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

//    public List<Actividad> getActividades() {
//        return actividades;
//    }
//    
//    public void setActividades(List<Actividad> actividades) {
//        this.actividades = actividades;
//    }
    public String getMensaje() {
        return mensaje;
    }

    public List<Actividad> getEncontradosBusqueda() {
        return encontradosBusqueda;
    }

}
