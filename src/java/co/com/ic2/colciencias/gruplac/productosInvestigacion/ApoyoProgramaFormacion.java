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
 *Clase que representa el producto Apoyo a programas de formación
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ApoyoProgramaFormacion",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class ApoyoProgramaFormacion extends ProductoInvestigacion{
    
    @XmlElement(name = "actoAdministrativo")
    private String actoAdministrativo;
    @XmlElement(name = "fechaActoAdministrativo")
    private String fechaActoAdministrativo;
    @XmlElement(name = "institucion")
    private String institucion;
    @XmlElement(name = "programaSeleccionado")
    private String programaSeleccionado;   
    @XmlElement(name = "ano")
    private int ano;
    
    @XmlElement(name = "facultad")
    private String facultad;
    @XmlElement(name = "departamento")
    private String departamento;
    @XmlElement(name = "numActoAdmin")
    private String numActoAdmin;
    @XmlElement(name = "tipoApoyo")
    private int tipoApoyo;

    
    public String getActoAdministrativo() {
        return actoAdministrativo;
    }

    public void setActoAdministrativo(String actoAdministrativo) {
        this.actoAdministrativo = actoAdministrativo;
    }

    public String getFechaActoAdministrativo() {
        return fechaActoAdministrativo;
    }

    public void setFechaActoAdministrativo(String fechaActoAdministrativo) {
        this.fechaActoAdministrativo = fechaActoAdministrativo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getProgramaSeleccionado() {
        return programaSeleccionado;
    }

    public void setProgramaSeleccionado(String programaSeleccionado) {
        this.programaSeleccionado = programaSeleccionado;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFacultad() {
        return facultad;
    }
    
	public void setFacultad(String facultad) {
		this.facultad = facultad;	
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNumActoAdmin() {
		return numActoAdmin;
	}

	public void setNumActoAdmin(String numActoAdmin) {
		this.numActoAdmin = numActoAdmin;
	}

	public int getTipoApoyo() {
		return tipoApoyo;
	}

	public void setTipoApoyo(int tipoApoyo) {
		this.tipoApoyo = tipoApoyo;
	}
}
