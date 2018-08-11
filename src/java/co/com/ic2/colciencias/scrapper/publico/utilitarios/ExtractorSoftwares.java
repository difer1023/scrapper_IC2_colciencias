/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.cache.CacheManagerScraper;
import co.com.ic2.colciencias.constants.ConstantesModelo;
import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
import co.com.ic2.colciencias.scrapper.publico.ScrapperColcienciasPublico;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion.USER_AGENT;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorCapitulosLibroInvestigacion.LOG;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer la información relacionada con el producto Software
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorSoftwares {
    
    /**
    * Método encargado de extraer información sobre el producto Software
    * Presente en la parte pública del gruplac
    */
    public static ArrayList<Software> extraerSoftwares(Elements elements) {
        ArrayList<Software> softwares = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Software software = new Software();
            software.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            software.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleSoftware=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            software.setPais(detalleSoftware.split(",")[0].substring(1));
            String ano=detalleSoftware.split(",")[1].substring(1,5);
            software.setAno(Integer.parseInt(ano));
            software.setDisponibilidad(detalleSoftware.split(",")[2].substring(17));
            software.setSitioWeb(detalleSoftware.split(",")[3].substring(12));
           
            String detalleSoftwareI=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            software.setInstitucion(detalleSoftwareI.split(":")[1].substring(1));
           
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            software.setAutores(autores);

            softwares.add(software);
        }
        return softwares;
    }
    
    
    /**
    * Método encargado de extraer información sobre el producto Software
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<Software> extraerSoftwaresPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<Software> softwares = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                Software software = new Software();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                software.setAno(ano);
                
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_SF-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    software.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        software.setCategoria(categoria);
                        software.setClasificado(true);
                    }
                    String enlaceDetalle=(ConstantesScrapper.urlGruplac+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
    //                System.out.println("enlace"+enlaceDetalle); 
                    Document doc = null;
                    try {
                        Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                            .cookies(cookies)
                            .userAgent(USER_AGENT)
                            .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
                            .execute();
                        doc=res2.parse();
                    } catch (IOException ex) {
                        Logger.getLogger(ExtractorSoftwares.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    software.setRegistrosAsociados(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());

                    softwares.add(software);
                }
            }
        }
        return softwares;
    }
    
    public static void analizarAutores(Software software, GrupoInvestigacion grupoInvestigacion) {
        LOG.info("PRODUCTO " + software.getNombre());
        HashSet<String> gruposColaboracion = new HashSet<String>();
        for (Investigador investigador : software.getAutores()) {
            LOG.info("INVESTIGADOR " + investigador.getNombreCompleto());

            LOG.info("BUSCANDO GRUPOS INVESTIGADOR");

            ArrayList<GrupoInvestigacion> gruposInvestigador = CacheManagerScraper.getInstance().getCacheCienciaWar().get(investigador.getNombreCompleto());
            
            if (gruposInvestigador == null) {
                    LOG.info("BUSQUEDA NO ESTA EN CACHE" );
                    gruposInvestigador = ExtractorIntegrantes.extraerGruposIntegrante(investigador);
                    CacheManagerScraper.getInstance().getCacheCienciaWar().put(StringUtils.stripAccents(investigador.getNombreCompleto()).toUpperCase(), gruposInvestigador);
                    CacheManagerScraper.getInstance().getCacheCienciaWar().forEach(e -> LOG.info("KEYS CACHE WAR" + e.getKey()));
                }
            
            LOG.info("CANTIDAD GRUPOS " + gruposInvestigador.size());
            
            for (GrupoInvestigacion grupo : gruposInvestigador) {
                GrupoInvestigacion grupoExterno;
                Investigador investigadorExterno = null;
                Software softwareExterno = null;

                LOG.info("GRUPO  " + grupo.getNombre());
                grupoExterno = CacheManagerScraper.getInstance().getCacheGrupo().get(StringUtils.stripAccents(grupo.getNombre()));
                
                LOG.info("GRUPO CACHE " + grupoExterno);
                if (grupoExterno == null) {
                    grupoExterno = new ScrapperColcienciasPublico().extraerGrupoInvestigacion(grupo.getUrlGruplac(), false, false, true, false, true);
                    CacheManagerScraper.getInstance().getCacheGrupo().put(StringUtils.stripAccents(grupoExterno.getNombre()).toUpperCase(), grupoExterno);
                    CacheManagerScraper.getInstance().getCacheGrupo().forEach(e -> LOG.info("KEYS CACHE " + e.getKey()));
                }

                Optional<Investigador> resultadoInvestigadorExterno = grupoExterno.getIntegrantes()
                        .stream()
                        .filter(Investigador -> StringUtils.stripAccents(Investigador.getNombreCompleto()).equalsIgnoreCase(StringUtils.stripAccents(investigador.getNombreCompleto())))
                        .findFirst();
                if (!resultadoInvestigadorExterno.equals(Optional.empty())) {
                    investigadorExterno = resultadoInvestigadorExterno.get();
                    Optional<Software> resultadoSoftware = grupoExterno.getSoftware()
                            .stream()
                            .filter(Software -> StringUtils.stripAccents(Software.getNombre()).equalsIgnoreCase(StringUtils.stripAccents(software.getNombre())))
                            .findFirst();
                    if (!resultadoSoftware.equals(Optional.empty())) {

                        softwareExterno = resultadoSoftware.get();
                        LOG.info("ANO ARTICULO " + softwareExterno.getAno());
                        LOG.info("INICIO VINCULACION " + investigadorExterno.getInicioVinculacion());
                        if (investigadorExterno.getFinVinculacion() == null) {
                            investigadorExterno.setFinVinculacion(Utilidades.getFechaActual());
                            LOG.info("FIN VINCULACION " + investigadorExterno.getFinVinculacion());
                        }
                        if (Integer.parseInt(investigadorExterno.getInicioVinculacion().substring(0, 4)) <= softwareExterno.getAno()
                                && Integer.parseInt(investigadorExterno.getFinVinculacion().substring(0, 4)) >= softwareExterno.getAno()) {

                            if (!gruposColaboracion.contains(grupoExterno.getNombre())) {
                                gruposColaboracion.add(grupoExterno.getNombre());
                                investigador.setGruposColaboracion(investigador.getGruposColaboracion() + 1);
                            }
                            if (grupoExterno.getNombre().equalsIgnoreCase(grupoInvestigacion.getNombre())) {
                                investigador.setIntegranteGrupo(1);
                            }
                        }
                    }
                }
            }
            LOG.info("GRUPOS COLABORACION INVESTIGADOR" + investigador.getGruposColaboracion());
        }
    }
}
