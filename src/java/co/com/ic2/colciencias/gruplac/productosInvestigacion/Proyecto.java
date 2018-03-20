/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

/**
 * Clase que representa el producto Proyecto
 * @author L
 */
public class Proyecto extends ProductoInvestigacion{
    
    private int anoInicio;
    private String tipo;
    
    private String institucion;
    private int numeroInvestigadoresPrincipales;
    private String actoAdministrativo;
    private String fechaActoAdministrativo;
    private int numeroInvestigadoresParticipantes;
    
    private int anoFin;
    
    private String financiacion;
    private boolean actoAdmin;
    private String numeroContrato;
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
