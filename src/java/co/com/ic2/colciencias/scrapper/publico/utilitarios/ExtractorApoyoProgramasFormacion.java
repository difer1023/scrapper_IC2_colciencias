/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesModelo;
import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ApoyoProgramaFormacion;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion.USER_AGENT;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer la información relacionada con el producto Apoyo a programas de formación
 * Extrae información de la parte privada del gruplac
 * @author L
 */
public class ExtractorApoyoProgramasFormacion {
    
    /**
    * Método encargado de extraer información sobre el producto Apoyo a creación de programas
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<ApoyoProgramaFormacion> extraerApoyoCreacionProgramasPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<ApoyoProgramaFormacion> apoyoProgramasFormacion = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                ApoyoProgramaFormacion apoyoProgramaFormacion = new ApoyoProgramaFormacion();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano=0;
                try{
                    ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get().substring(0,4));
                apoyoProgramaFormacion.setAno(ano);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    System.out.println("Error en ano programa formacion");
                }
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_AP-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){

                    apoyoProgramaFormacion.setTipoApoyo(1);
                    apoyoProgramaFormacion.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());


                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        apoyoProgramaFormacion.setCategoria(categoria);
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
                        Logger.getLogger(ExtractorApoyoProgramasFormacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try{
                    apoyoProgramaFormacion.setActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                    } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay acto administrativo");}
                    try{
                    String fechaActoAdministrativo=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get().trim();
                    if(!fechaActoAdministrativo.isEmpty()){
                    apoyoProgramaFormacion.setFechaActoAdministrativo(Utilidades.transformarFormatoFecha("dd/MM/yyyy", "yyyy-MM-dd", fechaActoAdministrativo));
                    }
                    } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto administrativo");}
                    try{
                    apoyoProgramaFormacion.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                    } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto institucion");}

                    apoyoProgramasFormacion.add(apoyoProgramaFormacion);
                }
            }
        }
        return apoyoProgramasFormacion;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Apoyo a creación de cursos
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<ApoyoProgramaFormacion> extraerApoyoCreacionCursosPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<ApoyoProgramaFormacion> apoyoCreacionCursos = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                ApoyoProgramaFormacion apoyoCreacionCurso = new ApoyoProgramaFormacion();
//                System.out.println("FILA"+ elements.get(i).text());
                apoyoCreacionCurso.setTipoApoyo(2);
                apoyoCreacionCurso.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get().substring(0,4);
                apoyoCreacionCurso.setAno(Integer.parseInt(ano));
                
                String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                if(!categoria.equals("No cumple existencia")){
                    apoyoCreacionCurso.setCategoria(categoria);
                    apoyoCreacionCurso.setClasificado(true);
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
                Logger.getLogger(ExtractorApoyoProgramasFormacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                apoyoCreacionCurso.setActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                try{
                String fechaActoAdministrativo=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                
                apoyoCreacionCurso.setFechaActoAdministrativo(Utilidades.transformarFormatoFecha("dd/MM/yyyy", "yyyy-MM-dd", fechaActoAdministrativo));
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto administrativo");}
                
                apoyoCreacionCurso.setProgramaSeleccionado(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                
                
                apoyoCreacionCursos.add(apoyoCreacionCurso);
            }
        }
        return apoyoCreacionCursos;
    }
}
