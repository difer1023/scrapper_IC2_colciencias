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
 * Clase que representa el producto Generación de contenido multimedia
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "GeneracionContenidoMultimedia",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class GeneracionContenidoMultimedia extends ProductoInvestigacion{
    
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "medioCirculacion")
    private String medioCirculacion;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "instituciones")
    private ArrayList<Institucion> instituciones;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "ambito")
    private String ambito;
    @XmlElement(name = "comunidades")
    private String comunidades;
    @XmlElement(name = "resumen")
    private String resumen;
    @XmlElement(name = "certificacion")
    private boolean certificacion;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMedioCirculacion() {
        return medioCirculacion;
    }

    public void setMedioCirculacion(String medioCirculacion) {
        this.medioCirculacion = medioCirculacion;
    }

    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
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

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getComunidades() {
		return comunidades;
	}

	public void setComunidades(String comunidades) {
		this.comunidades = comunidades;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public boolean isCertificacion() {
		return certificacion;
	}

	public void setCertificacion(boolean certificacion) {
		this.certificacion = certificacion;
	}
}
