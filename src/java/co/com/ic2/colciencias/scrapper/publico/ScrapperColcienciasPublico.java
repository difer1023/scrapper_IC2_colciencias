/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.LineaInvestigacion;
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
import co.com.ic2.colciencias.gruplac.productosInvestigacion.PrototipoIndustrial;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Proyecto;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.RedConocimiento;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.SignoDistintivo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoGrado;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorAsesoriasProgramaOndas;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorCapitulosLibroInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorConsultorias;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorDisenosIndustriales;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorDocumentosTrabajo;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEdiciones;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEmpresasBaseTecnologica;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEspaciosParticipacionCiudadana;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEsquemasCircuito;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEstrategiasComunicacionConocimiento;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEstrategiasPedagogicasFomentoCTI;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEventosCientificos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorGeneracionContenidosImpresos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorGeneracionContenidosMultimedia;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorGeneracionContenidosVirtuales;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInformesInvestigacion;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInformesTecnicos.extraerInformesTecnicos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInnovacionProcesos;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInnovacionesGeneradasGestionEmpresarial.extraerInnovacionesGeneradasGestionEmpresarial;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInstituciones;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorIntegrantes;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorLibrosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorLineasInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorNuevasVariedadesAnimales;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorNuevasVariedadesAnimales.extraerNuevasVariedadesAnimales;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorNuevasVariedadesVegetales;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorNuevasVariedadesVegetales.extraerNuevasVariedadesVegetales;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorParticipacionCiudadanaProyectosCTI;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorPlantasPiloto;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorPrototipos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorProyectos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorRedesConocimiento;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorReglamentosTecnicos.extraerReglamentosTecnicos;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorRegulacionesNormas.extraerRegulacionesNormas;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorSignosDistintivos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorSoftwares;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorTrabajosDirigidos;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorGuiasPracticaClinica.extraerGuiasPracticaClinica;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorProyectosLey.extraerProyectosLey;

/**
 * Clase que se encarga de extraer la información de la parte pública del Gruplac
 * Requiere la dirección URL del grupo de investigación
 * @author Difer
 */
@WebService(serviceName = "scrapperColcienciasPublico")
public class ScrapperColcienciasPublico {

