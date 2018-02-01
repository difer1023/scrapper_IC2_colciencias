/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
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
 *
 * @author Difer
 */
public class ExtractorSoftwares {
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
           
            //String detalleSoftwareNombres=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            //software.setNombreComercial(detalleSoftwareNombres.split(":")[1].substring(12).split(",")[0]);
            //software.setNombreProyecto(detalleSoftwareNombres.split(":")[3].substring(12).split(",")[2]);
            
            String detalleSoftwareI=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            software.setInstitucion(detalleSoftwareI.split(":")[1].substring(1));
           
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            software.setAutores(autores);

            softwares.add(software);
        }
        return softwares;
    }
    
    public static ArrayList<Software> extraerSoftwaresPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<Software> softwares = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                Software software = new Software();
                System.out.println("FILA"+ elements.get(i).text());
                
                software.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
              software.setAno(Integer.parseInt(ano));
              software.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                
                software.setRegistrosAsociados(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                
                softwares.add(software);
            }
        }
        return softwares;
    }
}
