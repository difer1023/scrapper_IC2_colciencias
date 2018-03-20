/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Investigador;
import java.util.ArrayList;

/**
 * Clase que representa el producto Capítulo de libro
 * @author L
 */
public class CapituloLibroPublicado extends ProductoInvestigacion {
    private String isbn;
    private int ano;
    private String pais;
    private String tituloLibro;
    private String editorial;
    private ArrayList<Investigador> autores;
    private String tipo;
    
    private int numeroAutores;
    private boolean requisitosGuiaVerificacion;
    private boolean certificadoValidacion;
    
    private String tituloCapitulo;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
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

    public int getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(int numeroAutores) {
        this.numeroAutores = numeroAutores;
    }

    public boolean getRequisitosGuiaVerificacion() {
        return requisitosGuiaVerificacion;
    }

    public void setRequisitosGuiaVerificacion(boolean requisitosGuiaVerificacion) {
        this.requisitosGuiaVerificacion = requisitosGuiaVerificacion;
    }

	public String getTituloCapitulo() {
		return tituloCapitulo;
	}

	public void setTituloCapitulo(String tituloCapitulo) {
		this.tituloCapitulo = tituloCapitulo;
	}

	public boolean isCertificadoValidacion() {
		return certificadoValidacion;
	}

	public void setCertificadoValidacion(boolean certificadoValidacion) {
		this.certificadoValidacion = certificadoValidacion;
	}
	
}
