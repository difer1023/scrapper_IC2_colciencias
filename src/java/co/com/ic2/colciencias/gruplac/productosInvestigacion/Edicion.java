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
 * Clase que representa el producto Edición
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Edicion",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class Edicion extends ProductoInvestigacion{
    
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "editorial")
    private String editorial;
    @XmlElement(name = "idiomas")
    private String idiomas;
    @XmlElement(name = "numPaginas")
    private String numPaginas;
    @XmlElement(name = "autor")
    private Investigador autor;
    @XmlElement(name = "tipo")
    private String tipo;
    
    
    @XmlElement(name = "isbn_issn")
    private String isbn_issn;
    @XmlElement(name = "fechaEdicion")
    private String fechaEdicion;
    @XmlElement(name = "url")
    private String url;

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

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(String numPaginas) {
        this.numPaginas = numPaginas;
    }

    public Investigador getAutor() {
        return autor;
    }

    public void setAutor(Investigador autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public String getIssn_Isbn() {
		return isbn_issn;
	}

	public void setIssn_Isbn(String isbn_issn) {
		this.isbn_issn = isbn_issn;
	}
        
	public String getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(String fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
