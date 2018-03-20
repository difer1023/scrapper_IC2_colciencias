/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import co.com.ic2.colciencias.gruplac.Investigador;
import java.util.ArrayList;

/**
 * Clase que representa el producto Innovación en Proceso
 * @author L
 */
public class InnovacionProcedimientoServicio extends ProductoInvestigacion{
    
    private String pais;
    private int ano;
    private String disponibilidad;
    private String institucion;
    private ArrayList<Investigador> autores;
    private String tipo;
    
    private String nit;
    private boolean certificadoImpPeq;
    private boolean certificadoImpMed;
    private boolean certificadoImpGran;

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

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
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

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public boolean isCertificadoImpPeq() {
		return certificadoImpPeq;
	}

	public void setCertificadoImpPeq(boolean certificadoImpPeq) {
		this.certificadoImpPeq = certificadoImpPeq;
	}

	public boolean isCertificadoImpMed() {
		return certificadoImpMed;
	}

	public void setCertificadoImpMed(boolean certificadoImpMed) {
		this.certificadoImpMed = certificadoImpMed;
	}

	public boolean isCertificadoImpGran() {
		return certificadoImpGran;
	}

	public void setCertificadoImpGran(boolean certificadoImpGran) {
		this.certificadoImpGran = certificadoImpGran;
	}
}


