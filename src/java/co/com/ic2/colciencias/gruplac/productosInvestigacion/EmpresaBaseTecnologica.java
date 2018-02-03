/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Integrante;
import java.util.ArrayList;

/**
 * Clase que representa el producto Empresa de base tecnológica
 * @author L
 */
public class EmpresaBaseTecnologica extends ProductoInvestigacion{
    
    private String fecha;
    private String nit;
    private String fechaRegistro;
    private String estado;
    private ArrayList<Integrante> autores;
    private String tipo;
    private int ano;
    private String certificadoCamaraComercio;
    private String certificacionInstitucional;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCertificadoCamaraComercio() {
        return certificadoCamaraComercio;
    }

    public void setCertificadoCamaraComercio(String certificadoCamaraComercio) {
        this.certificadoCamaraComercio = certificadoCamaraComercio;
    }

    public String getCertificacionInstitucional() {
        return certificacionInstitucional;
    }

    public void setCertificacionInstitucional(String certificacionInstitucional) {
        this.certificacionInstitucional = certificacionInstitucional;
    }
    
    
}
