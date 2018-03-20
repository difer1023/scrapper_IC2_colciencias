/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

/**
 * Clase que representa el producto Espacio de participación ciudadana
 * @author L
 */
public class EspacioParticipacionCiudadanaCTI extends ProductoInvestigacion{
    
    private int anoInicio;
    private String tipo;
    
    private String nombreComunidad;
    private boolean constanciaParticipacion;
    
    private int numeroParticipantes;

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public String getNombreComunidad() {
		return nombreComunidad;
	}

	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}

	public boolean isConstanciaParticipacion() {
		return constanciaParticipacion;
	}

	public void setConstanciaParticipacion(boolean constanciaParticipacion) {
		this.constanciaParticipacion = constanciaParticipacion;
	}

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }
}
