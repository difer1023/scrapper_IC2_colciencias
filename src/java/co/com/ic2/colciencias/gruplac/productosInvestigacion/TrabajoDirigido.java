/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Integrante;
import java.util.ArrayList;

/**
 * Clase que representa el producto Trabajo dirigido
 * @author L
 */
public class TrabajoDirigido extends ProductoInvestigacion{
    
    private String tipo;
    private String lugar;
    private int anoFin;
    private String valoracion;
    private String institucion;

    private ArrayList<Integrante> autores;
    private String autorTrabajo;
    
    private String tipoDireccion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getAnoFin() {
        return anoFin;
    }

    public void setAnoFin(int anoFin) {
        this.anoFin = anoFin;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public ArrayList<Integrante> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Integrante> autores) {
        this.autores = autores;
    }   

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getAutorTrabajo() {
        return autorTrabajo;
    }

    public void setAutorTrabajo(String autorTrabajo) {
        this.autorTrabajo = autorTrabajo;
    }
}
