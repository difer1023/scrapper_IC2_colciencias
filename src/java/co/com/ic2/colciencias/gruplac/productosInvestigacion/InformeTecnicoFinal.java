package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import java.util.ArrayList;

import co.com.ic2.colciencias.gruplac.Investigador;

public class InformeTecnicoFinal extends ProductoInvestigacion{
    private int ano;
    private String tituloInvestigacion;
    private ArrayList<Investigador> autores;
    private String tipo;
    
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
