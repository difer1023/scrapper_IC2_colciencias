/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Integrante;
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
    private ArrayList<Integrante> autores;
    private String tipo;
    
    private int numeroAutores;
    private String requisitosGuiaVerificacion;

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
    
    public ArrayList<Integrante> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Integrante> autores) {
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

    public String getRequisitosGuiaVerificacion() {
        return requisitosGuiaVerificacion;
    }

    public void setRequisitosGuiaVerificacion(String requisitosGuiaVerificacion) {
        this.requisitosGuiaVerificacion = requisitosGuiaVerificacion;
    }
}
