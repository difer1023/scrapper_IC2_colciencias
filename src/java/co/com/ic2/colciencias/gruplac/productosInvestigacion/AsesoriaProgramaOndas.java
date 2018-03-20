/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

/**
 * Clase que reperesenta el producto Asesoría al programa Ondas
 * @author L
 */
public class AsesoriaProgramaOndas extends ProductoInvestigacion{
    
    private String lugar;
    private int anoInicio;
    private String institucion;
    private String tipo;
    
    private boolean registroBD;
    private String municipio;
    private String departamento;
    private boolean resultados;
    private boolean reconocido;
    private boolean participacionFerias;

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public boolean isRegistroBD() {
		return registroBD;
	}

	public void setRegistroBD(boolean registroBD) {
		this.registroBD = registroBD;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public boolean isResultados() {
		return resultados;
	}

	public void setResultados(boolean resultados) {
		this.resultados = resultados;
	}

	public boolean isReconocido() {
		return reconocido;
	}

	public void setReconocido(boolean reconocido) {
		this.reconocido = reconocido;
	}

	public boolean isParticipacionFerias() {
		return participacionFerias;
	}

	public void setParticipacionFerias(boolean participacionFerias) {
		this.participacionFerias = participacionFerias;
	}
}
