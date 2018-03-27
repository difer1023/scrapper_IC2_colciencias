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
 * Clase que representa el producto Informe final de investigación
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "InformeFinalInvestigacion",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class InformeFinalInvestigacion extends ProductoInvestigacion{
    
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "proyecto")
    private String proyecto;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
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
}
