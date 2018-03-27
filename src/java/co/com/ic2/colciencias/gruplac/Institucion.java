/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Institucion",namespace = "gruplac.colciencias.ic2.com.co")
public class Institucion {
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "avalado")
    private boolean avalado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAvalado() {
        return avalado;
    }

    public void setAvalado(boolean avalado) {
        this.avalado = avalado;
    }
}
