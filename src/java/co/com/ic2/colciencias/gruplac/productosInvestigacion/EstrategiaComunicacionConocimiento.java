/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Estrategia de comunicación del conocimiento
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "EstrategiaComunicacionConocimiento",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class EstrategiaComunicacionConocimiento extends ProductoInvestigacion{
    
    @XmlElement(name = "anoInicio")
    private int anoInicio;
    @XmlElement(name = "descripcion")
    private String descripcion;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "lider")
    private String lider;
    @XmlElement(name = "nombreComunidad")
    private String nombreComunidad;
    @XmlElement(name = "certificacion")
    private boolean certificacion;

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
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

	public String getLider() {
		return lider;
	}

	public void setLider(String lider) {
		this.lider = lider;
	}

	public String getNombreComunidad() {
		return nombreComunidad;
	}

	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}

	public boolean isCertificacion() {
		return certificacion;
	}

	public void setCertificacion(boolean certificacion) {
		this.certificacion = certificacion;
	}
}
