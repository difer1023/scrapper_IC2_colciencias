/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Integrante;
import java.util.ArrayList;

/**
 *
 * @author L
 */
public class GeneracionContenidoImpreso extends ProductoInvestigacion{
    private String lugarPublicacion;
    private String fecha;
    private String medioCirculacion;
    private ArrayList<Integrante> autores;
    private String tipo;

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMedioCirculacion() {
        return medioCirculacion;
    }

    public void setMedioCirculacion(String medioCirculacion) {
        this.medioCirculacion = medioCirculacion;
    }

    public ArrayList<Integrante> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Integrante> autores) {
        this.autores = autores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
