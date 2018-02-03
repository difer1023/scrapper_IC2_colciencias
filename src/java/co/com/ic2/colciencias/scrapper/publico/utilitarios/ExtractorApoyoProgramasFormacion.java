/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.ApoyoProgramaFormacion;
import co.com.ic2.colciencias.scrapper.publico.ScraperPublico2;
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
 * Clase encargada de extraer la información relacionada con el producto Apoyo a programas de formación
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author L
 */
public class ExtractorApoyoProgramasFormacion {
    
    public static ArrayList<ApoyoProgramaFormacion> extraerApoyoCreacionProgramasPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<ApoyoProgramaFormacion> apoyoProgramasFormacion = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                ApoyoProgramaFormacion apoyoProgramaFormacion = new ApoyoProgramaFormacion();
                System.out.println("FILA"+ elements.get(i).text());
                
                apoyoProgramaFormacion.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                try{
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get().substring(0,4);
                apoyoProgramaFormacion.setAno(Integer.parseInt(ano));
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    System.out.println("Error en ano programa formacion");
                }
                
                apoyoProgramaFormacion.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
                String enlaceDetalle=("http://scienti.colciencias.gov.co:8080"+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                Document doc = null;
            try {
                Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                doc=res2.parse();
            } catch (IOException ex) {
                Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                try{
                apoyoProgramaFormacion.setActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay acto administrativo");}
                try{
                apoyoProgramaFormacion.setFechaActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto administrativo");}
                try{
                apoyoProgramaFormacion.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay fecha acto institucion");}
                
                
                apoyoProgramasFormacion.add(apoyoProgramaFormacion);
                
            }
        }
        return apoyoProgramasFormacion;
    }
    
    public static ArrayList<ApoyoProgramaFormacion> extraerApoyoCreacionCursosPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<ApoyoProgramaFormacion> apoyoCreacionCursos = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                ApoyoProgramaFormacion apoyoCreacionCurso = new ApoyoProgramaFormacion();
                System.out.println("FILA"+ elements.get(i).text());
                
                apoyoCreacionCurso.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get().substring(0,4);
                apoyoCreacionCurso.setAno(Integer.parseInt(ano));
                apoyoCreacionCurso.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
                String enlaceDetalle=("http://scienti.colciencias.gov.co:8080"+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                Document doc = null;
            try {
                Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                doc=res2.parse();
            } catch (IOException ex) {
                Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          
                apoyoCreacionCurso.setActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                apoyoCreacionCurso.setFechaActoAdministrativo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                apoyoCreacionCurso.setProgramaSeleccionado(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                
                
                apoyoCreacionCursos.add(apoyoCreacionCurso);
            }
        }
        return apoyoCreacionCursos;
    }
}
