/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Investigador;
import java.util.ArrayList;

/**
 * Clase que reperesenta el producto Artículo de investigación
 * @author L
 */
public class ArticuloInvestigacion extends ProductoInvestigacion{
    private String pais;
    private String issn;
    private int ano;
    private String tipo;
    private ArrayList<Investigador> autores;
    private String revista;
    private String volumen;
    private String fasciculo;
    private String paginas;
    private String doi;
    private String pagInicial;
    private String pagFinal;
    private String url;
    private int numeroAutores;
    private String mes;

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
}
