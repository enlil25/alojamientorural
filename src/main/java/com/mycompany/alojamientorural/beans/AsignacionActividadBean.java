/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.beans;

import com.mycompany.alojamientorural.entidades.Actividad;
import com.mycompany.alojamientorural.entidades.Alojamiento;
import com.mycompany.alojamientorural.entidades.Alojamiento_actividad;
import com.mycompany.alojamientorural.entidades.Alojamiento_actividad_PK;
import com.mycompany.alojamientorural.entidades.Habitacion;
import com.mycompany.alojamientorural.servicios.ActividadAlojamientoService;
import com.mycompany.alojamientorural.servicios.ActividadService;
import com.mycompany.alojamientorural.servicios.AlojamientoService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author cesar
 */
@Named("asignaactividadbean")
@RequestScoped
@Transactional
public class AsignacionActividadBean {

    @Inject
    private AlojamientoService alojamientoService;
    @Inject
    private ActividadService actividadService;

    private String codigoaloja;

    private List<SelectItem> selectAlojamientos;

    private List<Alojamiento_actividad> asignaciones;

    private String mensaje;

    private String diaSemana;
    private List<SelectItem> selectActividades;
    private String actividadSel;
    private String alojaSel;

    @Inject
    private ActividadAlojamientoService actividadAlojamientoService;
    private static final Logger LOG = Logger.getLogger(AsignacionActividadBean.class.getName());

    @PostConstruct
    private void init() {
        selectAlojamientos = new ArrayList();
        selectActividades = new ArrayList();
        cargarSelectAlojamientos();
        cargarSelectActividades();

    }

    private void cargarSelectAlojamientos() {
        List<Alojamiento> alojamientos = alojamientoService.listAll();
        for (Alojamiento aloja : alojamientos) {
            SelectItem si = new SelectItem();
            si.setLabel(aloja.getNombre_alojamiento());
            si.setValue(aloja.getNombre_alojamiento());
            selectAlojamientos.add(si);
        }
    }

    private void cargarSelectActividades() {
        List<Actividad> actividades = actividadService.listAll();
        for (Actividad actividad : actividades) {
            SelectItem si = new SelectItem();
            si.setLabel(actividad.getNombre());
            si.setValue(actividad.getCodigo_activ());
            selectActividades.add(si);
        }
    }

    public void verAsignaciones() {

        LOG.log(Level.SEVERE, "---VER ASIGNACIONES YA !!!!");
        if (codigoaloja != null && codigoaloja.length() > 0) {
            asignaciones = alojamientoService.buscarAsignacionesPorAlojamiento(codigoaloja);

            if (asignaciones.isEmpty()) {
                mensaje = "aun no tiene asignaciones este alojamiento";
            }
        } else {
            mensaje = "Seleccione un alojamiento";
        }

    }

    public void asignar() {

        if (alojaSel != null && !alojaSel.isEmpty() && diaSemana != null && !diaSemana.isEmpty() && actividadSel != null && !actividadSel.isEmpty()) {

            Alojamiento alojamiento = alojamientoService.find(alojaSel);
            Actividad actividad = actividadService.find(actividadSel);

            Alojamiento_actividad aloac = new Alojamiento_actividad();
            Alojamiento_actividad_PK aloacpk = new Alojamiento_actividad_PK();
            aloacpk.setActividad(actividad);
            aloacpk.setAlojamiento(alojamiento);
            aloac.setId(aloacpk);
            aloac.setDiaSemana(diaSemana);

            actividadAlojamientoService.merge(aloac);
            mensaje = "actividad ha sido asignada";

        } else {
            mensaje = "Debe seleccionar correctamente";
        }
    }

    public String vistaEliminar(String codaloja, String codactiv) {

//        Alojamiento alojbusc = alojamientoService.find(codaloja);
//        Actividad actbusc = actividadService.find(codactiv);
//        Alojamiento_actividad aloact = new Alojamiento_actividad();
//        Alojamiento_actividad_PK aloact_pk = new Alojamiento_actividad_PK();
//        aloact_pk.setActividad(actbusc);
//        aloact_pk.setAlojamiento(alojbusc);
//        aloact.setId(aloact_pk);
//        
//        actividadAlojamientoService.remove(aloact);
//        
//        
//        
        return "eliminarAsignacion";
    }

    public void eliminarAsignacion(Alojamiento_actividad_PK id) {

        Alojamiento_actividad aloactbusc = actividadAlojamientoService.find(id);
        System.err.println("mi error :" + aloactbusc.getId().getActividad().getCodigo_activ());
        System.err.println("mi error :" + aloactbusc.getId().getAlojamiento().getNombre_alojamiento());

        LOG.log(Level.SEVERE, "codactividad:" + aloactbusc.getId().getActividad().getCodigo_activ());
        LOG.log(Level.SEVERE, "nomalojamiento:" + aloactbusc.getId().getAlojamiento().getNombre_alojamiento());
        
        if (aloactbusc != null) {
            actividadAlojamientoService.remove(aloactbusc);
            mensaje = "asignacion eliminada";
        } else {
            mensaje = "asignacion que intenta eliminar no existe en la base de datos";
        }
    }

    public void eliminarAsignacion(String codaloja, String codactiv) {
        Alojamiento alojbusc = alojamientoService.find(codaloja);
        Actividad actbusc = actividadService.find(codactiv);
        Alojamiento_actividad aloact = new Alojamiento_actividad();
        Alojamiento_actividad_PK aloact_pk = new Alojamiento_actividad_PK();
        aloact_pk.setActividad(actbusc);
        aloact_pk.setAlojamiento(alojbusc);
        aloact.setId(aloact_pk);

        Alojamiento_actividad aloactbusc = actividadAlojamientoService.find(aloact_pk);
        if (aloactbusc != null) {
            actividadAlojamientoService.remove(aloact);
            mensaje = "asignacion eliminada";
        } else {
            mensaje = "asignacion no encontrada";
        }

    }

    public String getCodigoaloja() {
        return codigoaloja;
    }

    public void setCodigoaloja(String codigoaloja) {
        this.codigoaloja = codigoaloja;
    }

    public List<SelectItem> getSelectAlojamientos() {
        return selectAlojamientos;
    }

    public void setSelectAlojamientos(List<SelectItem> selectAlojamientos) {
        this.selectAlojamientos = selectAlojamientos;
    }

    public List<Alojamiento_actividad> getAsignaciones() {
        return asignaciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public List<SelectItem> getSelectActividades() {
        return selectActividades;
    }

    public void setSelectActividades(List<SelectItem> selectActividades) {
        this.selectActividades = selectActividades;
    }

    public String getActividadSel() {
        return actividadSel;
    }

    public void setActividadSel(String actividadSel) {
        this.actividadSel = actividadSel;
    }

    public String getAlojaSel() {
        return alojaSel;
    }

    public void setAlojaSel(String alojaSel) {
        this.alojaSel = alojaSel;
    }

}
