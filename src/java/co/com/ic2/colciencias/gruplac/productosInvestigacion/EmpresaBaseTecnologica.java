/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Investigador;
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
    private ArrayList<Investigador> autores;
    private String tipo;
    private int ano;
    private boolean certificadoCamaraComercio;
    private boolean certificacionInstitucional;
    
    private int tipoIdentificacion;

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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean getCertificadoCamaraComercio() {
        return certificadoCamaraComercio;
    }

    public void setCertificadoCamaraComercio(boolean certificadoCamaraComercio) {
        this.certificadoCamaraComercio = certificadoCamaraComercio;
    }

    public boolean getCertificacionInstitucional() {
        return certificacionInstitucional;
    }

    public void setCertificacionInstitucional(boolean certificacionInstitucional) {
        this.certificacionInstitucional = certificacionInstitucional;
    }

	public int getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(int tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
}
