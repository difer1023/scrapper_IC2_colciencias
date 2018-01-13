/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.scrapper.publico.ScraperPublico2;
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
 *
 * @author Difer
 */
public class ExtractorIntegrantes {
    public static ArrayList<Integrante> extraerIntegrantes(Elements elements) {
        ArrayList<Integrante> integrantes = new ArrayList();
       for(int i=2;i<elements.size();i++){
            Integrante integrante = new Integrante();
            integrante.setNombreCompleto(Xsoup.compile("/td[1]/a/text()").evaluate(elements.get(i)).get());
            integrante.setVinculacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
            integrante.setHorasDedicacion(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
            integrante.setInicioVinculacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[0]);
            integrante.setFinVinculacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[1]);
            
            String url= (Xsoup.compile("/td[1]/a/@href").evaluate(elements.get(i)).get());
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException ex) {
                Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
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
