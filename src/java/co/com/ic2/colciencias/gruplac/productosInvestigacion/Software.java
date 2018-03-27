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
 * Clase que representa el producto Software
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Software",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class Software extends ProductoInvestigacion{
    
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "disponibilidad")
    private String disponibilidad;
    @XmlElement(name = "sitioWeb")
    private String sitioWeb;
    @XmlElement(name = "nombreComercial")
    private String nombreComercial;
    @XmlElement(name = "nombreProyecto")
    private String nombreProyecto;
    @XmlElement(name = "institucion")
    private String institucion;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;
    @XmlElement(name = "registrosAsociados")
    private String registrosAsociados;
    
    @XmlElement(name = "registroDerechosAutor")
    private String registroDerechosAutor;
    @XmlElement(name = "descripcion")
    private String descripcion;
    @XmlElement(name = "codigoProyectoColciencias")
    private String codigoProyectoColciencias;
    @XmlElement(name = "certificacion")
    private boolean certificacion;

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

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
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

    public String getRegistrosAsociados() {
        return registrosAsociados;
    }

    public void setRegistrosAsociados(String registrosAsociados) {
        this.registrosAsociados = registrosAsociados;
    }

	public String getRegistroDerechosAutor() {
		return registroDerechosAutor;
	}

	public void setRegistroDerechosAutor(String registroDerechosAutor) {
		this.registroDerechosAutor = registroDerechosAutor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoProyectoColciencias() {
		return codigoProyectoColciencias;
	}

	public void setCodigoProyectoColciencias(String codigoProyectoColciencias) {
		this.codigoProyectoColciencias = codigoProyectoColciencias;
	}

	public boolean isCertificacion() {
		return certificacion;
	}

	public void setCertificacion(boolean certificacion) {
		this.certificacion = certificacion;
	}
}

