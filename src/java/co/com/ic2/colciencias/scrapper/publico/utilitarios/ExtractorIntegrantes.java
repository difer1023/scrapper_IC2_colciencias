/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con los integrantes
 * Extrae los integrantes de un grupo de investigación
 * Extrae los integrantes relacionados con un producto presente en el grupo de investigación
 * @author Difer
 */
public class ExtractorIntegrantes {
    public static ArrayList<Investigador> extraerIntegrantes(Elements elements) {
        ArrayList<Investigador> integrantes = new ArrayList();
       for(int i=2;i<elements.size();i++){
            Investigador integrante = new Investigador();
            integrante.setNombreCompleto(Xsoup.compile("/td[1]/a/text()").evaluate(elements.get(i)).get());
            integrante.setHorasDedicacion(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
            integrante.setInicioVinculacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[0]);
            integrante.setFinVinculacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[1]);
            
            String url= (Xsoup.compile("/td[1]/a/@href").evaluate(elements.get(i)).get());
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException ex) {
                Logger.getLogger(ExtractorIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String categoriaIntegrante=Xsoup.compile("/html/body/table/tbody/tr[2]/td/blockquote/table/tbody/tr[1]/td[1]/text()").evaluate(doc).get();
            
            if(categoriaIntegrante==null){

                String campo = Xsoup.compile("/html/body/div/div[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/text()").evaluate(doc).get();
                
                if(campo!=null && StringUtils.stripAccents(campo).equalsIgnoreCase("Categoria ")){
                categoriaIntegrante=Xsoup.compile("/html/body/div/div[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/text()").evaluate(doc).get();
                integrante.setCategoria(categoriaIntegrante);
                }
            }else{
                categoriaIntegrante=categoriaIntegrante.replace("Categoría", "");
                integrante.setCategoria(categoriaIntegrante);
            }
            integrantes.add(integrante);
        }
        return integrantes;
    }
}
