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
 * Clase que representa el producto Generación de contenido impreso
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "GeneracionContenidoImpreso",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class GeneracionContenidoImpreso extends ProductoInvestigacion{
    
    @XmlElement(name = "lugarPublicacion")
    private String lugarPublicacion;
    @XmlElement(name = "fecha")
    private String fecha;
    @XmlElement(name = "medioCirculacion")
    private String medioCirculacion;
    @XmlElement(name = "ambito")
    private String ambito;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "revista")
    private String revista;
    @XmlElement(name = "volumen")
    private String volumen;
    @XmlElement(name = "fasciculo")
    private String fasciculo;
    @XmlElement(name = "paginas")
    private String paginas;
    @XmlElement(name = "doi")
    private String doi;
    @XmlElement(name = "pagInicial")
    private String pagInicial;
    @XmlElement(name = "pagFinal")
    private String pagFinal;
    @XmlElement(name = "url")
    private String url;
    @XmlElement(name = "numeroAutores")
    private int numeroAutores;
    @XmlElement(name = "mes")
    private String mes;
    @XmlElement(name = "issn")
    private String issn;
    @XmlElement(name = "ano")
    private int ano;
    
    @XmlElement(name = "certificacion")
    private boolean certificacion;

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getFasciculo() {
        return fasciculo;
    }

    public void setFasciculo(String fasciculo) {
        this.fasciculo = fasciculo;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPagInicial() {
        return pagInicial;
    }

    public void setPagInicial(String pagInicial) {
        this.pagInicial = pagInicial;
    }

    public String getPagFinal() {
        return pagFinal;
    }

    public void setPagFinal(String pagFinal) {
        this.pagFinal = pagFinal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(int numeroAutores) {
        this.numeroAutores = numeroAutores;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public boolean isCertificacion() {
		return certificacion;
	}

	public void setCertificacion(boolean certificacion) {
		this.certificacion = certificacion;
	}   
}
