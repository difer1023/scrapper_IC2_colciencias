/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

/**
 *Clase que representa el producto Apoyo a programas de formación
 * @author L
 */
public class ApoyoProgramaFormacion extends ProductoInvestigacion{
    
    private String actoAdministrativo;
    private String fechaActoAdministrativo;
    private String institucion;
    private String programaSeleccionado;   
    private int ano;
    
    private String facultad;
    private String departamento;
    private String numActoAdmin;
    private int tipoApoyo;

    
    public String getActoAdministrativo() {
        return actoAdministrativo;
    }

    public void setActoAdministrativo(String actoAdministrativo) {
        this.actoAdministrativo = actoAdministrativo;
    }

    public String getFechaActoAdministrativo() {
        return fechaActoAdministrativo;
    }

    public void setFechaActoAdministrativo(String fechaActoAdministrativo) {
        this.fechaActoAdministrativo = fechaActoAdministrativo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getProgramaSeleccionado() {
        return programaSeleccionado;
    }

    public void setProgramaSeleccionado(String programaSeleccionado) {
        this.programaSeleccionado = programaSeleccionado;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFacultad() {
        return facultad;
    }
    
	public void setFacultad(String facultad) {
		this.facultad = facultad;	
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNumActoAdmin() {
		return numActoAdmin;
	}

	public void setNumActoAdmin(String numActoAdmin) {
		this.numActoAdmin = numActoAdmin;
	}

	public int getTipoApoyo() {
		return tipoApoyo;
	}

	public void setTipoApoyo(int tipoApoyo) {
		this.tipoApoyo = tipoApoyo;
	}
}
