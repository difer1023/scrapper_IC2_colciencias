/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.privado;

import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Difer
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ExtraerGrupoInvestigacionObject",namespace = "privado.scrapper.colciencias.ic2.com.co")
public class ExtraerGrupoInvestigacionObject {
    
    @XmlElement(name = "respuesta")
    private int respuesta;
    @XmlElement(name = "grupoInvestigacion")
    private GrupoInvestigacion grupoInvestigacion;
    
    @XmlElement(name = "gruposInvestigacion")
    private ArrayList<String> gruposInvestigacion;

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public GrupoInvestigacion getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }

    public ArrayList<String> getGruposInvestigacion() {
        return gruposInvestigacion;
    }

    public void setGruposInvestigacion(ArrayList<String> gruposInvestigacion) {
        this.gruposInvestigacion = gruposInvestigacion;
    }

    
}
