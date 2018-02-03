/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Institucion;
import java.util.ArrayList;

/**
 * Clase que representa el producto Participacion ciudadana en proyectos de CTI
 * @author L
 */
public class ParticipacionCiudadanaProyectoCTI extends ProductoInvestigacion{
    
    private int anoInicio;
    private String tipo;
    
    private int numeroInvestigadoresPrincipales;
    private String comunidadesParticipantes;
    private ArrayList<Institucion> instituciones;

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
}
