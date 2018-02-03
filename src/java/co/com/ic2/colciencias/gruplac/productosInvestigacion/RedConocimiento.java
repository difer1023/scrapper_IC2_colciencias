/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Integrante;
import java.util.ArrayList;

/**
 * Clase que representa el producto Red de conocimiento
 * @author L
 */
public class RedConocimiento extends ProductoInvestigacion{
    private String lugar;
    private String fechaInicio;
    private String tipo;
    
    private int ano;
    private String investigador;
    private int comunidadesParticipantes;
    private String pagWeb;
    
    private ArrayList<Institucion> instituciones;
    

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public String getInvestigador() {
        return investigador;
    }

    public void setInvestigador(String investigador) {
        this.investigador = investigador;
    }

    public int getComunidadesParticipantes() {
        return comunidadesParticipantes;
    }

    public void setComunidadesParticipantes(int comunidadesParticipantes) {
        this.comunidadesParticipantes = comunidadesParticipantes;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public void setPagWeb(String pagWeb) {
        this.pagWeb = pagWeb;
    }

    public ArrayList<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(ArrayList<Institucion> instituciones) {
        this.instituciones = instituciones;
    }
}
