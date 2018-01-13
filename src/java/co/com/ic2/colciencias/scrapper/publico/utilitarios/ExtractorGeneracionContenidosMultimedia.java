/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoMultimedia;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorGeneracionContenidosMultimedia {
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
            
            //String detalleContenidoMultimedia3=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            //contenidoMultimedia.setInstituciones(detalleContenidoMultimedia3.split(",")[0].substring(22));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoMultimedia.setAutores(autores);

            contenidosMultimedia.add(contenidoMultimedia);
        }
        return contenidosMultimedia;
    }
}
