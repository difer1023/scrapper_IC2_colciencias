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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa el producto Reglamento Técnico
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ReglamentoTecnico",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class ReglamentoTecnico extends ProductoInvestigacion{
    
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "pais")
    private String pais;
    
    @XmlElement(name = "ano")
    private int ano;
    
    @XmlElement(name = "institucionFinanciadora")
    private String institucionFinanciadora;
    
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

    public String getInstitucionFinanciadora() {
        return institucionFinanciadora;
    }

    public void setInstitucionFinanciadora(String institucionFinanciadora) {
        this.institucionFinanciadora = institucionFinanciadora;
    }

    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
    } 
}
