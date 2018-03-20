/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.ApoyoProgramaFormacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ArticuloInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.AsesoriaProgramaOndas;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Consultoria;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DisenoIndustrial;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DocumentoTrabajo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Edicion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EmpresaBaseTecnologica;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EspacioParticipacionCiudadanaCTI;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EsquemaCircuito;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EstrategiaComunicacionConocimiento;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EstrategiaPedagogicaFomentoCTI;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EventoCientifico;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoImpreso;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoMultimedia;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoVirtual;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InformeFinalInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InnovacionProcedimientoServicio;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.LibroInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroArticuloPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroLibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ParticipacionCiudadanaProyectoCTI;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.PlantaPiloto;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ProductoInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.PrototipoIndustrial;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Proyecto;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.RedConocimiento;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.SignoDistintivo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoGrado;
import java.util.ArrayList;

/**
 *
 * @author L
 */
public class GrupoInvestigacion {
	private int codigo;
    private String nombre;
    private String anoMesFormacion;
    private String departamento;
    private String ciudad;
    private String lider;
    private String infoCertificacion;
    private String pagWeb;
    private String email;
    private String clasificacion;
    private String areaConocimiento;
    private String programaCienciaTecnologia;
    private String programaCienciaTecnologiaSecundario;
    private ArrayList<Institucion> instituciones;
    private ArrayList<LineaInvestigacion> lineasInvestigacion;
    private ArrayList<Investigador> integrantes;
    private ArrayList<ProductoInvestigacion> productosInvestigacion;
    private ArrayList<ArticuloInvestigacion> articulosInvestigacion;
    private ArrayList<LibroInvestigacion> librosResultadoInvestigacion;
    private ArrayList<CapituloLibroPublicado> capituloDeLibro;
    private ArrayList<DocumentoTrabajo> documentoTrabajo;
    private ArrayList<OtroArticuloPublicado> otroArticuloPublicado;
    private ArrayList<OtroLibroPublicado> otroLibroPublicado;
    private ArrayList<Consultoria> consultoria;
    private ArrayList<DisenoIndustrial> disenoIndustrial;
    private ArrayList<EsquemaCircuito> esquemaCircuito;
    private ArrayList<InnovacionProcedimientoServicio> innovacionProceso;
    private ArrayList<PlantaPiloto> plantaPiloto;
    private ArrayList<PrototipoIndustrial> prototipo;
    private ArrayList<SignoDistintivo> signoDistintivo;
    private ArrayList<Software> software;
    private ArrayList<EmpresaBaseTecnologica> empresaBaseTecnologica;
    private ArrayList<Edicion> edicion;
    private ArrayList<EventoCientifico> eventoCientifico;
    private ArrayList<InformeFinalInvestigacion> informeInvestigacion;
    private ArrayList<RedConocimiento> redConocimiento;
    private ArrayList<GeneracionContenidoImpreso> generacionContenidoImpreso;
    private ArrayList<GeneracionContenidoMultimedia> generacionContenidoMultimedia;
    private ArrayList<GeneracionContenidoVirtual> generacionContenidoVirtual;
    private ArrayList<EstrategiaComunicacionConocimiento> estrategiaComunicacionConocimiento;
    private ArrayList<EstrategiaPedagogicaFomentoCTI> estrategiaPedagogicaFomentoCTI;
    private ArrayList<EspacioParticipacionCiudadanaCTI> espacioParticipacionCiudadana;
    private ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectoCTI;
    private ArrayList<AsesoriaProgramaOndas> asesoriaProgramaOndas;
    private ArrayList<TrabajoGrado> trabajoDirigido;
    private ArrayList<Proyecto> proyecto;
    private ArrayList<ApoyoProgramaFormacion> apoyoProgramaFormacion;
    
    
    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnoMesFormacion() {
        return anoMesFormacion;
    }

