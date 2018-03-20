/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EsquemaCircuito;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encarhada de extraer información relacionada con el producto Esquemas de circuito
 * extrae informaicón de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorEsquemasCircuito {
    
    /**
    * Método encargado de extraer información sobre el producto Esquemas de Circuito
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<EsquemaCircuito> extraerEsquemasCircuito(Elements elements) {
        ArrayList<EsquemaCircuito> esquemasCircuito = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EsquemaCircuito esquemaCircuito = new EsquemaCircuito();
            esquemaCircuito.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            esquemaCircuito.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDisenoIndustrial=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            esquemaCircuito.setPais(detalleDisenoIndustrial.split(",")[0].substring(1));
            String ano=detalleDisenoIndustrial.split(",")[1].substring(1,5);
            esquemaCircuito.setAno(Integer.parseInt(ano));
            esquemaCircuito.setDisponibilidad(detalleDisenoIndustrial.split(",")[2].substring(17));
            esquemaCircuito.setInstitucion(detalleDisenoIndustrial.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            esquemaCircuito.setAutores(autores);

            esquemasCircuito.add(esquemaCircuito);
        }
        return esquemasCircuito;
    }
}
