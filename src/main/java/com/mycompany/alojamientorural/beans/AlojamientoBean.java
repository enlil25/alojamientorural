/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.beans;

import com.mycompany.alojamientorural.entidades.Actividad;
import com.mycompany.alojamientorural.entidades.Alojamiento;
import com.mycompany.alojamientorural.entidades.Personal;
import com.mycompany.alojamientorural.servicios.ActividadService;
import com.mycompany.alojamientorural.servicios.AlojamientoService;
import com.mycompany.alojamientorural.servicios.PersonalService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author cesar
 */
@Named("alojamientobean")
@RequestScoped
@Transactional
public class AlojamientoBean {

    @Inject
    private AlojamientoService alojamientoService;
    @Inject
    private Alojamiento alojamiento;
    private String mensaje;

    @Inject
    private PersonalService personalService;
    private List<SelectItem> listSelectItems;
    //codigo de personal de contacto seleccionado
    private String contactoSeleccionado;

    //codigo de actividad seleccionado
    private String actividadSel;
    // listado selectItem para actividades
    private List<SelectItem> listSelectItemsActividad;

    @Inject
    private ActividadService actividadService;

    //para guardar el dia se la semana
    private String diaSemana;

    @PostConstruct
    private void init() {
        listSelectItems = new ArrayList();
        listSelectItemsActividad = new ArrayList();
        cargarPersonalContactos();
        cargarActividades();
    }

    private void cargarActividades() {
        List<Actividad> listadoActividades = actividadService.listAll();

        if (!listadoActividades.isEmpty()) {
            for (Actividad p : listadoActividades) {
                SelectItem sitem = new SelectItem();
                sitem.setLabel(p.getNombre());
                sitem.setValue(p.getCodigo_activ());
                listSelectItemsActividad.add(sitem);
            }
        }
    }

    private void cargarPersonalContactos() {
        List<Personal> listadoPersonal = personalService.listAll();
        //List<Personal> listadoPersonal = personalService.listarPersonalPorAlojamiento("");
        if (!listadoPersonal.isEmpty()) {
            for (Personal p : listadoPersonal) {
                SelectItem sitem = new SelectItem();
                sitem.setLabel(p.getNombre());
                sitem.setValue(p.getCodigo_p());
                listSelectItems.add(sitem);
            }
        }
    }

    private void cargarPersonalPorAlojamiento(String codigoaloja) {
        //List<Personal> listadoPersonal = personalService.listAll();
        List<Personal> listadoPersonal = personalService.listarPersonalPorAlojamiento(codigoaloja);
        if (!listadoPersonal.isEmpty()) {
            for (Personal p : listadoPersonal) {
                SelectItem sitem = new SelectItem();
                sitem.setLabel(p.getNombre());
                sitem.setValue(p.getCodigo_p());
                listSelectItems.add(sitem);
            }
        }
    }

    public void crearAlojamiento() {

        Alojamiento aloj = alojamientoService.find(alojamiento.getNombre_alojamiento());
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (aloj != null) {
            ctx.addMessage("formuA:nombre", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alojamiento ya existe", "Alojamiento '" + alojamiento.getNombre_alojamiento() + "' ya se encuentra registrado"));
        } else {
            alojamientoService.persist(alojamiento);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alojamiento fue creado", "Alojamiento " + alojamiento.getNombre_alojamiento() + "se creo correctamente"));
        }
    }

    public String vistaActualiza(String codigo) {

        //carga el personal por alojamiento --temporalmente
        // cargarPersonalPorAlojamiento(codigo);
        //buscamos la actividad y la enviamos a la pagina de edicion
        //Actividad buscado = actividadService.find(codigo);
        alojamiento = alojamientoService.find(codigo);

        if (alojamiento != null) {
            contactoSeleccionado = alojamiento.getContacto() == null ? "" : alojamiento.getContacto().getCodigo_p();
        }

        return "actualizarAlojamiento";
    }

    public String vistaElimina(String codigo) {
        alojamiento = alojamientoService.find(codigo);
        if (alojamiento != null && alojamiento.getContacto() != null) {
            contactoSeleccionado = alojamiento.getContacto().getNombre();
        } else {
            contactoSeleccionado = "";
        }
        return "eliminarAlojamiento";
    }

    public void actualizarAlojamiento() {

        //buscamos la actividad
        //si se selecciono un contacto
        //se busca ese contacto por el codigo pasado y se obtiene una referencia
        //para actualizarlo en alojamiento
        //sino se establece contacto en null
        //posteriormente el usuario editara
        if (contactoSeleccionado != null && contactoSeleccionado.length() > 0) {
            Personal personalbusc = personalService.find(contactoSeleccionado);
            alojamiento.setContacto(personalbusc);
        } else {
            alojamiento.setContacto(null);
        }

        alojamientoService.merge(alojamiento);
        mensaje = "Alojamiento fue actualizado";

    }

    public String eliminarAlojamiento() {

        //cuando al entidad esta marcada como detached 
        //para agregarlo nuevamente al contexto de persistencia
        //o la cache de primer nivel
        //puedo hacerlo de 2 formas
        //buscarlo por codigo y el obtenido se encuentra administrado
        // o hacer un merge antes 
        alojamientoService.remove(alojamientoService.merge(alojamiento));
        mensaje = "Alojamiento fue eliminado de la base de datos";
        return "/confirmacion";
    }

    public List<Alojamiento> listarAlojamientos() {

        return alojamientoService.listAll();
    }

    public List<SelectItem> getListSelectItems() {
        return listSelectItems;
    }

    public void setListSelectItems(List<SelectItem> listSelectItems) {
        this.listSelectItems = listSelectItems;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public String getContactoSeleccionado() {
        return contactoSeleccionado;
    }

    public void setContactoSeleccionado(String contactoSeleccionado) {
        this.contactoSeleccionado = contactoSeleccionado;
    }

    public String getActividadSel() {
        return actividadSel;
    }

    public void setActividadSel(String actividadSel) {
        this.actividadSel = actividadSel;
    }

    public List<SelectItem> getListSelectItemsActividad() {
        return listSelectItemsActividad;
    }

    public void setListSelectItemsActividad(List<SelectItem> listSelectItemsActividad) {
        this.listSelectItemsActividad = listSelectItemsActividad;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

}
