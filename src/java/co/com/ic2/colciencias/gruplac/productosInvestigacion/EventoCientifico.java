/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import java.util.ArrayList;

import co.com.ic2.colciencias.gruplac.Institucion;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Evento científico
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "EventoCientifico",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class EventoCientifico extends ProductoInvestigacion{
    
    @XmlElement(name = "lugar")
    private String lugar;
    @XmlElement(name = "fechaInicio")
    private String fechaInicio;
    @XmlElement(name = "fechaFin")
    private String fechaFin;
    @XmlElement(name = "ambito")
    private String ambito;
    @XmlElement(name = "tipoParticipacion")
    private String tipoParticipacion;
    @XmlElement(name = "instituciones")
    private ArrayList<Institucion> instituciones;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "numeroAutores")
    private int numeroAutores;

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getTipoParticipacion() {
        return tipoParticipacion;
    }

    public void setTipoParticipacion(String tipoParticipacion) {
        this.tipoParticipacion = tipoParticipacion;
    }

    public ArrayList<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(ArrayList<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(int numeroAutores) {
        this.numeroAutores = numeroAutores;
    }

    
}
