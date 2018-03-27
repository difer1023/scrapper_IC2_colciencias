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
 * Clase que representa el producto Empresa de base tecnológica
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "EmpresaBaseTecnologica",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class EmpresaBaseTecnologica extends ProductoInvestigacion{
    
    @XmlElement(name = "fecha")
    private String fecha;
    @XmlElement(name = "nit")
    private String nit;
    @XmlElement(name = "fechaRegistro")
    private String fechaRegistro;
    @XmlElement(name = "estado")
    private String estado;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "certificadoCamaraComercio")
    private boolean certificadoCamaraComercio;
    @XmlElement(name = "certificacionInstitucional")
    private boolean certificacionInstitucional;
    
    @XmlElement(name = "tipoIdentificacion")
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
