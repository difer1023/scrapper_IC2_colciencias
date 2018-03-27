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
 * Clase que representa el producto Libro
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "LibroInvestigacion",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class LibroInvestigacion extends ProductoInvestigacion{
    
    @XmlElement(name = "isbn")
    private String isbn;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "editorial")
    private String editorial;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;
    @XmlElement(name = "mes")
    private String mes;
    @XmlElement(name = "numeroAutores")
    private int numeroAutores;
    @XmlElement(name = "requisitosGuiaVerificacion")
    private boolean requisitosGuiaVerificacion;
    
    @XmlElement(name = "certificacionInstitucion")
    private String certificacionInstitucion;
    @XmlElement(name = "bookCitationIndex")
    private String bookCitationIndex;
    @XmlElement(name = "referenciaRevistasD")
    private String referenciaRevistasD;
    @XmlElement(name = "referenciaRevistasA1")
    private String referenciaRevistasA1;
    @XmlElement(name = "numeroReferenciasBCI")
    private int numeroReferenciasBCI;
    @XmlElement(name = "numeroReferenciasLibB")
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
    
    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Investigador> autores) {
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

    public boolean getRequisitosVerificacion() {
        return requisitosGuiaVerificacion;
    }

    public void setRequisitosGuiaVerificacion(boolean requisitosGuiaVerificacion) {
        this.requisitosGuiaVerificacion = requisitosGuiaVerificacion;
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
