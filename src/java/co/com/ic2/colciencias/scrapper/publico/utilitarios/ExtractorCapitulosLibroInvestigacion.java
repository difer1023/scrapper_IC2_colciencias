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
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ArticuloInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import co.com.ic2.colciencias.scrapper.publico.ScrapperColcienciasPublico;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion.USER_AGENT;
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
 * Clase encargada de extraer la información relacionada con el producto Capítulo de libro
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorCapitulosLibroInvestigacion {
    
    static final Logger LOG = Logger.getLogger(ExtractorCapitulosLibroInvestigacion.class.getName());
    /**
    * Método encargado de extraer información sobre el producto Captpitulo de Libro
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<CapituloLibroPublicado> extraerCapitulosLibroPublicados(Elements elements) {
        ArrayList<CapituloLibroPublicado> capitulosLibroPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            CapituloLibroPublicado capituloLibroPublicado = new CapituloLibroPublicado();
            capituloLibroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            capituloLibroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleCapLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            capituloLibroPublicado.setPais(detalleCapLibro.split(",")[0].substring(1));
            
            String ano=detalleCapLibro.split(",")[1].substring(1,5);
            capituloLibroPublicado.setAno(Integer.parseInt(ano));
            capituloLibroPublicado.setTituloLibro(detalleCapLibro.split(",")[2].substring(1));
            
            String [] detalleCapLibro2=detalleCapLibro.split("ISBN:");
            
            capituloLibroPublicado.setIsbn(detalleCapLibro2[1].split(",")[0].substring(1));
            capituloLibroPublicado.setEditorial(detalleCapLibro2[1].split(",")[3].substring(1));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            capituloLibroPublicado.setAutores(autores);

            capitulosLibroPublicados.add(capituloLibroPublicado);
        }
        return capitulosLibroPublicados;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Capítulo de Libro
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<CapituloLibroPublicado> extraerCapitulosLibroPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<CapituloLibroPublicado> capitulosLibro = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                CapituloLibroPublicado capituloLibro = new CapituloLibroPublicado();
//                System.out.println("FILA"+ elements.get(i).text());
                
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                capituloLibro.setAno(ano);
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_CAP_LIB-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                    capituloLibro.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());


                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        capituloLibro.setCategoria(categoria);
                        capituloLibro.setClasificado(true);
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
                        Logger.getLogger(ExtractorCapitulosLibroInvestigacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    capituloLibro.setTituloLibro(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());

                    capituloLibro.setIsbn(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());

                    String [] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split("-");

                    try {
                    capituloLibro.setAno(Integer.parseInt(fechaPublicacion[0].trim()));
                    capituloLibro.setFechaProducto(ano+"-"+Utilidades.transformarMesANumero(fechaPublicacion[1].trim())+"-1");
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error en extraerCapitulosLibroPrivado no existe año "+e);}
                    String numeroAutores=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
                    try {
                    capituloLibro.setNumeroAutores(Integer.parseInt(numeroAutores.split("\\) ")[0].split("\\(")[1]));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Errorno existe numero autores");}
                    capituloLibro.setEditorial(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());

                    capituloLibro.setPais(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[8]/td[3]/text()").evaluate(doc).get());

                    String guiaVerificacion=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[9]/td[3]/text()").evaluate(doc).get();
                    if(!guiaVerificacion.equals("")||guiaVerificacion!=null){
                    capituloLibro.setRequisitosGuiaVerificacion(true);
                    }else{
                    capituloLibro.setRequisitosGuiaVerificacion(false);
                    }

                    capitulosLibro.add(capituloLibro);
                }
            }
        }
        return capitulosLibro;
    }
    
    public static void analizarAutores(CapituloLibroPublicado capituloLibro, GrupoInvestigacion grupoInvestigacion) {
        LOG.info("PRODUCTO " + capituloLibro.getNombre());
        HashSet<String> gruposColaboracion = new HashSet<String>();
        for (Investigador investigador : capituloLibro.getAutores()) {
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
                CapituloLibroPublicado capituloLibroExterno = null;

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
                    Optional<CapituloLibroPublicado> resultadoCapituloLibro = grupoExterno.getCapituloDeLibro()
                            .stream()
                            .filter(CapituloLibro -> StringUtils.stripAccents(CapituloLibro.getNombre()).equalsIgnoreCase(StringUtils.stripAccents(capituloLibro.getNombre())))
                            .findFirst();
                    if (!resultadoCapituloLibro.equals(Optional.empty())) {

                        capituloLibroExterno = resultadoCapituloLibro.get();
                        LOG.info("ANO ARTICULO " + capituloLibroExterno.getAno());
                        LOG.info("INICIO VINCULACION " + investigadorExterno.getInicioVinculacion());
                        if (investigadorExterno.getFinVinculacion() == null) {
                            investigadorExterno.setFinVinculacion(Utilidades.getFechaActual());
                            LOG.info("FIN VINCULACION " + investigadorExterno.getFinVinculacion());
                        }
                        if (Integer.parseInt(investigadorExterno.getInicioVinculacion().substring(0, 4)) <= capituloLibroExterno.getAno()
                                && Integer.parseInt(investigadorExterno.getFinVinculacion().substring(0, 4)) >= capituloLibroExterno.getAno()) {

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
