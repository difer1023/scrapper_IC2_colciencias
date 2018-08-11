/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Investigador;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Estrategia pedagogica para el fomento de CTI
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "EstrategiaPedagogicaFomentoCTI",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class EstrategiaPedagogicaFomentoCTI extends ProductoInvestigacion{
    
    @XmlElement(name = "fechaInicio")
    private String fechaInicio;
    @XmlElement(name = "descripcion")
    private String descripcion;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "instituciones")
    private ArrayList<Institucion> instituciones;
    @XmlElement(name = "numeroInvestigadoresPrincipales")
    private int numeroInvestigadoresPrincipales;
    
    @XmlElement(name = "investigador")
    private Investigador investigador;
    @XmlElement(name = "certificado")
    private boolean certificado;

    public Investigador getInvestigador() {
		return investigador;
	}

	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	}

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String anoInicio) {
        this.fechaInicio = anoInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(ArrayList<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

    public int getNumeroInvestigadoresPrincipales() {
        return numeroInvestigadoresPrincipales;
    }

    public void setNumeroInvestigadoresPrincipales(int numeroInvestigadoresPrincipales) {
        this.numeroInvestigadoresPrincipales = numeroInvestigadoresPrincipales;
    }
}

