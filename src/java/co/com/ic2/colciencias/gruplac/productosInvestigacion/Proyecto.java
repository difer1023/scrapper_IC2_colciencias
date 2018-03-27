/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Proyecto
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Proyecto",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class Proyecto extends ProductoInvestigacion{
    
    @XmlElement(name = "anoInicio")
    private int anoInicio;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "institucion")
    private String institucion;
    @XmlElement(name = "numeroInvestigadoresPrincipales")
    private int numeroInvestigadoresPrincipales;
    @XmlElement(name = "actoAdministrativo")
    private String actoAdministrativo;
    @XmlElement(name = "fechaActoAdministrativo")
    private String fechaActoAdministrativo;
    @XmlElement(name = "numeroInvestigadoresParticipantes")
    private int numeroInvestigadoresParticipantes;
    
    @XmlElement(name = "anoFin")
    private int anoFin;
    
    @XmlElement(name = "financiacion")
    private String financiacion;
    @XmlElement(name = "actoAdmin")
    private boolean actoAdmin;
    @XmlElement(name = "numeroContrato")
    private String numeroContrato;
    @XmlElement(name = "tipoFinanciacion")
    private int tipoFinanciacion;

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public int getNumeroInvestigadoresPrincipales() {
        return numeroInvestigadoresPrincipales;
    }

    public void setNumeroInvestigadoresPrincipales(int numeroInvestigadoresPrincipales) {
        this.numeroInvestigadoresPrincipales = numeroInvestigadoresPrincipales;
    }

    public String getActoAdministrativo() {
        return actoAdministrativo;
    }

    public void setActoAdministrativo(String actoAdministrativo) {
        this.actoAdministrativo = actoAdministrativo;
    }

    public String getFechaActoAdministrativo() {
        return fechaActoAdministrativo;
    }

    public void setFechaActoAdministrativo(String fechaActoAdministrativo) {
        this.fechaActoAdministrativo = fechaActoAdministrativo;
    }

    public int getAnoFin() {
        return anoFin;
    }

    public void setAnoFin(int anoFin) {
        this.anoFin = anoFin;
    }

    public int getNumeroinvestigadoresParticipantes() {
        return numeroInvestigadoresParticipantes;
    }

    public void setNumeroinvestigadoresParticipantes(int numeroinvestigadoresParticipantes) {
        this.numeroInvestigadoresParticipantes = numeroinvestigadoresParticipantes;
    }

	public String getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(String financiacion) {
		this.financiacion = financiacion;
	}

	public boolean isActoAdmin() {
		return actoAdmin;
	}

	public void setActoAdmin(boolean actoAdmin) {
		this.actoAdmin = actoAdmin;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public int getTipoFinanciacion() {
		return tipoFinanciacion;
	}

	public void setTipoFinanciacion(int tipoFinanciacion) {
		this.tipoFinanciacion = tipoFinanciacion;
	}
}
