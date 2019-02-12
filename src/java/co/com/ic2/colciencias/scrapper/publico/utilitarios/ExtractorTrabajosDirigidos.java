/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesModelo;
import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoGrado;
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
 * Clase encargada de extraer la información relacionada con el producto Trabajos dirigidos
 * Extrae información de la parte pública y la parte privada del gruplac
 * Extrae tesis de doctorado, maestría y pregrado
 * @author Difer
 */
public class ExtractorTrabajosDirigidos {
    
    /**
    * Método encargado de extraer información sobre el producto Trabajo dirigido
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<TrabajoGrado> extraerTrabajosDirigidos(Elements elements) {
        ArrayList<TrabajoGrado> trabajosDirigidos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            TrabajoGrado trabajoDirigido = new TrabajoGrado();
            trabajoDirigido.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            if(!trabajoDirigido.getTipo().equalsIgnoreCase("Trabajos dirigidos/Tutorías de otro tipo")){
            trabajoDirigido.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
           
            String detalleTrabajoGrado=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleTrabajo1=detalleTrabajoGrado.split(" hasta ");
            String [] detalleTrabajo2= detalleTrabajo1[1].split(",");
            String [] detalleTrabajo3= detalleTrabajo2[0].split(" ");
            
            try{
            trabajoDirigido.setTipoDireccion(detalleTrabajoGrado.split("Tipo de orientación: ")[1]);
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("Error tipo de orientacion trabajo de grado");}
            
            if(detalleTrabajo3.length>=2){
            trabajoDirigido.setAnoFin(Integer.parseInt(detalleTrabajo3[1]));
            }
           
            try{
            String nombreEstudiante=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            String [] detalleNombreEstudiante= nombreEstudiante.split(", Programa académico:");
            trabajoDirigido.setAutorTrabajo(detalleNombreEstudiante[0].split("Nombre del estudiante: ")[1]);
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("Error nombre estudiante trabajo dirigido");}
            
            String detalleTrabajoGrado2=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            trabajoDirigido.setValoracion(detalleTrabajoGrado2.split(",")[1].substring(13));
            trabajoDirigido.setInstitucion(detalleTrabajoGrado2.split(",")[2].substring(14));
         
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            trabajoDirigido.setAutores(autores);

            trabajosDirigidos.add(trabajoDirigido);
            }
        }
        return trabajosDirigidos;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Tesis de doctorado
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<TrabajoGrado> extraerTesisDoctoradoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<TrabajoGrado> trabajosDoctorado = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                TrabajoGrado trabajoDoctorado = new TrabajoGrado();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                trabajoDoctorado.setAnoFin(ano);
                
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_TD-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    trabajoDoctorado.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        trabajoDoctorado.setCategoria(categoria);
                        trabajoDoctorado.setClasificado(true);
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
                        Logger.getLogger(ExtractorTrabajosDirigidos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    trabajoDoctorado.setAutorTrabajo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                    trabajoDoctorado.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                    trabajoDoctorado.setTipoDireccion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                    trabajoDoctorado.setValoracion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());

                    trabajosDoctorado.add(trabajoDoctorado);
                }
            }
        }
        return trabajosDoctorado;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Tesis de maestría
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<TrabajoGrado> extraerTesisMaestriaPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies, int anoFinVentanaObservacion) {
        ArrayList<TrabajoGrado> trabajosMaestria = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                TrabajoGrado trabajoMaestria = new TrabajoGrado();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                trabajoMaestria.setAnoFin(ano);

                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_TM-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    trabajoMaestria.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia")){
                        trabajoMaestria.setCategoria(categoria);
                        trabajoMaestria.setClasificado(true);
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
                        Logger.getLogger(ExtractorTrabajosDirigidos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    trabajoMaestria.setAutorTrabajo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                    trabajoMaestria.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                    trabajoMaestria.setTipoDireccion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                    trabajoMaestria.setValoracion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());

                    trabajosMaestria.add(trabajoMaestria);
                }
            }
        }
        return trabajosMaestria;
    }
    
    /**
    * Método encargado de extraer información sobre el producto tesis de pregrado
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<TrabajoGrado> extraerTesisPregradoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies, int anoFinVentanaObservacion) {
        ArrayList<TrabajoGrado> trabajosPregrado = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                TrabajoGrado trabajoPregrado = new TrabajoGrado();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                trabajoPregrado.setAnoFin(ano);

                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_TP-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    trabajoPregrado.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia")){
                        trabajoPregrado.setCategoria(categoria);
                        trabajoPregrado.setClasificado(true);
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
                        Logger.getLogger(ExtractorTrabajosDirigidos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    trabajoPregrado.setAutorTrabajo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                    trabajoPregrado.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                    trabajoPregrado.setTipoDireccion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                    trabajoPregrado.setValoracion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());

                    trabajosPregrado.add(trabajoPregrado);
                }
            }
        }
        return trabajosPregrado;
    }
}
