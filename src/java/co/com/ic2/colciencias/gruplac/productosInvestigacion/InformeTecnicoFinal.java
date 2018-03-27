package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import java.util.ArrayList;

import co.com.ic2.colciencias.gruplac.Investigador;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *Clase que representa el producto Informe técnico final
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "InformeTecnicoFinal",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public class InformeTecnicoFinal extends ProductoInvestigacion{
    
    @XmlElement(name = "ano")
    private int ano;
    @XmlElement(name = "tituloInvestigacion")
    private String tituloInvestigacion;
    @XmlElement(name = "autores")
    private ArrayList<Investigador> autores;
    @XmlElement(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "certificacion")
    private boolean certificacion;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTituloInvestigacion() {
        return tituloInvestigacion;
    }

    public void setTituloInvestigacion(String proyecto) {
        this.tituloInvestigacion = proyecto;
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

	public boolean isCertificacion() {
		return certificacion;
	}

	public void setCertificacion(boolean certificacion) {
		this.certificacion = certificacion;
	}
}
