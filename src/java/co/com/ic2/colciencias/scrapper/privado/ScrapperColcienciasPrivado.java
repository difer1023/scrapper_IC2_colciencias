/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.privado;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ApoyoProgramaFormacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ArticuloInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DocumentoTrabajo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Edicion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EmpresaBaseTecnologica;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoImpreso;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InformeFinalInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.LibroInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroArticuloPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Proyecto;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoGrado;
import co.com.ic2.colciencias.scrapper.publico.ScrapperColcienciasPublico;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorApoyoProgramasFormacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorCapitulosLibroInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorDocumentosTrabajo;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEdiciones;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEmpresasBaseTecnologica;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEstrategiasPedagogicasFomentoCTI;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEventosCientificos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorGeneracionContenidosImpresos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInformesInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorLibrosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorParticipacionCiudadanaProyectosCTI;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorProyectos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorRedesConocimiento;
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
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase que se encarga de extraer la información de la parte privada de un grupo de investigación
 * Requiere de los datos necesarios para hacer Log In
 * @author Difer
 */
@WebService(serviceName = "scrapperColcienciasPrivado")
public class ScrapperColcienciasPrivado {

    static final Logger LOG=Logger.getLogger(ScrapperColcienciasPrivado.class.getName());
    /**
     * This is a sample web service operation
     * @param tipoNacionalidad
     * @param paisNacimiento
     * @param nombre
     * @param identificacion
     * @param contrasena
     * @param idGrupo
     * @param anoFinVentanaObservacion
     * @return 
     */
    @WebMethod(operationName = "extraerGrupoInvestigacion")
    public ExtraerGrupoInvestigacionObject extraerGrupoInvestigacion(
            @WebParam(name = "tipoNacionalidad") String tipoNacionalidad,
            @WebParam(name = "paisNacimiento") String paisNacimiento,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "identificacion") String identificacion,
            @WebParam(name = "contrasena") String contrasena,
            @WebParam(name = "idGrupo") int idGrupo,
            @WebParam(name = "anoFinVentanaObservacion") int anoFinVentanaObservacion) {
        
        LOG.info("Iniciando extraccion grupo investigacion privado");
        ExtraerGrupoInvestigacionObject response=new ExtraerGrupoInvestigacionObject();

        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
                + "          \" 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36\"";
        String loginFormUrl = ConstantesScrapper.urlGruplac+"/gruplac/";
        String loginActionUrl = ConstantesScrapper.urlGruplac+"/gruplac/LoginGruplac/login.do";

        String fechaNacimiento = "";

        HashMap<String, String> cookies = new HashMap<>();
        HashMap<String, String> formData = new HashMap<>();

        Gson gson = new Gson();
        
        String enlacePublico;
        try {

            Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET).userAgent(USER_AGENT).proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY).execute();
            //Document loginDoc = loginForm.parse();
            
            cookies.putAll(loginForm.cookies()); // save the cookies, this will be passed on to next request  
            cookies.put("_ga","GA1.3.358109058.1506213293");
            
            formData.put("tpo_nacionalidade", tipoNacionalidad);  
            formData.put("sgl_pais_nasc", paisNacimiento);  
            formData.put("nme_rh", nombre);  
            formData.put("cpf_rh", identificacion);  
            formData.put("dta_nasc_string", fechaNacimiento); 
            formData.put("txt_senha_cnpq", contrasena); 
            
            Connection.Response homePage = Jsoup.connect(loginActionUrl)
                    .cookies(cookies)
                    .data(formData)
                    .method(Connection.Method.POST)
                    .userAgent(USER_AGENT)
                    .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
                    .execute();
            
