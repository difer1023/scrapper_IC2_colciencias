/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InformeInvestigacion;
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
 * Clase encargada de extraer la información relacionada con el producto Informes finales de investigación
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorInformesInvestigacion {
    public static ArrayList<InformeInvestigacion> extraerInformesInvestigacion(Elements elements) {
        ArrayList<InformeInvestigacion> informesInvestigacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            InformeInvestigacion informeInvestigacion= new InformeInvestigacion();
            informeInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            informeInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleIfi=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleIfi.split(",")[0].substring(1,5);
            informeInvestigacion.setAno(Integer.parseInt(ano));
            informeInvestigacion.setProyecto(detalleIfi.split(",")[1].substring(28));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            informeInvestigacion.setAutores(autores);

            informesInvestigacion.add(informeInvestigacion);
        }
        return informesInvestigacion;
    }
    
    public static ArrayList<InformeInvestigacion> extraerInformesInvestigacionPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<InformeInvestigacion> informesInvestigacion = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                InformeInvestigacion informeInvestigacion = new InformeInvestigacion();
                System.out.println("FILA"+ elements.get(i).text());
                
                informeInvestigacion.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                informeInvestigacion.setAno(Integer.parseInt(ano));
                informeInvestigacion.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                
                informeInvestigacion.setProyecto(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()/text()").evaluate(doc).get());
                
                informesInvestigacion.add(informeInvestigacion);
            }
        }
        return informesInvestigacion;
    }
}
