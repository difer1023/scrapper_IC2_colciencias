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
public class Proyecto  extends ProductoInvestigacion{
    
    private int anoInicio;
    private String tipo;
    
    private String institucion;
    private int numeroInvestigadoresPrincipales;
    private String actoAdministrativo;
    private String fechaActoAdministrativo;
    private int numeroinvestigadoresParticipantes;
    
    private int anoFin;

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
        return numeroinvestigadoresParticipantes;
    }

    public void setNumeroinvestigadoresParticipantes(int numeroinvestigadoresParticipantes) {
        this.numeroinvestigadoresParticipantes = numeroinvestigadoresParticipantes;
    }
    
}
