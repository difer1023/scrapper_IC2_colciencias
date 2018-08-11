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
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EmpresaBaseTecnologica;
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
 * Clase encargada de extraer la información relacionada con el producto Empresa de base tecnológica
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorEmpresasBaseTecnologica {
    
    /**
    * Método encargado de extraer información sobre el producto Empresas de base tecnológica
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<EmpresaBaseTecnologica> extraerEmpresasBaseTecnologica(Elements elements) {
        ArrayList<EmpresaBaseTecnologica> empresasBT = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EmpresaBaseTecnologica empresaBT = new EmpresaBaseTecnologica();
            empresaBT.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            empresaBT.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEmpresaBT=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleEmpresaBT.split(", NIT:")[0].split(" ")[2];
            empresaBT.setAno(Integer.parseInt(ano));
            
            empresaBT.setNit(detalleEmpresaBT.split(",")[1].substring(6));
            empresaBT.setFechaRegistro(detalleEmpresaBT.split(",")[2].substring(32));
            
            String detalleEmpresaBTEstado=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            empresaBT.setEstado(detalleEmpresaBTEstado.substring(1));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            empresaBT.setAutores(autores);

            empresasBT.add(empresaBT);
        }
        return empresasBT;
    }
    
    
    /**
    * Método encargado de extraer información sobre el producto Empresa de base tecnlógica
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<EmpresaBaseTecnologica> extraerEmpresaBaseTecnologicaPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<EmpresaBaseTecnologica> empresasBT = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                EmpresaBaseTecnologica empresaBT = new EmpresaBaseTecnologica();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                empresaBT.setAno(ano);

                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_EBT-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){

                    empresaBT.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        empresaBT.setCategoria(categoria);
                        empresaBT.setClasificado(true);
                    }
                    empresaBT.setTipoIdentificacion(1);
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
                        Logger.getLogger(ExtractorEmpresasBaseTecnologica.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String certificadoCamara=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get();
                    if(!certificadoCamara.equals("")||certificadoCamara!=null){
                    empresaBT.setCertificadoCamaraComercio(true);
                    }else{
                    empresaBT.setCertificadoCamaraComercio(false);
                    }


                    empresaBT.setNit(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());


                    String certificacionInstitucion=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()/text()").evaluate(doc).get();
                    if(!certificacionInstitucion.equals("")||certificacionInstitucion!=null){
                    empresaBT.setCertificacionInstitucional(true);
                    }else{
                    empresaBT.setCertificacionInstitucional(false);
                    }

                    empresasBT.add(empresaBT);
                }
            }
        }
        return empresasBT;
    }
    
    public static void analizarAutores(EmpresaBaseTecnologica empresaBaseTecnologica, GrupoInvestigacion grupoInvestigacion) {
        LOG.info("PRODUCTO " + empresaBaseTecnologica.getNombre());
        HashSet<String> gruposColaboracion = new HashSet<String>();
        for (Investigador investigador : empresaBaseTecnologica.getAutores()) {
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
                EmpresaBaseTecnologica empresaBaseTecnologicaExterno = null;

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
                    Optional<EmpresaBaseTecnologica> resultadoCapituloLibro = grupoExterno.getEmpresaBaseTecnologica()
                            .stream()
                            .filter(EmpresaBaseTecnologica -> StringUtils.stripAccents(EmpresaBaseTecnologica.getNombre()).equalsIgnoreCase(StringUtils.stripAccents(empresaBaseTecnologica.getNombre())))
                            .findFirst();
                    if (!resultadoCapituloLibro.equals(Optional.empty())) {

                        empresaBaseTecnologicaExterno = resultadoCapituloLibro.get();
                        LOG.info("ANO ARTICULO " + empresaBaseTecnologicaExterno.getAno());
                        LOG.info("INICIO VINCULACION " + investigadorExterno.getInicioVinculacion());
                        if (investigadorExterno.getFinVinculacion() == null) {
                            investigadorExterno.setFinVinculacion(Utilidades.getFechaActual());
                            LOG.info("FIN VINCULACION " + investigadorExterno.getFinVinculacion());
                        }
                        if (Integer.parseInt(investigadorExterno.getInicioVinculacion().substring(0, 4)) <= empresaBaseTecnologicaExterno.getAno()
                                && Integer.parseInt(investigadorExterno.getFinVinculacion().substring(0, 4)) >= empresaBaseTecnologicaExterno.getAno()) {

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
