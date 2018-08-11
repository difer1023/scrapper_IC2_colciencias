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
import co.com.ic2.colciencias.gruplac.productosInvestigacion.LibroInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroLibroPublicado;
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
 * Clase encargada de extraer la información relacionada con el producto Libro
 * Extrae información de la parte pública y la parte privada del gruplac
 *
 * @author Difer
 */
public class ExtractorLibrosInvestigacion {

    /**
     * Método encargado de extraer información sobre el producto Libro publicado
     * Presente en la parte pública del Gruplac
     */
    public static ArrayList<LibroInvestigacion> extraerLibrosPublicados(Elements elements) {
        ArrayList<LibroInvestigacion> librosPublicados = new ArrayList();
        for (int i = 1; i < elements.size(); i++) {
            LibroInvestigacion libroPublicado = new LibroInvestigacion();
            libroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            libroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            libroPublicado.setPais(detalleLibro.split(",")[0].substring(1));
            libroPublicado.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            libroPublicado.setEditorial(detalleLibro.split(",")[3].substring(1));
            try {
                String isbn = detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
                libroPublicado.setIsbn(isbn.substring(1, isbn.length() - 1));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Error en extraerLibrosPublicados ISBN");
            }
//            System.out.println("detalle" + detalleLibro);
            String[] datosAutores = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores = new ArrayList<>();
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Investigador autor = new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            libroPublicado.setAutores(autores);

            librosPublicados.add(libroPublicado);
        }
        return librosPublicados;
    }

    /**
     * Método encargado de extraer información sobre el producto Otro libro
     * publicado Presente en la parte pública del Gruplac en algunos casos
     * pueden ser tipificados como Libros publicados
     */
    public static ArrayList<OtroLibroPublicado> extraerOtrosLibrosPublicados(Elements elements) {
        ArrayList<OtroLibroPublicado> otrosLibrosPublicados = new ArrayList();
        for (int i = 1; i < elements.size(); i++) {
            OtroLibroPublicado otroLibroInvestigacion = new OtroLibroPublicado();
            otroLibroInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            otroLibroInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            otroLibroInvestigacion.setPais(detalleLibro.split(",")[0].substring(1));
            otroLibroInvestigacion.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            otroLibroInvestigacion.setEditorial(detalleLibro.split(",")[3].substring(1));
            try {
                String isbn = detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
                otroLibroInvestigacion.setIsbn(isbn.substring(1, isbn.length() - 1));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Error en extraerOtrosLibrosPublicados ISBN");
            }
//            System.out.println("detalle" + detalleLibro);
            String[] datosAutores = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores = new ArrayList<>();
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Investigador autor = new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            otroLibroInvestigacion.setAutores(autores);

            otrosLibrosPublicados.add(otroLibroInvestigacion);
        }
        return otrosLibrosPublicados;
    }

    /**
     * Método encargado de extraer información sobre el producto Libro publicado
     * Presente en la parte privada del Gruplac
     */
    public static ArrayList<LibroInvestigacion> extraerLibrosPublicadosPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<LibroInvestigacion> librosPublicados = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                LibroInvestigacion libroPublicado = new LibroInvestigacion();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                libroPublicado.setAno(ano);

                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_LIB-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    libroPublicado.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    String categoria = Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if (!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")) {
                        libroPublicado.setCategoria(categoria);
                        libroPublicado.setClasificado(true);
                    }
                    String enlaceDetalle = (ConstantesScrapper.urlGruplac + Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
    //                System.out.println("enlace"+enlaceDetalle); 
                    Document doc = null;
                    try {
                        Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                                .cookies(cookies)
                                .userAgent(USER_AGENT)
                                .proxy(ConstantesScrapper.proxy ? new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy)) : Proxy.NO_PROXY)
                                .execute();
                        doc = res2.parse();
                    } catch (IOException ex) {
                        Logger.getLogger(ExtractorLibrosInvestigacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        String[] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get().split("-");
                        libroPublicado.setMes(fechaPublicacion[1]);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        System.out.println("Error no existe mes");
                    }

                    libroPublicado.setIsbn(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());

                    try {
                        String numeroAutores = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get();
                        libroPublicado.setNumeroAutores(Integer.parseInt(numeroAutores.split("\\) ")[0].split("\\(")[1]));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        System.out.println("Error no existe numero autores");
                    }

                    libroPublicado.setEditorial(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get());

                    libroPublicado.setPais(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());

                    //Campo en blanco
                    String guiaVerificacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[8]/td[3]/text()").evaluate(doc).get();
                    if (!guiaVerificacion.equals("") || guiaVerificacion != null) {
                        libroPublicado.setRequisitosGuiaVerificacion(true);
                    } else {
                        libroPublicado.setRequisitosGuiaVerificacion(false);
                    }

    //                libroPublicado.setCertificacionInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());
    //                libroPublicado.setBookCitationIndex(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
    //                
    //                
    //                libroPublicado.setReferenciaRevistasD(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
    //                
    //                libroPublicado.setReferenciaRevistasA1(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
    //                
    //                String referenciasBCI=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
    //                libroPublicado.setNumeroReferenciasBCI(Integer.parseInt(referenciasBCI.split("Index: ")[1].split("\\).")[0].split("\\(")[1]));
    //                
    //                String referenciasLibB=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
    //                libroPublicado.setNumeroReferenciasLibB(Integer.parseInt(referenciasLibB.split("categoría B")[1].split("\\).")[0].split("\\(")[1]));
    //                
                    librosPublicados.add(libroPublicado);
                }
            }
        }
        return librosPublicados;
    }
    
    public static void analizarAutores(LibroInvestigacion libro, GrupoInvestigacion grupoInvestigacion) {
        LOG.info("PRODUCTO " + libro.getNombre());
        HashSet<String> gruposColaboracion = new HashSet<String>();
        for (Investigador investigador : libro.getAutores()) {
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
                LibroInvestigacion libroExterno = null;

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
                    Optional<LibroInvestigacion> resultadoLibro = grupoExterno.getLibrosResultadoInvestigacion()
                            .stream()
                            .filter(Libro -> StringUtils.stripAccents(Libro.getNombre()).equalsIgnoreCase(StringUtils.stripAccents(libro.getNombre())))
                            .findFirst();
                    if (!resultadoLibro.equals(Optional.empty())) {

                        libroExterno = resultadoLibro.get();
                        LOG.info("ANO ARTICULO " + libroExterno.getAno());
                        LOG.info("INICIO VINCULACION " + investigadorExterno.getInicioVinculacion());
                        if (investigadorExterno.getFinVinculacion() == null) {
                            investigadorExterno.setFinVinculacion(Utilidades.getFechaActual());
                            LOG.info("FIN VINCULACION " + investigadorExterno.getFinVinculacion());
                        }
                        if (Integer.parseInt(investigadorExterno.getInicioVinculacion().substring(0, 4)) <= libroExterno.getAno()
                                && Integer.parseInt(investigadorExterno.getFinVinculacion().substring(0, 4)) >= libroExterno.getAno()) {

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
