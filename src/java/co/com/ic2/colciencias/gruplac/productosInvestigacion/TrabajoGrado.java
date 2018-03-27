/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Investigador;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Trabajo dirigido
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "TrabajoGrado",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class TrabajoGrado extends ProductoInvestigacion{
    
    @XmlElement(name = "tipo")
    private String tipo;
    @XmlElement(name = "lugar")
    private String lugar;
    @XmlElement(name = "anoFin")
    private int anoFin;
    @XmlElement(name = "valoracion")
    private String valoracion;
    @XmlElement(name = "institucion")
    private String institucion;

    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "autorTrabajo")
    private String autorTrabajo;
    
    @XmlElement(name = "tipoDireccion")
    private String tipoDireccion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getAnoFin() {
        return anoFin;
    }

    public void setAnoFin(int anoFin) {
        this.anoFin = anoFin;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
    }   

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getAutorTrabajo() {
        return autorTrabajo;
    }

    public void setAutorTrabajo(String autorTrabajo) {
        this.autorTrabajo = autorTrabajo;
    }
}
