/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Institucion;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Red de conocimiento
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "RedConocimiento",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class RedConocimiento extends ProductoInvestigacion{
    @XmlElement(name = "lugar")
    private String lugar;
    @XmlElement(name = "fechaInicio")
    private String fechaInicio;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "investigador")
    private String investigador;
    @XmlElement(name = "comunidadesParticipantes")
    private int comunidadesParticipantes;
    @XmlElement(name = "pagWeb")
    private String pagWeb;
    
    @XmlElement(name = "nombreComunidad")
    private String nombreComunidad;
    @XmlElement(name = "liderRed")
    private boolean liderRed;
    
    @XmlElement(name = "instituciones")
    private ArrayList<Institucion> instituciones;
    

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

    public String getInvestigador() {
        return investigador;
    }

    public void setInvestigador(String investigador) {
        this.investigador = investigador;
    }

    public int getComunidadesParticipantes() {
        return comunidadesParticipantes;
    }

    public void setComunidadesParticipantes(int comunidadesParticipantes) {
        this.comunidadesParticipantes = comunidadesParticipantes;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public void setPagWeb(String pagWeb) {
        this.pagWeb = pagWeb;
    }

    public ArrayList<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(ArrayList<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

	public String getNombreComunidad() {
		return nombreComunidad;
	}

	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}

	public boolean isLiderRed() {
		return liderRed;
	}

	public void setLiderRed(boolean liderRed) {
		this.liderRed = liderRed;
	}
}
