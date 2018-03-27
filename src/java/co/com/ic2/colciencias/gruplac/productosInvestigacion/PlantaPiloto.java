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
 * Clase que representa el producto Planta piloto
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "PlantaPiloto",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class PlantaPiloto extends ProductoInvestigacion{
    
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "disponibilidad")
    private String disponibilidad;
    @XmlElement(name = "nombreComercial")
    private String nombreComercial;
    @XmlElement(name = "institucion")
    private String institucion;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "copiaContrato")
    private boolean copiaContrato;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public boolean isCopiaContrato() {
		return copiaContrato;
	}

	public void setCopiaContrato(boolean copiaContrato) {
		this.copiaContrato = copiaContrato;
	}   
}


