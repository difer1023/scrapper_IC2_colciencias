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
 * Clase que representa el producto Signo distintivo
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SignoDistintivo",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class SignoDistintivo extends ProductoInvestigacion{
    
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "numRegistro")
    private String numRegistro;
    @XmlElement(name = "nombreTitular")
    private String nombreTitular;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "mes")
    private String mes;
    @XmlElement(name = "certificado")
    private boolean certificado;
    @XmlElement(name = "condicionesUso")
    private String condicionesUso;

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

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
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

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	public String getCondicionesUso() {
		return condicionesUso;
	}

	public void setCondicionesUso(String condicionesUso) {
		this.condicionesUso = condicionesUso;
	} 
}