    static final Logger LOG=Logger.getLogger(ScrapperColcienciasPublico.class.getName());
    /**
     * Operacion para extraer los datos de la parte publica del gruplac de un
     * grupo de investigacion
     *
     * @param urlGruplac
     * @param instituciones
     * @param lineasInvestigacion
     * @param integrantes
     * @param integrantesDetalles
     * @param productos
     * @return
     */
    @WebMethod(operationName = "extraerGrupoInvestigacion")
    public GrupoInvestigacion extraerGrupoInvestigacion(
            @WebParam(name = "urlGruplac") String urlGruplac,
            @WebParam(name = "instituciones") boolean instituciones,
            @WebParam(name = "lineasInvestigacion") boolean lineasInvestigacion,
            @WebParam(name = "integrantes") boolean integrantes,
            @WebParam(name = "integrantesDetalles") boolean integrantesDetalles,
            @WebParam(name = "productos") boolean productos) {
        GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion();
        Document documento;
        try {
            LOG.info("Iniciando extraccion grupo investigacion "+urlGruplac);
            documento = Jsoup.connect(urlGruplac).proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY).get();
            Elements elements = Xsoup.compile("/html/body/table[1]/tbody/tr").evaluate(documento).getElements();
            
            grupoInvestigacion.setNombre(Xsoup.compile("/html/body/span/text()").evaluate(documento).get());
            grupoInvestigacion.setAnoMesFormacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(1)).get());
            grupoInvestigacion.setDepartamento(Xsoup.compile("/td[2]/text()").evaluate(elements.get(2)).get().split("-")[0]);
            grupoInvestigacion.setCiudad(Xsoup.compile("/td[2]/text()").evaluate(elements.get(2)).get().split("-")[1]);
            grupoInvestigacion.setLider(Xsoup.compile("/td[2]/text()").evaluate(elements.get(3)).get());
            grupoInvestigacion.setInfoCertificacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(4)).get());
            grupoInvestigacion.setPagWeb(Xsoup.compile("/td[2]/text()").evaluate(elements.get(5)).get());
            grupoInvestigacion.setEmail(Xsoup.compile("/td[2]/text()").evaluate(elements.get(6)).get());
            grupoInvestigacion.setClasificacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(7)).get());
            grupoInvestigacion.setProgramaCienciaTecnologia(Xsoup.compile("/td[2]/text()").evaluate(elements.get(8)).get());
            grupoInvestigacion.setProgramaCienciaTecnologiaSecundario(Xsoup.compile("/td[2]/text()").evaluate(elements.get(9)).get());

            
            if (instituciones) {
                LOG.info("Iniciando extraccion instituciones");
                elements = Xsoup.compile("/html/body/table[2]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setInstituciones(extraerInstituciones(elements));
            }

            
            if (lineasInvestigacion) {
                LOG.info("Iniciando extraccion lineas investigacion");
                elements = Xsoup.compile("/html/body/table[4]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setLineasInvestigacion(extraerLineasInvestigacion(elements));
            }

            
            if (integrantes) {
                LOG.info("Iniciando extraccion integrantes");
                elements = Xsoup.compile("/html/body/table[6]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setIntegrantes(extraerIntegrantes(elements,grupoInvestigacion.getLider(),integrantesDetalles));
            }

            
            if (productos) {
                LOG.info("Iniciando extraccion productos");
                elements = Xsoup.compile("/html/body/table[8]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setArticulosInvestigacion(extraerArticulosPublicados(elements));

                elements = Xsoup.compile("/html/body/table[9]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setLibrosResultadoInvestigacion(extraerLibrosPublicados(elements));

                elements = Xsoup.compile("/html/body/table[10]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setCapituloDeLibro(extraerCapitulosLibroPublicados(elements));

                elements = Xsoup.compile("/html/body/table[11]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setDocumentoTrabajo(extraerDocumentosTrabajo(elements));

                elements = Xsoup.compile("/html/body/table[13]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setOtroArticuloPublicado(extraerOtrosArticulosPublicados(elements));

                elements = Xsoup.compile("/html/body/table[14]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setOtroLibroPublicado(extraerOtrosLibrosPublicados(elements));

                elements = Xsoup.compile("/html/body/table[20]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setDisenoIndustrial(extraerDisenosIndustriales(elements));

                elements = Xsoup.compile("/html/body/table[21]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEsquemaCircuito(extraerEsquemasCircuito(elements));
                
                elements = Xsoup.compile("/html/body/table[22]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setInformeTecnico(extraerInformesTecnicos(elements));

                elements = Xsoup.compile("/html/body/table[23]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setInnovacionProceso(extraerInnovacionProcesos(elements));
                
                elements = Xsoup.compile("/html/body/table[24]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setInnovacionGestion(extraerInnovacionesGeneradasGestionEmpresarial(elements));

                elements = Xsoup.compile("/html/body/table[25]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setNuevaVariedadAnimal(extraerNuevasVariedadesAnimales(elements));
                
                elements = Xsoup.compile("/html/body/table[27]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setNuevaVariedadVegetal(extraerNuevasVariedadesVegetales(elements));
                
                elements = Xsoup.compile("/html/body/table[29]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setPlantaPiloto(extraerPlantasPiloto(elements));

                elements = Xsoup.compile("/html/body/table[32]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setPrototipo(extraerPrototipos(elements));
                
                elements = Xsoup.compile("/html/body/table[33]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setRegulacionNorma(extraerRegulacionesNormas(elements));
                
                elements = Xsoup.compile("/html/body/table[35]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setReglamentoTecnico(extraerReglamentosTecnicos(elements));
                
                elements = Xsoup.compile("/html/body/table[36]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setGuiaPracticaClinica(extraerGuiasPracticaClinica(elements));
                
                elements = Xsoup.compile("/html/body/table[37]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setProyectoLey(extraerProyectosLey(elements));

                elements = Xsoup.compile("/html/body/table[38]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setSignoDistintivo(extraerSignosDistintivos(elements));

                elements = Xsoup.compile("/html/body/table[39]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setSoftware(extraerSoftwares(elements));

                elements = Xsoup.compile("/html/body/table[40]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEmpresaBaseTecnologica(extraerEmpresasBaseTecnologica(elements));
                
                elements = Xsoup.compile("/html/body/table[42]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setConsultoria(extraerConsultorias(elements));

                elements = Xsoup.compile("/html/body/table[43]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEdicion(extraerEdiciones(elements));

                elements = Xsoup.compile("/html/body/table[44]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEventoCientifico(extraerEventosCientificos(elements));

                elements = Xsoup.compile("/html/body/table[45]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setInformeInvestigacion(extraerInformesInvestigacion(elements));

                elements = Xsoup.compile("/html/body/table[47]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setRedConocimiento(extraerRedesConocimiento(elements));

                elements = Xsoup.compile("/html/body/table[49]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setGeneracionContenidoImpreso(extraerGeneracionContenidosImpresos(elements));

                elements = Xsoup.compile("/html/body/table[50]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setGeneracionContenidoMultimedia(extraerGeneracionContenidosMultimedia(elements));

                elements = Xsoup.compile("/html/body/table[51]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setGeneracionContenidoVirtual(extraerGeneracionContenidosVirtuales(elements));

                elements = Xsoup.compile("/html/body/table[52]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEstrategiaComunicacionConocimiento(extraerEstrategiasComunicacionConocimiento(elements));

                elements = Xsoup.compile("/html/body/table[53]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEstrategiaPedagogicaFomentoCTI(extraerEstrategiasPedagogicasFomentoCTI(elements));

                elements = Xsoup.compile("/html/body/table[54]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setEspacioParticipacionCiudadana(extraerEspaciosParticipacionCiudadana(elements));

                elements = Xsoup.compile("/html/body/table[55]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setParticipacionCiudadanaProyectoCTI(extraerParticipacionCiudadanaProyectosCTI(elements));

                elements = Xsoup.compile("/html/body/table[58]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setAsesoriaProgramaOndas(extraerAsesoriasProgramaOndas(elements));

                elements = Xsoup.compile("/html/body/table[60]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setTrabajoDirigido(extraerTrabajosDirigidos(elements));

                elements = Xsoup.compile("/html/body/table[65]/tbody/tr").evaluate(documento).getElements();
                grupoInvestigacion.setProyecto(extraerProyectos(elements));
            }
            Gson gson = new Gson();
           
//            LOG.info(gson.toJson(grupoInvestigacion));
        } catch (IOException ex) {
            Logger.getLogger(ScrapperColcienciasPublico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupoInvestigacion;
    }
    
    @WebMethod(operationName = "extraerGruposInvestigador")
    public ArrayList<GrupoInvestigacion> extraerGruposInvestigador(
            @WebParam(name = "Investigador") Investigador investigador) {
        return ExtractorIntegrantes.extraerGruposIntegrante(investigador);
    }

    private ArrayList<Institucion> extraerInstituciones(Elements elements) {
        return ExtractorInstituciones.extraerInstituciones(elements);
    }

    private ArrayList<LineaInvestigacion> extraerLineasInvestigacion(Elements elements) {
        return ExtractorLineasInvestigacion.extraerLineasInvestigacion(elements);
    }

    private ArrayList<Investigador> extraerIntegrantes(Elements elements,String lider,boolean integrantesDetalles) {
        return ExtractorIntegrantes.extraerIntegrantes(elements,lider,integrantesDetalles);
    }

    //problemas con grupos muy grandes como giira
    //No scrapea el primer artículo, pendiente scrapear volumen, numero pag inicial y final y doi
    private ArrayList<ArticuloInvestigacion> extraerArticulosPublicados(Elements elements) {
        return ExtractorArticulosInvestigacion.extraerArticulosPublicados(elements);
    }

    //pendientes espacio blanco despues de la editorial
    private ArrayList<LibroInvestigacion> extraerLibrosPublicados(Elements elements) {
        return ExtractorLibrosInvestigacion.extraerLibrosPublicados(elements);
    }

    //pendiente campos en orden,  espacio en blanco al final de editorial  
    private ArrayList<CapituloLibroPublicado> extraerCapitulosLibroPublicados(Elements elements) {
        return ExtractorCapitulosLibroInvestigacion.extraerCapitulosLibroPublicados(elements);
    }

    //Pendiente scrapear instituciones
    private ArrayList<DocumentoTrabajo> extraerDocumentosTrabajo(Elements elements) {
        return ExtractorDocumentosTrabajo.extraerDocumentosTrabajo(elements);
    }

    //No scrapea el primer artículo, pendiente scrapear volumen, numero pag inicial y final y doi
    private ArrayList<OtroArticuloPublicado> extraerOtrosArticulosPublicados(Elements elements) {
        return ExtractorArticulosInvestigacion.extraerOtrosArticulosPublicados(elements);
    }

    //pendientes espacio blanco despues de la editorial
    private ArrayList<OtroLibroPublicado> extraerOtrosLibrosPublicados(Elements elements) {
        return ExtractorLibrosInvestigacion.extraerOtrosLibrosPublicados(elements);
    }

    //no hay que extraer disponibilidad ni institucion, espacio en blanco al final de institucion
    private ArrayList<Consultoria> extraerConsultorias(Elements elements) {
        return ExtractorConsultorias.extraerConsultorias(elements);
    }

    //espacio en blanco al final de institucion, no necesita disponibilidad ni institución
    private ArrayList<DisenoIndustrial> extraerDisenosIndustriales(Elements elements) {
        return ExtractorDisenosIndustriales.extraerDisenosIndustriales(elements);
    }

    //espacio en blanco al final de institucion, no necesita disponibilidad ni institución
    private ArrayList<EsquemaCircuito> extraerEsquemasCircuito(Elements elements) {
        return ExtractorEsquemasCircuito.extraerEsquemasCircuito(elements);
    }

    //espacio en blanco al final de institucion, no necesita disponibilidad
    private ArrayList<InnovacionProcedimientoServicio> extraerInnovacionProcesos(Elements elements) {
        return ExtractorInnovacionProcesos.extraerInnovacionProcesos(elements);
    }

    //No necesita disponibilidad ni nombre comercial, espacio en blanco despues de institucion
    private ArrayList<PlantaPiloto> extraerPlantasPiloto(Elements elements) {
        return ExtractorPlantasPiloto.extraerPlantasPiloto(elements);
    }

    //No necesita disponibilidad ni nombre comercial, espacio en blanco despues de institucion
    private ArrayList<PrototipoIndustrial> extraerPrototipos(Elements elements) {
        return ExtractorPrototipos.extraerPrototipos(elements);
    }

    private ArrayList<SignoDistintivo> extraerSignosDistintivos(Elements elements) {
        return ExtractorSignosDistintivos.extraerSignosDistintivos(elements);
    }

    //Pendiente scrapear nombre comercial y del producto, no sé si es necesario pais autores disponibilidad
    //espacio en blanco al final de la institucion
    private ArrayList<Software> extraerSoftwares(Elements elements) {
        return ExtractorSoftwares.extraerSoftwares(elements);
    }

    //Espacio en blanco al final del estado, fecha de registro tal vez necesita substring
    //No sé si es necesario scrapear autor, y cual de las dos fechas
    private ArrayList<EmpresaBaseTecnologica> extraerEmpresasBaseTecnologica(Elements elements) {
        return ExtractorEmpresasBaseTecnologica.extraerEmpresasBaseTecnologica(elements);
    }

    //No sé qué campos hay que scrapear exactamente
    private ArrayList<Edicion> extraerEdiciones(Elements elements) {
        return ExtractorEdiciones.extraerEdiciones(elements);
    }

    //No sé si hay que scrapear ambito, faltan instituciones patrocinadoras 13 giiira hay mas de una
    //falta posible substring fechas, espacio en blanco al final de ambas
    private ArrayList<EventoCientifico> extraerEventosCientificos(Elements elements) {
        return ExtractorEventosCientificos.extraerEventosCientificos(elements);
    }

    //no sé si es necesario scrapear nombre del proyecto
    private ArrayList<InformeFinalInvestigacion> extraerInformesInvestigacion(Elements elements) {
        return ExtractorInformesInvestigacion.extraerInformesInvestigacion(elements);
    }

    //formato fecha, no se si scrapear fecha fin y numero de participantes
    private ArrayList<RedConocimiento> extraerRedesConocimiento(Elements elements) {
        return ExtractorRedesConocimiento.extraerRedesConocimiento(elements);
    }
    //espacio blanco medio de circulacion final
    //no sé si scrapear ambito y sitio web
    //formato fecha

    private ArrayList<GeneracionContenidoImpreso> extraerGeneracionContenidosImpresos(Elements elements) {
        return ExtractorGeneracionContenidosImpresos.extraerGeneracionContenidosImpresos(elements);
    }

    //No sé si scrapear país, idioma, sitio web, emisora
    //pendiente array de instituciones 
    private ArrayList<GeneracionContenidoMultimedia> extraerGeneracionContenidosMultimedia(Elements elements) {
        return ExtractorGeneracionContenidosMultimedia.extraerGeneracionContenidosMultimedia(elements);
    }

    //pendiente array de instituciones 
    //formato fecha
    private ArrayList<GeneracionContenidoVirtual> extraerGeneracionContenidosVirtuales(Elements elements) {
        return ExtractorGeneracionContenidosVirtuales.extraerGeneracionContenidosVirtuales(elements);
    }

    //No se si scrapear año fin
    //No sé si scrapear descripcion, espacio en blanco al final de descripcion
    //cambio de tipo a nombre
    private ArrayList<EstrategiaComunicacionConocimiento> extraerEstrategiasComunicacionConocimiento(Elements elements) {
        return ExtractorEstrategiasComunicacionConocimiento.extraerEstrategiasComunicacionConocimiento(elements);
    }

    //No se si scrapear año fin
    //No sé si scrapear descripcion, espacio en blanco al final de descripcion
    //cambio de tipo a nombre
    private ArrayList<EstrategiaPedagogicaFomentoCTI> extraerEstrategiasPedagogicasFomentoCTI(Elements elements) {
        return ExtractorEstrategiasPedagogicasFomentoCTI.extraerEstrategiasPedagogicasFomentoCTI(elements);
    }

    //no se si scrapear fecha fin (no hay aparentemente) y descripcion
    //cambio de tipo a nombre
    private ArrayList<EspacioParticipacionCiudadanaCTI> extraerEspaciosParticipacionCiudadana(Elements elements) {
        return ExtractorEspaciosParticipacionCiudadana.extraerEspaciosParticipacionCiudadana(elements);
    }

    private ArrayList<ParticipacionCiudadanaProyectoCTI> extraerParticipacionCiudadanaProyectosCTI(Elements elements) {
        return ExtractorParticipacionCiudadanaProyectosCTI.extraerParticipacionCiudadanaProyectosCTI(elements);
    }

    //no se si scrapear fecha fin, participacion feria y nombre feria
//cambio de tipo a nombre
    private ArrayList<AsesoriaProgramaOndas> extraerAsesoriasProgramaOndas(Elements elements) {
        return ExtractorAsesoriasProgramaOndas.extraerAsesoriasProgramaOndas(elements);
    }

    //No sé si scrapear tipo de orientacion, nombre estudiante, programa académico,número de páginas
    //Hay que scrappear mes
    private ArrayList<TrabajoGrado> extraerTrabajosDirigidos(Elements elements) {
        return ExtractorTrabajosDirigidos.extraerTrabajosDirigidos(elements);
    }

    //No sé si scrapear fecha fin ni como hacerlo
    //A veces no existe la línea de la fecha, es necesario un ciclo?
    private ArrayList<Proyecto> extraerProyectos(Elements elements) {
        return ExtractorProyectos.extraerProyectos(elements);
    }
    
}
