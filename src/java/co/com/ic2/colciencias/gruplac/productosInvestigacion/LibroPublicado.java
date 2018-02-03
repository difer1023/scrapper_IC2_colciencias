/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Integrante;
import java.util.ArrayList;

/**
 * Clase que representa el producto Libro
 * @author L
 */
public class LibroPublicado extends ProductoInvestigacion{
    private String isbn;
    private int ano;
    private String pais;
    private String editorial;
    private ArrayList<Integrante> autores;
    private String tipo;
    private String mes;
    private int numeroAutores;
    private String requisitosVerificacion;
    
    private String certificacionInstitucion;
    private String bookCitationIndex;
    private String referenciaRevistasD;
    private String referenciaRevistasA1;
    private int numeroReferenciasBCI;
    private int numeroReferenciasLibB;
    
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(int numeroAutores) {
        this.numeroAutores = numeroAutores;
    }

    public String getRequisitosVerificacion() {
        return requisitosVerificacion;
    }

    public void setRequisitosVerificacion(String requisitosVerificacion) {
        this.requisitosVerificacion = requisitosVerificacion;
    }

    public String getCertificacionInstitucion() {
        return certificacionInstitucion;
    }

    public void setCertificacionInstitucion(String certificacionInstitucion) {
        this.certificacionInstitucion = certificacionInstitucion;
    }

    public String getBookCitationIndex() {
        return bookCitationIndex;
    }

    public void setBookCitationIndex(String bookCitationIndex) {
        this.bookCitationIndex = bookCitationIndex;
    }

    public String getReferenciaRevistasD() {
        return referenciaRevistasD;
    }

    public void setReferenciaRevistasD(String referenciaRevistasD) {
        this.referenciaRevistasD = referenciaRevistasD;
    }

    public String getReferenciaRevistasA1() {
        return referenciaRevistasA1;
    }

    public void setReferenciaRevistasA1(String referenciaRevistasA1) {
        this.referenciaRevistasA1 = referenciaRevistasA1;
    }

    public int getNumeroReferenciasBCI() {
        return numeroReferenciasBCI;
    }

    public void setNumeroReferenciasBCI(int numeroReferenciasBCI) {
        this.numeroReferenciasBCI = numeroReferenciasBCI;
    }

    public int getNumeroReferenciasLibB() {
        return numeroReferenciasLibB;
    }

    public void setNumeroReferenciasLibB(int numeroReferenciasLibB) {
        this.numeroReferenciasLibB = numeroReferenciasLibB;
    }
}