    public void setAnoMesFormacion(String anoMesFormacion) {
        this.anoMesFormacion = anoMesFormacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public String getInfoCertificacion() {
        return infoCertificacion;
    }

    public void setInfoCertificacion(String infoCertificacion) {
        this.infoCertificacion = infoCertificacion;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public void setPagWeb(String pagWeb) {
        this.pagWeb = pagWeb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public String getProgramaCienciaTecnologia() {
        return programaCienciaTecnologia;
    }

    public void setProgramaCienciaTecnologia(String programaCienciaTecnologia) {
        this.programaCienciaTecnologia = programaCienciaTecnologia;
    }

    public String getProgramaCienciaTecnologiaSecundario() {
        return programaCienciaTecnologiaSecundario;
    }

    public void setProgramaCienciaTecnologiaSecundario(String programaCienciaTecnologiaSecundario) {
        this.programaCienciaTecnologiaSecundario = programaCienciaTecnologiaSecundario;
    }

    public ArrayList<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(ArrayList<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

    public ArrayList<LineaInvestigacion> getLineasInvestigacion() {
        return lineasInvestigacion;
    }

    public void setLineasInvestigacion(ArrayList<LineaInvestigacion> lineasInvestigacion) {
        this.lineasInvestigacion = lineasInvestigacion;
    }

    public ArrayList<Investigador> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<Investigador> integrantes) {
        this.integrantes = integrantes;
    }

    public ArrayList<ProductoInvestigacion> getProductosInvestigacion() {
        return productosInvestigacion;
    }

    public void setProductosInvestigacion(ArrayList<ProductoInvestigacion> productosInvestigacion) {
        this.productosInvestigacion = productosInvestigacion;
    }

    public ArrayList<ArticuloInvestigacion> getArticulosInvestigacion() {
        return articulosInvestigacion;
    }

    public void setArticulosInvestigacion(ArrayList<ArticuloInvestigacion> articulosInvestigacion) {
        this.articulosInvestigacion = articulosInvestigacion;
    }

    public ArrayList<LibroInvestigacion> getLibrosResultadoInvestigacion() {
        return librosResultadoInvestigacion;
    }

    public void setLibrosResultadoInvestigacion(ArrayList<LibroInvestigacion> librosResultadoInvestigacion) {
        this.librosResultadoInvestigacion = librosResultadoInvestigacion;
    }

    public ArrayList<CapituloLibroPublicado> getCapituloDeLibro() {
        return capituloDeLibro;
    }

    public void setCapituloDeLibro(ArrayList<CapituloLibroPublicado> capituloDeLibro) {
        this.capituloDeLibro = capituloDeLibro;
    }

    public ArrayList<DocumentoTrabajo> getDocumentoTrabajo() {
        return documentoTrabajo;
    }

    public void setDocumentoTrabajo(ArrayList<DocumentoTrabajo> documentoTrabajo) {
        this.documentoTrabajo = documentoTrabajo;
    }

    public ArrayList<OtroArticuloPublicado> getOtroArticuloPublicado() {
        return otroArticuloPublicado;
    }

    public void setOtroArticuloPublicado(ArrayList<OtroArticuloPublicado> otroArticuloPublicado) {
        this.otroArticuloPublicado = otroArticuloPublicado;
    }

    public ArrayList<OtroLibroPublicado> getOtroLibroPublicado() {
        return otroLibroPublicado;
    }

    public void setOtroLibroPublicado(ArrayList<OtroLibroPublicado> OtroLibroPublicado) {
        this.otroLibroPublicado = OtroLibroPublicado;
    }

    
    public ArrayList<Consultoria> getConsultoria() {
        return consultoria;
    }

    public void setConsultoria(ArrayList<Consultoria> consultoria) {
        this.consultoria = consultoria;
    }

    public ArrayList<DisenoIndustrial> getDisenoIndustrial() {
        return disenoIndustrial;
    }

    public void setDisenoIndustrial(ArrayList<DisenoIndustrial> disenoIndustrial) {
        this.disenoIndustrial = disenoIndustrial;
    }

    public ArrayList<EsquemaCircuito> getEsquemaCircuito() {
        return esquemaCircuito;
    }

    public void setEsquemaCircuito(ArrayList<EsquemaCircuito> esquemaCircuito) {
        this.esquemaCircuito = esquemaCircuito;
    }

    public ArrayList<InnovacionProcedimientoServicio> getInnovacionProceso() {
        return innovacionProceso;
    }

    public void setInnovacionProceso(ArrayList<InnovacionProcedimientoServicio> innovacionProceso) {
        this.innovacionProceso = innovacionProceso;
    }

    public ArrayList<PlantaPiloto> getPlantaPiloto() {
        return plantaPiloto;
    }

    public void setPlantaPiloto(ArrayList<PlantaPiloto> plantaPiloto) {
        this.plantaPiloto = plantaPiloto;
    }

    public ArrayList<PrototipoIndustrial> getPrototipo() {
        return prototipo;
    }

    public void setPrototipo(ArrayList<PrototipoIndustrial> prototipo) {
        this.prototipo = prototipo;
    }

    public ArrayList<SignoDistintivo> getSignoDistintivo() {
        return signoDistintivo;
    }

    public void setSignoDistintivo(ArrayList<SignoDistintivo> signoDistintivo) {
        this.signoDistintivo = signoDistintivo;
    }

    public ArrayList<Software> getSoftware() {
        return software;
    }

    public void setSoftware(ArrayList<Software> software) {
        this.software = software;
    }

    public ArrayList<EmpresaBaseTecnologica> getEmpresaBaseTecnologica() {
        return empresaBaseTecnologica;
    }

    public void setEmpresaBaseTecnologica(ArrayList<EmpresaBaseTecnologica> empresaBaseTecnologica) {
        this.empresaBaseTecnologica = empresaBaseTecnologica;
    }

    public ArrayList<Edicion> getEdicion() {
        return edicion;
    }

    public void setEdicion(ArrayList<Edicion> edicion) {
        this.edicion = edicion;
    }

    public ArrayList<EventoCientifico> getEventoCientifico() {
        return eventoCientifico;
    }

    public void setEventoCientifico(ArrayList<EventoCientifico> eventoCientifico) {
        this.eventoCientifico = eventoCientifico;
    }

    public ArrayList<InformeFinalInvestigacion> getInformeInvestigacion() {
        return informeInvestigacion;
    }

    public void setInformeInvestigacion(ArrayList<InformeFinalInvestigacion> informeInvestigacion) {
        this.informeInvestigacion = informeInvestigacion;
    }

    public ArrayList<RedConocimiento> getRedConocimiento() {
        return redConocimiento;
    }

    public void setRedConocimiento(ArrayList<RedConocimiento> redConocimiento) {
        this.redConocimiento = redConocimiento;
    }

    public ArrayList<GeneracionContenidoImpreso> getGeneracionContenidoImpreso() {
        return generacionContenidoImpreso;
    }

    public void setGeneracionContenidoImpreso(ArrayList<GeneracionContenidoImpreso> generacionContenidoImpreso) {
        this.generacionContenidoImpreso = generacionContenidoImpreso;
    }

    public ArrayList<GeneracionContenidoMultimedia> getGeneracionContenidoMultimedia() {
        return generacionContenidoMultimedia;
    }

    public void setGeneracionContenidoMultimedia(ArrayList<GeneracionContenidoMultimedia> generacionContenidoMultimedia) {
        this.generacionContenidoMultimedia = generacionContenidoMultimedia;
    }

    public ArrayList<GeneracionContenidoVirtual> getGeneracionContenidoVirtual() {
        return generacionContenidoVirtual;
    }

    public void setGeneracionContenidoVirtual(ArrayList<GeneracionContenidoVirtual> generacionContenidoVirtual) {
        this.generacionContenidoVirtual = generacionContenidoVirtual;
    }

    public ArrayList<EstrategiaComunicacionConocimiento> getEstrategiaComunicacionConocimiento() {
        return estrategiaComunicacionConocimiento;
    }

    public void setEstrategiaComunicacionConocimiento(ArrayList<EstrategiaComunicacionConocimiento> estrategiaComunicacionConocimiento) {
        this.estrategiaComunicacionConocimiento = estrategiaComunicacionConocimiento;
    }

    public ArrayList<EstrategiaPedagogicaFomentoCTI> getEstrategiaPedagogicaFomentoCTI() {
        return estrategiaPedagogicaFomentoCTI;
    }

    public void setEstrategiaPedagogicaFomentoCTI(ArrayList<EstrategiaPedagogicaFomentoCTI> estrategiaPedagogicaFomentoCTI) {
        this.estrategiaPedagogicaFomentoCTI = estrategiaPedagogicaFomentoCTI;
    }

    public ArrayList<EspacioParticipacionCiudadanaCTI> getEspacioParticipacionCiudadana() {
        return espacioParticipacionCiudadana;
    }

    public void setEspacioParticipacionCiudadana(ArrayList<EspacioParticipacionCiudadanaCTI> espacioParticipacionCiudadana) {
        this.espacioParticipacionCiudadana = espacioParticipacionCiudadana;
    }

    public ArrayList<ParticipacionCiudadanaProyectoCTI> getParticipacionCiudadanaProyectoCTI() {
        return participacionCiudadanaProyectoCTI;
    }

    public void setParticipacionCiudadanaProyectoCTI(ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectoCTI) {
        this.participacionCiudadanaProyectoCTI = participacionCiudadanaProyectoCTI;
    }

    public ArrayList<AsesoriaProgramaOndas> getAsesoriaProgramaOndas() {
        return asesoriaProgramaOndas;
    }

    public void setAsesoriaProgramaOndas(ArrayList<AsesoriaProgramaOndas> asesoriaProgramaOndas) {
        this.asesoriaProgramaOndas = asesoriaProgramaOndas;
    }

    public ArrayList<TrabajoGrado> getTrabajoDirigido() {
        return trabajoDirigido;
    }

    public void setTrabajoDirigido(ArrayList<TrabajoGrado> trabajoDirigido) {
        this.trabajoDirigido = trabajoDirigido;
    }

    public ArrayList<Proyecto> getProyecto() {
        return proyecto;
    }

    public void setProyecto(ArrayList<Proyecto> proyecto) {
        this.proyecto = proyecto;
    }

    public ArrayList<ApoyoProgramaFormacion> getApoyoProgramaFormacion() {
        return apoyoProgramaFormacion;
    }

    public void setApoyoProgramaFormacion(ArrayList<ApoyoProgramaFormacion> apoyoProgramaFormacion) {
        this.apoyoProgramaFormacion = apoyoProgramaFormacion;
    }
}

