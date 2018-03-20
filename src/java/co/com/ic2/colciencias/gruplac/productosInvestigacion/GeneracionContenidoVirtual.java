/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Investigador;
import java.util.ArrayList;

/**
 * Clase que representa el producto Generación de contenido virtual
 * @author L
 */
public class GeneracionContenidoVirtual extends ProductoInvestigacion{
    
    private int ano;
    private String fecha;
    private String medioCirculacion;
    private ArrayList<Investigador> autores;
    private ArrayList<Institucion> instituciones;
    private String tipo;
    
    private String comunidad;
    private String resumen;
    private String url;
    private boolean certificacion;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isCertificacion() {
		return certificacion;
	}

	public void setCertificacion(boolean certificacion) {
		this.certificacion = certificacion;
	}   
}
