/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoDirigido;
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
 * Clase encargada de extraer la información relacionada con el producto Trabajos dirigidos
 * Extrae información de la parte pública y la parte privada del gruplac
 * Extrae tesis de doctorado, maestría y pregrado
 * @author Difer
 */
public class ExtractorTrabajosDirigidos {
    public static ArrayList<TrabajoDirigido> extraerTrabajosDirigidos(Elements elements) {
        ArrayList<TrabajoDirigido> trabajosDirigidos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            TrabajoDirigido trabajoDirigido = new TrabajoDirigido();
            trabajoDirigido.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            if(!trabajoDirigido.getTipo().equalsIgnoreCase("Trabajos dirigidos/Tutorías de otro tipo")){
            trabajoDirigido.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            System.out.println("tipo "+trabajoDirigido.getTipo());
            String detalleTrabajoDirigido=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleTrabajo1=detalleTrabajoDirigido.split(" hasta ");
            String [] detalleTrabajo2= detalleTrabajo1[1].split(",");
            String [] detalleTrabajo3= detalleTrabajo2[0].split(" ");
            
            System.out.println("DETALLETRABAJODIRIGIDO "+detalleTrabajoDirigido);
            System.out.println("DETALLETRABAJO2 "+detalleTrabajo2[1]);
            System.out.println("DETALLETRABAJO3 "+detalleTrabajo3[0]);
            
            if(detalleTrabajo3.length>=2){
                System.out.println("DETALLETRABAJO3 "+detalleTrabajo3[1]);
            trabajoDirigido.setAnoFin(Integer.parseInt(detalleTrabajo3[1]));
            }
            String detalleTrabajoDirigido2=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            trabajoDirigido.setValoracion(detalleTrabajoDirigido2.split(",")[1].substring(13));
            trabajoDirigido.setInstitucion(detalleTrabajoDirigido2.split(",")[2].substring(14));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            trabajoDirigido.setAutores(autores);

            trabajosDirigidos.add(trabajoDirigido);
            }
        }
        return trabajosDirigidos;
    }
    
    public static ArrayList<TrabajoDirigido> extraerTesisDoctoradoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<TrabajoDirigido> trabajosDoctorado = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                TrabajoDirigido trabajoDoctorado = new TrabajoDirigido();
                System.out.println("FILA"+ elements.get(i).text());
                
                trabajoDoctorado.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                trabajoDoctorado.setAnoFin(Integer.parseInt(ano));
                trabajoDoctorado.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                trabajoDoctorado.setAutorTrabajo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                trabajoDoctorado.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                trabajoDoctorado.setTipoDireccion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                trabajoDoctorado.setValoracion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
                
                trabajosDoctorado.add(trabajoDoctorado);
            }
        }
        return trabajosDoctorado;
    }
    
    public static ArrayList<TrabajoDirigido> extraerTesisMaestriaPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<TrabajoDirigido> trabajosMaestria = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                TrabajoDirigido trabajoMaestria = new TrabajoDirigido();
                System.out.println("FILA"+ elements.get(i).text());
                
                trabajoMaestria.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                trabajoMaestria.setAnoFin(Integer.parseInt(ano));
                trabajoMaestria.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                trabajoMaestria.setAutorTrabajo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                trabajoMaestria.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                trabajoMaestria.setTipoDireccion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                trabajoMaestria.setValoracion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
                
                trabajosMaestria.add(trabajoMaestria);
            }
        }
        return trabajosMaestria;
    }
    
    public static ArrayList<TrabajoDirigido> extraerTesisPregradoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<TrabajoDirigido> trabajosPregrado = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                TrabajoDirigido trabajoPregrado = new TrabajoDirigido();
                System.out.println("FILA"+ elements.get(i).text());
                
                trabajoPregrado.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                trabajoPregrado.setAnoFin(Integer.parseInt(ano));
                trabajoPregrado.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                trabajoPregrado.setAutorTrabajo(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                trabajoPregrado.setInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                trabajoPregrado.setTipoDireccion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                trabajoPregrado.setValoracion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
                
                trabajosPregrado.add(trabajoPregrado);
            }
        }
        return trabajosPregrado;
    }
}
