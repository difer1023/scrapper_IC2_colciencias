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
 * Clase que representa el producto Otro artículo publicado
 * Presente en la parte pública del Gruplac
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "OtroArticuloPublicado",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class OtroArticuloPublicado extends ProductoInvestigacion{
    
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "issn")
    private String issn;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "tipo")
    private String tipo;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
    
}

