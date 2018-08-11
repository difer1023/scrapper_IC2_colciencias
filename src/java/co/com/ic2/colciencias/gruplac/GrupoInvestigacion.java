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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "GrupoInvestigacion",namespace = "gruplac.colciencias.ic2.com.co")
public class GrupoInvestigacion {
    
    @XmlElement(name = "codigo")
    private int codigo;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "anoMesFormacion")
    private String anoMesFormacion;
    @XmlElement(name = "departamento")
    private String departamento;
    @XmlElement(name = "ciudad")
    private String ciudad;
    @XmlElement(name = "lider")
    private String lider;
    @XmlElement(name = "infoCertificacion")
    private String infoCertificacion;
    @XmlElement(name = "pagWeb")
    private String pagWeb;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "clasificacion")
    private String clasificacion;
    @XmlElement(name = "areaConocimiento")
    private String areaConocimiento;
    @XmlElement(name = "programaCienciaTecnologia")
    private String programaCienciaTecnologia;
    @XmlElement(name = "programaCienciaTecnologiaSecundario")
    private String programaCienciaTecnologiaSecundario;
    @XmlElementWrapper(name = "listInstituciones")
    @XmlElement(name = "instituciones")
    private ArrayList<Institucion> instituciones;
    @XmlElement(name = "lineasInvestigacion")
    private ArrayList<LineaInvestigacion> lineasInvestigacion;
    @XmlElement(name = "integrantes")
    private ArrayList<Investigador> integrantes;
    @XmlElement(name = "productosInvestigacion")
    private ArrayList<ProductoInvestigacion> productosInvestigacion;
    @XmlElement(name = "articulosInvestigacion")
    private ArrayList<ArticuloInvestigacion> articulosInvestigacion;
    @XmlElement(name = "librosResultadoInvestigacion")
    private ArrayList<LibroInvestigacion> librosResultadoInvestigacion;
    @XmlElement(name = "capituloDeLibro")
    private ArrayList<CapituloLibroPublicado> capituloDeLibro;
    @XmlElement(name = "documentoTrabajo")
    private ArrayList<DocumentoTrabajo> documentoTrabajo;
    @XmlElement(name = "otroArticuloPublicado")
    private ArrayList<OtroArticuloPublicado> otroArticuloPublicado;
    @XmlElement(name = "otroLibroPublicado")
    private ArrayList<OtroLibroPublicado> otroLibroPublicado;
    @XmlElement(name = "consultoria")
    private ArrayList<Consultoria> consultoria;
    @XmlElement(name = "disenoIndustrial")
    private ArrayList<DisenoIndustrial> disenoIndustrial;
    @XmlElement(name = "esquemaCircuito")
    private ArrayList<EsquemaCircuito> esquemaCircuito;
    @XmlElement(name = "innovacionProceso")
    private ArrayList<InnovacionProcedimientoServicio> innovacionProceso;
    @XmlElement(name = "plantaPiloto")
    private ArrayList<PlantaPiloto> plantaPiloto;
    @XmlElement(name = "prototipo")
    private ArrayList<PrototipoIndustrial> prototipo;
    @XmlElement(name = "signoDistintivo")
    private ArrayList<SignoDistintivo> signoDistintivo;
    @XmlElement(name = "software")
    private ArrayList<Software> software;
    @XmlElement(name = "empresaBaseTecnologica")
    private ArrayList<EmpresaBaseTecnologica> empresaBaseTecnologica;
    @XmlElement(name = "edicion")
    private ArrayList<Edicion> edicion;
    @XmlElement(name = "eventoCientifico")
    private ArrayList<EventoCientifico> eventoCientifico;
    @XmlElement(name = "informeInvestigacion")
    private ArrayList<InformeFinalInvestigacion> informeInvestigacion;
    @XmlElement(name = "redConocimiento")
    private ArrayList<RedConocimiento> redConocimiento;
    @XmlElement(name = "generacionContenidoImpreso")
    private ArrayList<GeneracionContenidoImpreso> generacionContenidoImpreso;
    @XmlElement(name = "generacionContenidoMultimedia")
    private ArrayList<GeneracionContenidoMultimedia> generacionContenidoMultimedia;
    @XmlElement(name = "generacionContenidoVirtual")
    private ArrayList<GeneracionContenidoVirtual> generacionContenidoVirtual;
    @XmlElement(name = "estrategiaComunicacionConocimiento")
    private ArrayList<EstrategiaComunicacionConocimiento> estrategiaComunicacionConocimiento;
    @XmlElement(name = "estrategiaPedagogicaFomentoCTI")
    private ArrayList<EstrategiaPedagogicaFomentoCTI> estrategiaPedagogicaFomentoCTI;
    @XmlElement(name = "espacioParticipacionCiudadana")
    private ArrayList<EspacioParticipacionCiudadanaCTI> espacioParticipacionCiudadana;
    @XmlElement(name = "participacionCiudadanaProyectoCTI")
    private ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectoCTI;
    @XmlElement(name = "asesoriaProgramaOndas")
    private ArrayList<AsesoriaProgramaOndas> asesoriaProgramaOndas;
    @XmlElement(name = "trabajoDirigido")
    private ArrayList<TrabajoGrado> trabajoDirigido;
    @XmlElement(name = "proyecto")
    private ArrayList<Proyecto> proyecto;
    @XmlElement(name = "apoyoProgramaFormacion")
    private ArrayList<ApoyoProgramaFormacion> apoyoProgramaFormacion;
    @XmlElement(name = "urlGruplac")
    private String urlGruplac;
    
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

    public String getUrlGruplac() {
        return urlGruplac;
    }

    public void setUrlGruplac(String urlGruplac) {
        this.urlGruplac = urlGruplac;
    }
}

