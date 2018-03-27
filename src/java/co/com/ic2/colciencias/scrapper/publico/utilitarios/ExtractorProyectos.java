/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Proyecto;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion.USER_AGENT;
import java.io.IOException;
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
 * Clase encargada de extraer la información relacionada con el producto Proyecto
 * Extrae información de la parte pública y la parte privada del gruplac
 * Extrae información sobre proyectos de investigación y desarrollo, ID+I, extensión de responsabilidad social
 * @author Difer
 */
public class ExtractorProyectos {
    
    /**
    * Método encargado de extraer información sobre el producto Proyecto
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<Proyecto> extraerProyectos(Elements elements) {
        ArrayList<Proyecto> proyectos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Proyecto proyecto = new Proyecto();
            System.out.println("ELEMENTO "+Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
            proyecto.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            proyecto.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(2));
            
            String detalleProyecto=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            System.out.println("DETALLE PROYECTO "+detalleProyecto);
            if(!detalleProyecto.equals(" ")){
            String [] detalleProyecto1=detalleProyecto.split(" - ");
            String [] ano=detalleProyecto1[0].split("/");
            proyecto.setAnoInicio(Integer.parseInt(ano[0].substring(1)));
            }        
            proyectos.add(proyecto);
        }
        return proyectos;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Proyecto de investigación y desarrollo
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<Proyecto> extraerProyectoInvestigacionDesarrolloPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<Proyecto> proyectosInvestigacionDesarrollo = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                Proyecto proyectoInvestigacionDesarrollo = new Proyecto();
                System.out.println("FILA"+ elements.get(i).text());
                
                proyectoInvestigacionDesarrollo.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                proyectoInvestigacionDesarrollo.setAnoInicio(Integer.parseInt(ano));
                proyectoInvestigacionDesarrollo.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
                String enlaceDetalle=(ConstantesScrapper.urlGruplac+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                Document doc = null;
                try {
                    Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                    doc=res2.parse();
                } catch (IOException ex) {
                    Logger.getLogger(ExtractorProyectos.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                try{
                proyectoInvestigacionDesarrollo.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get().split("entidades: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay institucion");}
                String numeroInvestigadoresPrincipales=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                try{
                proyectoInvestigacionDesarrollo.setNumeroInvestigadoresPrincipales(Integer.parseInt(numeroInvestigadoresPrincipales.split("\\) ")[0].split("\\(")[1]));
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay numero de investigadores principales");}
                try{
                proyectoInvestigacionDesarrollo.setActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split("administrativo: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay acto administrativo");}
                try{
                proyectoInvestigacionDesarrollo.setFechaActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get().split("administrativo: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto administrativo");}
                proyectosInvestigacionDesarrollo.add(proyectoInvestigacionDesarrollo);
            }
        }
        return proyectosInvestigacionDesarrollo;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Proyecto ID+I
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<Proyecto> extraerProyectoIDIPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<Proyecto> proyectosIDI = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                Proyecto proyectoIDI = new Proyecto();
                System.out.println("FILA"+ elements.get(i).text());
                
                proyectoIDI.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                proyectoIDI.setAnoInicio(Integer.parseInt(ano));
                proyectoIDI.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
                String enlaceDetalle=(ConstantesScrapper.urlGruplac+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                Document doc = null;
                try {
                    Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                    doc=res2.parse();
                } catch (IOException ex) {
                    Logger.getLogger(ExtractorProyectos.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                try{
                proyectoIDI.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get().split("entidades: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay institucion");}
                String numeroInvestigadoresPrincipales=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                try{
                proyectoIDI.setNumeroInvestigadoresPrincipales(Integer.parseInt(numeroInvestigadoresPrincipales.split("\\) ")[0].split("\\(")[1]));
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay numero de investigadores principales");}
                try{
                proyectoIDI.setActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split("administrativo: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay acto administrativo");}
                try{
                proyectoIDI.setFechaActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get().split("administrativo: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto administrativo");}
                
                proyectosIDI.add(proyectoIDI);
            }
        }
        return proyectosIDI;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Proyecto de extensión en responsabilidad social
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<Proyecto> extraerProyectoExtensionResponsabilidadSocialPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<Proyecto> proyectosExtension = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                Proyecto proyectoExtension = new Proyecto();
                System.out.println("FILA"+ elements.get(i).text());
                
                proyectoExtension.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                try{
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                proyectoExtension.setAnoInicio(Integer.parseInt(ano));
                }catch(NumberFormatException e){System.out.println("Error no existe año inicio");}
                proyectoExtension.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
                String enlaceDetalle=(ConstantesScrapper.urlGruplac+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                Document doc = null;
                try {
                    Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                    doc=res2.parse();
                } catch (IOException ex) {
                    Logger.getLogger(ExtractorProyectos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String fechaInicio=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get().split("-")[0];
                try{
                proyectoExtension.setAnoInicio(Integer.parseInt(fechaInicio));
                } catch(ArrayIndexOutOfBoundsException | NumberFormatException e){System.out.println("Error no hay fecha inicio");}
                String fechaFin=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                try{
                proyectoExtension.setAnoFin(Integer.parseInt(fechaFin));
                } catch(ArrayIndexOutOfBoundsException | NumberFormatException e){System.out.println("Error no hay fecha fin");}
                
                String numeroInvestigadoresPrincipales=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get();
                try{
                proyectoExtension.setNumeroInvestigadoresPrincipales(Integer.parseInt(numeroInvestigadoresPrincipales.split("\\) ")[0].split("\\(")[1]));
                } catch(ArrayIndexOutOfBoundsException | NumberFormatException e){System.out.println("Error no hay numero de investigadores principales");}
              
                String numeroInvestigadoresParticipantes=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
                try{
                proyectoExtension.setNumeroInvestigadoresPrincipales(Integer.parseInt(numeroInvestigadoresParticipantes.split("\\) ")[0].split("\\(")[1]));
                } catch(ArrayIndexOutOfBoundsException | NumberFormatException e){System.out.println("Error no hay numero investigadores participantes");}
                
                proyectosExtension.add(proyectoExtension);
            }
        }
        return proyectosExtension;
    }
}
