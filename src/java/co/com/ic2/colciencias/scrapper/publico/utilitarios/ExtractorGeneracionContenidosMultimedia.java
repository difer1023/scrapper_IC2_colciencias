/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoMultimedia;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de exraer información relacionada con el producto Generación de contenido multimedia
 * Extrae información de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorGeneracionContenidosMultimedia {
    
    /**
    * Método encargado de extraer información sobre el producto Generación de contenido multimedia
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<GeneracionContenidoMultimedia> extraerGeneracionContenidosMultimedia(Elements elements) {
        ArrayList<GeneracionContenidoMultimedia> contenidosMultimedia = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoMultimedia contenidoMultimedia = new GeneracionContenidoMultimedia();
            contenidoMultimedia.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            contenidoMultimedia.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoMultimedia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleContenidoMultimedia.split(",")[0].substring(1,5);
            contenidoMultimedia.setAno(Integer.parseInt(ano));
            
            
            String detalleContenidoMultimedia2=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            contenidoMultimedia.setMedioCirculacion(detalleContenidoMultimedia2.split(",")[0].substring(23));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoMultimedia.setAutores(autores);

            contenidosMultimedia.add(contenidoMultimedia);
        }
        return contenidosMultimedia;
    }
}
