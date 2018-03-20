package co.com.ic2.colciencias.gruplac.productosInvestigacion;

/**
 * Clase que representa el producto Proyecto de extensiòn y responsabilidad social en CTI
 * @author L
 */
public class ProyectoExtensionCTI extends ProductoInvestigacion{
    
    private int anoInicio;
    private String tipo;
    
    private String institucion;
    private int numeroInvestigadoresPrincipales;
    private String actoAdministrativo;
    private String fechaActoAdministrativo;
    private int numeroInvestigadoresParticipantes;
    
    private int anoFin;
    
    private String financiacion;
    private boolean actoAdmin;
    private String numeroContrato;
    private int tipoFinanciacion;
    
    private String nombreComunidad;
    private String resumenCtel;

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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public int getNumeroInvestigadoresPrincipales() {
        return numeroInvestigadoresPrincipales;
    }

    public void setNumeroInvestigadoresPrincipales(int numeroInvestigadoresPrincipales) {
        this.numeroInvestigadoresPrincipales = numeroInvestigadoresPrincipales;
    }

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

    public int getAnoFin() {
        return anoFin;
    }

    public void setAnoFin(int anoFin) {
        this.anoFin = anoFin;
    }

    public int getNumeroinvestigadoresParticipantes() {
        return numeroInvestigadoresParticipantes;
    }

    public void setNumeroinvestigadoresParticipantes(int numeroinvestigadoresParticipantes) {
        this.numeroInvestigadoresParticipantes = numeroinvestigadoresParticipantes;
    }

	public String getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(String financiacion) {
		this.financiacion = financiacion;
	}

	public boolean isActoAdmin() {
		return actoAdmin;
	}

	public void setActoAdmin(boolean actoAdmin) {
		this.actoAdmin = actoAdmin;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public int getTipoFinanciacion() {
		return tipoFinanciacion;
	}

	public void setTipoFinanciacion(int tipoFinanciacion) {
		this.tipoFinanciacion = tipoFinanciacion;
	}

	public int getNumeroInvestigadoresParticipantes() {
		return numeroInvestigadoresParticipantes;
	}

	public void setNumeroInvestigadoresParticipantes(int numeroInvestigadoresParticipantes) {
		this.numeroInvestigadoresParticipantes = numeroInvestigadoresParticipantes;
	}

	public String getNombreComunidad() {
		return nombreComunidad;
	}

	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}

	public String getResumenCtel() {
		return resumenCtel;
	}

	public void setResumenCtel(String resumenCtel) {
		this.resumenCtel = resumenCtel;
	}
}
