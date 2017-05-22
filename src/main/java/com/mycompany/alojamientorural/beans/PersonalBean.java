/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alojamientorural.beans;

import com.mycompany.alojamientorural.entidades.Alojamiento;
import com.mycompany.alojamientorural.entidades.Personal;
import com.mycompany.alojamientorural.servicios.AlojamientoService;
import com.mycompany.alojamientorural.servicios.PersonalService;
import com.mycompany.alojamientorural.utiles.GeneradorCodigos;
import java.util.ArrayList;
import java.util.List;
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
@Named("personalbean")
@RequestScoped
@Transactional
public class PersonalBean {

    @Inject
    private PersonalService personalService;
    @Inject
    private Personal personal;
    private String mensaje;

    @Inject
    private AlojamientoService alojamientoService;
    private List<SelectItem> listSelectItems;
    private String alojamientoSeleccionado;

    @Inject
    private GeneradorCodigos generador;

    @PostConstruct
    private void init() {
        listSelectItems = new ArrayList();
        cargarAlojamientos();
        
        //genera el codigo de personal al cargar
        personal.setCodigo_p(generador.generarCodigoPersonal());
    }

    private void cargarAlojamientos() {
        List<Alojamiento> listadoAlojamientos = alojamientoService.listAll();
        if (!listadoAlojamientos.isEmpty()) {
            for (Alojamiento p : listadoAlojamientos) {
                SelectItem sitem = new SelectItem();
                sitem.setLabel(p.getNombre_alojamiento());
                sitem.setValue(p.getNombre_alojamiento());
                listSelectItems.add(sitem);
            }
        }
    }

    public void crearPersonal() {

        //como el codigo es autogenerado debo seterlo
        //personal.setCodigo_p(generador.generarCodigoPersonal());

       
        //y si tiene el alojamiento seleccionado
        if (alojamientoSeleccionado != null && alojamientoSeleccionado.length() > 0) {
            Alojamiento alojabusc = alojamientoService.find(alojamientoSeleccionado);
            personal.setAlojamiento(alojabusc);
            personalService.persist(personal);
            mensaje = "Personal creado correctamente";
        } else {
            //en este caso el personal debe trabajar obligatoriamente en un alojamiento
            //y no se puede postergar en la edicion
            personal.setAlojamiento(null);
            mensaje = "No se puede crear personal ,Debe seleccionar un alojamiento";
        }

    }

    public String vistaActualiza(String codigo) {
        //buscamos la actividad y la enviamos a la pagina de edicion
        //Actividad buscado = actividadService.find(codigo);
        personal = personalService.find(codigo);
        if (personal != null) {
            alojamientoSeleccionado = personal.getAlojamiento() == null ? "" : personal.getAlojamiento().getNombre_alojamiento();
        }

        return "actualizarPersonal";
    }

    public String vistaElimina(String codigo) {
        personal = personalService.find(codigo);
        if (personal != null && personal.getAlojamiento() != null) {
            alojamientoSeleccionado = personal.getAlojamiento().getNombre_alojamiento();
        } else {
            alojamientoSeleccionado = "";
        }
        return "eliminarPersonal";
    }

    public void actualizarPersonal() {

        //buscamos la actividad
        //si se selecciono un contacto
        //se busca ese contacto por el codigo pasado y se obtiene una referencia
        //para actualizarlo en alojamiento
        //sino se establece contacto en null
        //posteriormente el usuario editara
        if (alojamientoSeleccionado != null && alojamientoSeleccionado.length() > 0) {
            Alojamiento alojabusc = alojamientoService.find(alojamientoSeleccionado);
            personal.setAlojamiento(alojabusc);
            personalService.merge(personal);
            mensaje = "Personal fue actualizado";
        } else {
            personal.setAlojamiento(null);
            mensaje = "Debe seleccionar un alojamiento porque es obligatorio";
        }

    }

    public String eliminarPersonal() {

        //cuando al entidad esta marcada como detached 
        //para agregarlo nuevamente al contexto de persistencia
        //o la cache de primer nivel
        //puedo hacerlo de 2 formas
        //buscarlo por codigo y el obtenido se encuentra administrado
        // o hacer un merge antes 
        personalService.remove(personalService.merge(personal));
        mensaje = "Personal fue eliminado de la base de datos";
        return "/confirmacion";
    }

    public List<Personal> listarPersonal() {

        return personalService.listAll();
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<SelectItem> getListSelectItems() {
        return listSelectItems;
    }

    public void setListSelectItems(List<SelectItem> listSelectItems) {
        this.listSelectItems = listSelectItems;
    }

    public String getAlojamientoSeleccionado() {
        return alojamientoSeleccionado;
    }

    public void setAlojamientoSeleccionado(String alojamientoSeleccionado) {
        this.alojamientoSeleccionado = alojamientoSeleccionado;
    }

}
