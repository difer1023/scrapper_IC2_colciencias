/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Integrante;
import java.util.ArrayList;

/**
 * Clase que representa el producto Software
 * @author L
 */
public class Software extends ProductoInvestigacion{
    
    private String pais;
    private int ano;
    private String disponibilidad;
    private String sitioWeb;
    private String nombreComercial;
    private String nombreProyecto;
    private String institucion;
    private ArrayList<Integrante> autores;
    private String tipo;
    private String registrosAsociados;

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

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
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

    public String getRegistrosAsociados() {
        return registrosAsociados;
    }

    public void setRegistrosAsociados(String registrosAsociados) {
        this.registrosAsociados = registrosAsociados;
    }

}

