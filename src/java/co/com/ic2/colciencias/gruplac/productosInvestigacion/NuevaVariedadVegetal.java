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
 * Clase que representa el producto Nueva Variedad Vegetal
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "NuevaVariedadVegetal",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class NuevaVariedadVegetal extends ProductoInvestigacion{
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "ano")
    private int ano;
    
    @XmlElement(name = "tipoCiclo")
    private String tipoCiclo;
    
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipoCiclo() {
        return tipoCiclo;
    }

    public void setTipoCiclo(String tipoCiclo) {
        this.tipoCiclo = tipoCiclo;
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
