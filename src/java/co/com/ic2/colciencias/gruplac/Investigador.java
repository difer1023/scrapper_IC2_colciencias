/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac;

/**
 *
 * @author L
 */
public class Investigador {
    private String nombreCompleto;
    private String horasDedicacion;
    private String inicioVinculacion;
    private String finVinculacion;
    private String categoria;
    private String vigenciaCategoria;
    private boolean lider;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getHorasDedicacion() {
        return horasDedicacion;
    }

    public void setHorasDedicacion(String horasDedicacion) {
        this.horasDedicacion = horasDedicacion;
    }

    public String getInicioVinculacion() {
        return inicioVinculacion;
    }

    public void setInicioVinculacion(String inicioVinculacion) {
        this.inicioVinculacion = inicioVinculacion;
    }

    public String getFinVinculacion() {
        return finVinculacion;
    }

    public void setFinVinculacion(String finVinculacion) {
        this.finVinculacion = finVinculacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getVigenciaCategoria() {
        return vigenciaCategoria;
    }

    public void setVigenciaCategoria(String vigenciaCategoria) {
        this.vigenciaCategoria = vigenciaCategoria;
    }

	public boolean isLider() {
		return lider;
	}

	public void setLider(boolean lider) {
		this.lider = lider;
	}
    
    
}