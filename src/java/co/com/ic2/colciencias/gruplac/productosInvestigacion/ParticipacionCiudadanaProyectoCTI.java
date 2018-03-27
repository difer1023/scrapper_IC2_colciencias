/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Institucion;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Participacion ciudadana en proyectos de CTI
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ParticipacionCiudadanaProyectoCTI",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class ParticipacionCiudadanaProyectoCTI extends ProductoInvestigacion{
    
    @XmlElement(name = "anoInicio")
    private int anoInicio;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "numeroInvestigadoresPrincipales")
    private int numeroInvestigadoresPrincipales;
    @XmlElement(name = "comunidadesParticipantes")
    private String comunidadesParticipantes;
    @XmlElement(name = "instituciones")
    private ArrayList<Institucion> instituciones;
    
    @XmlElement(name = "constanciaParticipacion")
    private boolean constanciaParticipacion;

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

    public int getNumeroInvestigadoresPrincipales() {
        return numeroInvestigadoresPrincipales;
    }

    public void setNumeroInvestigadoresPrincipales(int numeroInvestigadoresPrincipales) {
        this.numeroInvestigadoresPrincipales = numeroInvestigadoresPrincipales;
    }

    public String getComunidadesParticipantes() {
        return comunidadesParticipantes;
    }

    public void setComunidadesParticipantes(String comunidadesParticipantes) {
        this.comunidadesParticipantes = comunidadesParticipantes;
    }

    public ArrayList<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(ArrayList<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

	public boolean isConstanciaParticipacion() {
		return constanciaParticipacion;
	}

	public void setConstanciaParticipacion(boolean constanciaParticipacion) {
		this.constanciaParticipacion = constanciaParticipacion;
	}
}