//            Pieza de codigo para extraer productos de grupos de lideres con mas de un grupo de investigacion

            Elements enlaces = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/li").evaluate(homePage.parse()).getElements();
            
            if(!enlaces.isEmpty() && idGrupo == 0){
                LOG.info("Lider con mas de un grupo de investigacion");
                ArrayList<String> gruposInvestigacion=new ArrayList();
                for (int i=0;i<enlaces.size();i++) {
                    gruposInvestigacion.add(Xsoup.compile("/a/text()").evaluate(enlaces.get(i)).get());
                }
                response.setGruposInvestigacion(gruposInvestigacion);
                response.setRespuesta(1);
                
                return response;
            }
            
            if(idGrupo!= 0){
            String enlaceGrupo = ConstantesScrapper.urlGruplac+"/gruplac" + (Xsoup.compile("/a/@href").evaluate(enlaces.get(idGrupo-1)).get().replace("..", ""));
            Connection.Response home = Jsoup.connect(enlaceGrupo)
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT)
                    .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
                    .execute();
            
            enlacePublico=Xsoup.compile("//*[@id=\"acc3\"]/li[10]/a/@href").evaluate(home.parse()).get();
            }else{
            enlacePublico=Xsoup.compile("//*[@id=\"acc3\"]/li[10]/a/@href").evaluate(homePage.parse()).get();
            }
            ScrapperColcienciasPublico scrapperPublico=new ScrapperColcienciasPublico();
            LOG.info(enlacePublico);
            GrupoInvestigacion grupoInvestigacionPublico=scrapperPublico.extraerGrupoInvestigacion(ConstantesScrapper.urlGruplac +enlacePublico, true, true, true, true, true);
            
            GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion();
            
            grupoInvestigacion.setNombre(grupoInvestigacionPublico.getNombre());
            grupoInvestigacion.setIntegrantes(grupoInvestigacionPublico.getIntegrantes());
            grupoInvestigacion.setAnoMesFormacion(grupoInvestigacionPublico.getAnoMesFormacion().substring(0,4));
            try{
                ArrayList<ArticuloInvestigacion> articulos=ExtractorArticulosInvestigacion.extraerArticulosPublicadosPrivado(extraerTablaProductos("ART", "19", "GNC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<ArticuloInvestigacion> articulosPublico=grupoInvestigacionPublico.getArticulosInvestigacion();
                ArrayList<OtroArticuloPublicado> otrosArticulosPublico=grupoInvestigacionPublico.getOtroArticuloPublicado();
                for(ArticuloInvestigacion articulo: articulos){
                    for(ArticuloInvestigacion articuloPublico:articulosPublico){
                        if(articulo.getNombre().equals(articuloPublico.getNombre()) && 
                                articulo.getNumeroAutores()==articuloPublico.getAutores().size()){
                            articulo.setAutores(articuloPublico.getAutores());
                            ExtractorArticulosInvestigacion.analizarAutores(articulo,grupoInvestigacion);
                            
                        }
                    }
                    
                    for(OtroArticuloPublicado otroContenidoImpresoPublico:otrosArticulosPublico){
                        if(articulo.getNombre().equals(otroContenidoImpresoPublico.getNombre()) && 
                                articulo.getNumeroAutores()==otroContenidoImpresoPublico.getAutores().size()){
                            articulo.setAutores(otroContenidoImpresoPublico.getAutores());
                        }
                    }
                }          
                grupoInvestigacion.setArticulosInvestigacion(articulos);
            }catch(NullPointerException e){
                LOG.info("Error al extraer nombre articulos "+e);
                e.printStackTrace();
            }

            try{
                ArrayList<LibroInvestigacion> libros=ExtractorLibrosInvestigacion.extraerLibrosPublicadosPrivado(extraerTablaProductos("LIB", "19", "GNC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<LibroInvestigacion> librosPublico=grupoInvestigacionPublico.getLibrosResultadoInvestigacion();
                for(LibroInvestigacion libro: libros){
                    for(LibroInvestigacion libroPublico:librosPublico){
                        if(libro.getNombre().equals(libroPublico.getNombre()) && 
                                libro.getNumeroAutores()==libroPublico.getAutores().size()){
                            libro.setAutores(libroPublico.getAutores());
                            ExtractorLibrosInvestigacion.analizarAutores(libro,grupoInvestigacion);
                        }
                    }
                
                }
            grupoInvestigacion.setLibrosResultadoInvestigacion(libros);
            }catch(NullPointerException e){
                LOG.info("Error al extraer libros "+e);
                
            }
            try{
                ArrayList<CapituloLibroPublicado> capitulosLibro=ExtractorCapitulosLibroInvestigacion.extraerCapitulosLibroPrivado(extraerTablaProductos("CAP_LIB", "19", "GNC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<CapituloLibroPublicado> capitulosLibroPublico=grupoInvestigacionPublico.getCapituloDeLibro();
                for(CapituloLibroPublicado capituloLibro: capitulosLibro){
                    for(CapituloLibroPublicado capituloLibroPublico:capitulosLibroPublico){
                        if(capituloLibro.getNombre().equals(capituloLibroPublico.getNombre()) && 
                                capituloLibro.getNumeroAutores()==capituloLibroPublico.getAutores().size()){
                            capituloLibro.setAutores(capituloLibroPublico.getAutores());
                            ExtractorCapitulosLibroInvestigacion.analizarAutores(capituloLibro,grupoInvestigacion);
                        }
                    }
                
                }
                
            grupoInvestigacion.setCapituloDeLibro(capitulosLibro);
            }catch(NullPointerException e){
                LOG.info("Error al extraer capítulos de libro "+e);
            }
            try{
                ArrayList<Software> softwares=ExtractorSoftwares.extraerSoftwaresPrivado(extraerTablaProductos("SF", "19", "DTI", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<Software> softwaresPublico=grupoInvestigacionPublico.getSoftware();
                for(Software software: softwares){
                    for(Software softwarePublico:softwaresPublico){
                        if(software.getNombre().equals(softwarePublico.getNombre())){
                            software.setAutores(softwarePublico.getAutores());
                            ExtractorSoftwares.analizarAutores(software, grupoInvestigacion);
                        }
                    }
                
                }
            grupoInvestigacion.setSoftware(softwares);
            }catch(NullPointerException e){
                LOG.info("Error al extraer software "+e);
                
            }
            try{
                
                ArrayList<EmpresaBaseTecnologica> empresasBaseTecnologica=ExtractorEmpresasBaseTecnologica.extraerEmpresaBaseTecnologicaPrivado(extraerTablaProductos("EBT", "19", "DTI", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<EmpresaBaseTecnologica> empresasBaseTecnologicaPublico=grupoInvestigacionPublico.getEmpresaBaseTecnologica();
                for(EmpresaBaseTecnologica empresaBaseTecnologica: empresasBaseTecnologica){
                    for(EmpresaBaseTecnologica empresaBaseTecnologicaPublico:empresasBaseTecnologicaPublico){
                        if(empresaBaseTecnologica.getNombre().equals(empresaBaseTecnologicaPublico.getNombre())){
                            empresaBaseTecnologica.setAutores(empresaBaseTecnologicaPublico.getAutores());
                        }
                    }
                
                }
            grupoInvestigacion.setEmpresaBaseTecnologica(empresasBaseTecnologica);
            }catch(NullPointerException e){
                LOG.info("Error al extraer empresas de base tecnologica "+e);
            }
            try{
            grupoInvestigacion.setParticipacionCiudadanaProyectoCTI(ExtractorParticipacionCiudadanaProyectosCTI.extraerPaticipacionCiudadanaProyectosCTIPrivado(extraerTablaProductos("PCI", "19", "ASC", cookies), cookies, anoFinVentanaObservacion));
            }catch(NullPointerException e){
                LOG.info("Error al extraer participacion ciudadana "+e);
            }
            try{
            grupoInvestigacion.setEstrategiaPedagogicaFomentoCTI(ExtractorEstrategiasPedagogicasFomentoCTI.extraerEstrategiasPedagogicasFomentoCTIPrivado(extraerTablaProductos("EPA", "19", "ASC", cookies), cookies, anoFinVentanaObservacion));
            }catch(NullPointerException e){
                LOG.info("Error al extraer estrategia pedagogica "+e);
            }
            try{
                ArrayList<GeneracionContenidoImpreso> contenidosImpresos=ExtractorGeneracionContenidosImpresos.extraerGeneracionContenidosImpresosPrivado(extraerTablaProductos("GC_I", "19", "ASC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<ArticuloInvestigacion> articulosPublico=grupoInvestigacionPublico.getArticulosInvestigacion();
                ArrayList<OtroArticuloPublicado> otrosArticulosPublico=grupoInvestigacionPublico.getOtroArticuloPublicado();
                System.out.println("NUMERO CONTENIDOS IMPRESOS"+contenidosImpresos.size());
                for(GeneracionContenidoImpreso contenidoImpreso: contenidosImpresos){
                    for(ArticuloInvestigacion contenidoImpresoPublico:articulosPublico){
                        if(contenidoImpreso.getNombre().equals(contenidoImpresoPublico.getNombre()) && 
                                contenidoImpreso.getNumeroAutores()==contenidoImpresoPublico.getAutores().size()){
                            contenidoImpreso.setAutores(contenidoImpresoPublico.getAutores());
                        }
                    }
                    
                    for(OtroArticuloPublicado otroContenidoImpresoPublico:otrosArticulosPublico){
                        if(contenidoImpreso.getNombre().equals(otroContenidoImpresoPublico.getNombre()) && 
                                contenidoImpreso.getNumeroAutores()==otroContenidoImpresoPublico.getAutores().size()){
                            contenidoImpreso.setAutores(otroContenidoImpresoPublico.getAutores());
                        }
                    }
                }
                
            grupoInvestigacion.setGeneracionContenidoImpreso(contenidosImpresos);
            }catch(NullPointerException e){
                LOG.info("Error al extraer generación de contenidos impresos "+e);
                e.printStackTrace();
            }
            try{
            grupoInvestigacion.setEventoCientifico(ExtractorEventosCientificos.extraerEventosCientificosPrivado(extraerTablaProductos("EC", "19", "ASC", cookies), cookies, anoFinVentanaObservacion));
            }catch(NullPointerException e){
                LOG.info("Error al extraer eventos cientificos "+e);
            }
            try{
            grupoInvestigacion.setRedConocimiento(ExtractorRedesConocimiento.extraerRedesConocimientoPrivado(extraerTablaProductos("RC", "19", "ASC", cookies), cookies, anoFinVentanaObservacion));
            }catch(NullPointerException e){
                LOG.info("Error al redes de conocimiento "+e);
            }
            try{
                
                ArrayList<DocumentoTrabajo> documentosTrabajo=ExtractorDocumentosTrabajo.extraerDocumentosTrabajoPrivado(extraerTablaProductos("WP", "19", "ASC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<DocumentoTrabajo> documentosTrabajoPublico=grupoInvestigacionPublico.getDocumentoTrabajo();
                for(DocumentoTrabajo documentoTrabajo: documentosTrabajo){
                    for(DocumentoTrabajo documentoTrabajoPublico:documentosTrabajoPublico){
                        if(documentoTrabajo.getNombre().equals(documentoTrabajoPublico.getNombre()) && 
                                documentoTrabajo.getNumeroAutores()==documentoTrabajoPublico.getAutores().size()){
                            documentoTrabajo.setAutores(documentoTrabajoPublico.getAutores());
                            documentoTrabajo.setInstituciones(documentoTrabajoPublico.getInstituciones());
                        }
                    }
                
                }
                
            grupoInvestigacion.setDocumentoTrabajo(documentosTrabajo);
            }catch(NullPointerException e){
                LOG.info("Error al extraer documentos de trabajo "+e);
            }
            try{
                
                ArrayList<Edicion> ediciones=ExtractorEdiciones.extraerEdicionesPrivado(extraerTablaProductos("ERL", "19", "ASC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<Edicion> edicionesPublico=grupoInvestigacionPublico.getEdicion();
                for(Edicion edicion: ediciones){
                    for(Edicion edicionPublico:edicionesPublico){
                        if(edicion.getNombre().equals(edicionPublico.getNombre())){
                            edicion.setAutor(edicionPublico.getAutor());
                        }
                    }
                
                }
            grupoInvestigacion.setEdicion(ediciones);
            }catch(NullPointerException e){
                LOG.info("Error al extraer ediciones "+e);
            }
            try{
                
                ArrayList<InformeFinalInvestigacion> informesFinalesInv=ExtractorInformesInvestigacion.extraerInformesInvestigacionPrivado(extraerTablaProductos("IFI", "19", "ASC", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<InformeFinalInvestigacion> informesFinalesInvPublico=grupoInvestigacionPublico.getInformeInvestigacion();
                for(InformeFinalInvestigacion informeFinalInv: informesFinalesInv){
                    for(InformeFinalInvestigacion informeFinalInvPublico:informesFinalesInvPublico){
                        if(informeFinalInv.getNombre().equals(informeFinalInvPublico.getNombre())){
                            informeFinalInv.setAutores(informeFinalInvPublico.getAutores());
                        }
                    }
                }
            grupoInvestigacion.setInformeInvestigacion(informesFinalesInv);
            }catch(NullPointerException e){
                LOG.info("Error al extraer informes de investigacion "+e);
            }
            try{
                
                ArrayList<TrabajoGrado> trabajosGradoDoctorado=ExtractorTrabajosDirigidos.extraerTesisDoctoradoPrivado(extraerTablaProductos("TD", "19", "FRH", cookies), cookies, anoFinVentanaObservacion);
                ArrayList<TrabajoGrado> trabajosGradoPublico=grupoInvestigacionPublico.getTrabajoDirigido();
                for(TrabajoGrado trabajoGrado: trabajosGradoDoctorado){
                    for(TrabajoGrado trabajoGradoPublico:trabajosGradoPublico){
                        if(trabajoGrado.getNombre().equals(trabajoGradoPublico.getNombre())){
                            trabajoGrado.setTipo(trabajoGradoPublico.getTipo());
                            trabajoGrado.setAutores(trabajoGradoPublico.getAutores());
                        }
                    }
                
                }
                ArrayList<TrabajoGrado> trabajosGradoMaestria=ExtractorTrabajosDirigidos.extraerTesisMaestriaPrivado(extraerTablaProductos("TM", "19", "FRH", cookies), cookies, anoFinVentanaObservacion);
                for(TrabajoGrado trabajoGrado: trabajosGradoMaestria){
                    for(TrabajoGrado trabajoGradoPublico:trabajosGradoPublico){
                        if(trabajoGrado.getNombre().equals(trabajoGradoPublico.getNombre())){
                            trabajoGrado.setTipo(trabajoGradoPublico.getTipo());
                            trabajoGrado.setAutores(trabajoGradoPublico.getAutores());
                        }
                    }
                
                }
                ArrayList<TrabajoGrado> trabajosGradoPregrado=ExtractorTrabajosDirigidos.extraerTesisPregradoPrivado(extraerTablaProductos("TP", "19", "FRH", cookies), cookies, anoFinVentanaObservacion);
                for(TrabajoGrado trabajoGrado: trabajosGradoPregrado){
                    for(TrabajoGrado trabajoGradoPublico:trabajosGradoPublico){
                        if(trabajoGrado.getNombre().equals(trabajoGradoPublico.getNombre())){
                            trabajoGrado.setTipo(trabajoGradoPublico.getTipo());
                            trabajoGrado.setAutores(trabajoGradoPublico.getAutores());
                        }
                    }
                
                }
                ArrayList<TrabajoGrado> trabajosGrado=new ArrayList<>();
                trabajosGrado.addAll(trabajosGradoDoctorado);
                trabajosGrado.addAll(trabajosGradoMaestria);
                trabajosGrado.addAll(trabajosGradoPregrado);
                
            grupoInvestigacion.setTrabajoDirigido(trabajosGrado);
            }catch(NullPointerException e){
                LOG.info("Error al extraer tesis "+e);
            }
            
            try{
                
            ArrayList<Proyecto> proyectosInvestigacionDesarrollo=ExtractorProyectos.extraerProyectoInvestigacionDesarrolloPrivado(extraerTablaProductos("PID", "19", "FRH", cookies), cookies, anoFinVentanaObservacion);
            ArrayList<Proyecto> proyectosPublico=grupoInvestigacionPublico.getProyecto();
            for(Proyecto proyecto: proyectosInvestigacionDesarrollo){
                    for(Proyecto proyectoPublico:proyectosPublico){
                        if(proyecto.getNombre().equals(proyectoPublico.getNombre())){
                            proyecto.setTipo(proyectoPublico.getTipo());
                        }
                    }
                
                }
                
            ArrayList<Proyecto> proyectosIDI=ExtractorProyectos.extraerProyectoIDIPrivado(extraerTablaProductos("PF", "19", "FRH", cookies), cookies, anoFinVentanaObservacion);
            for(Proyecto proyecto: proyectosIDI){
                    for(Proyecto proyectoPublico:proyectosPublico){
                        if(proyecto.getNombre().equals(proyectoPublico.getNombre())){
                            proyecto.setTipo(proyectoPublico.getTipo());
                        }
                    }
                
                }
            
            ArrayList<Proyecto> proyectosExtensionYResponsabilidadCTI=ExtractorProyectos.extraerProyectoExtensionResponsabilidadSocialPrivado(extraerTablaProductos("PE", "19", "FRH", cookies), cookies, anoFinVentanaObservacion);
            for(Proyecto proyecto: proyectosExtensionYResponsabilidadCTI){
                    for(Proyecto proyectoPublico:proyectosPublico){
                        if(proyecto.getNombre().equals(proyectoPublico.getNombre())){
                            proyecto.setTipo(proyectoPublico.getTipo());
                        }
                    }
                
                }
            ArrayList<Proyecto> proyectos=new ArrayList<>();
            proyectos.addAll(proyectosInvestigacionDesarrollo);
            proyectos.addAll(proyectosIDI);
            proyectos.addAll(proyectosExtensionYResponsabilidadCTI);
            grupoInvestigacion.setProyecto(proyectos);
            }catch(NullPointerException e){
                LOG.info("Error al extraer proyectos "+e);
            }
            
            try{
                ArrayList<ApoyoProgramaFormacion> apoyoCreacionProgramas=ExtractorApoyoProgramasFormacion.extraerApoyoCreacionProgramasPrivado(extraerTablaProductos("AP", "19", "FRH", cookies), cookies,anoFinVentanaObservacion);
                ArrayList<ApoyoProgramaFormacion> apoyoCreacionCursos=ExtractorApoyoProgramasFormacion.extraerApoyoCreacionCursosPrivado(extraerTablaProductos("AC", "19", "FRH", cookies), cookies);
                ArrayList<ApoyoProgramaFormacion> apoyoCreacionProgramasFormacion=new ArrayList<>();
                apoyoCreacionProgramasFormacion.addAll(apoyoCreacionProgramas);
                apoyoCreacionProgramasFormacion.addAll(apoyoCreacionCursos);
                grupoInvestigacion.setApoyoProgramaFormacion(apoyoCreacionProgramasFormacion);
            }catch(NullPointerException e){
                LOG.info("Error al extraer apoyo creacion programas "+e);
            }
            
            response.setGrupoInvestigacion(grupoInvestigacion);

            LOG.info(gson.toJson(grupoInvestigacion));
        } catch (IOException ex) {
            Logger.getLogger(ScrapperColcienciasPrivado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    private ArrayList<Elements> extraerTablaProductos(String tipoProducto,String idConvocatoria,String claseProduccion,HashMap<String, String> cookies) throws IOException, NullPointerException{
       
        ArrayList<Elements> elementosTabla=new ArrayList<>();
        
        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
                + "          \" 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36\"";
        
        boolean recorrerTabla=true;
        
        int pagina=1;
        while(recorrerTabla){
        HashMap<String, String> datosProductosConvocatoria = new HashMap<>();
            
            datosProductosConvocatoria.put(tipoProducto+"_TABLE_p_", String.valueOf(pagina));  
            datosProductosConvocatoria.put(tipoProducto+"_TABLE_mr_", "100");  
            datosProductosConvocatoria.put(tipoProducto+"_TABLE_tr_", "true");  
            datosProductosConvocatoria.put("cod_convocatoria", idConvocatoria);  
            datosProductosConvocatoria.put("sgl_categoria", tipoProducto); 
            datosProductosConvocatoria.put("clase_prod", claseProduccion);
        
            Connection.Response res2 = Jsoup.connect(ConstantesScrapper.urlGruplac+"/gruplac/Medicion/calificacion/redirect.do")
            .data(datosProductosConvocatoria)
            .method(Connection.Method.GET)
            .cookies(cookies)
            .userAgent(USER_AGENT)
            .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
            .execute();
            
            Elements elements=Xsoup.compile("/html/body/div/form/div/table/tbody[1]/tr").evaluate(res2.parse()).getElements();
            
            String resultadosTabla=Xsoup.compile("/html/body/div/form/div/table/tbody[2]/tr/td/text()").evaluate(res2.parse()).get();
            
            String [] registros = null;
            try{
            registros=(resultadosTabla.split(" - ")[1]).split(" de ");
            }catch(NullPointerException e){
                LOG.info("Error en tabla productos "+e);
                LOG.info(elements.html());
            }
            int numeroActual=Integer.parseInt(registros[0]);
            
            int numeroTotal=Integer.parseInt(registros[1].substring(0, registros[1].length()-1));
            
            if(numeroActual<numeroTotal){
                pagina++;
            }else{
                recorrerTabla=false;
            }
            elementosTabla.add(elements);        
        }
        
        
    return elementosTabla;
    }
}
