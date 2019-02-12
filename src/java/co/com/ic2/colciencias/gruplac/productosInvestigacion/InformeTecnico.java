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
 * Clase que representa el producto informe técnico
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "InformeTecnico",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class InformeTecnico extends ProductoInvestigacion{
    
    @XmlElement(name = "numeroContrato")
    private String numeroContrato;
    
    @XmlElement(name = "institucionServicio")
    private String institucionServicio;
    
    @XmlElement(name = "ano")
    private int ano;
    
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getInstitucionServicio() {
        return institucionServicio;
    }

    public void setInstitucionServicio(String institucionServicio) {
        this.institucionServicio = institucionServicio;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Investigador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
    }
    
    
}
