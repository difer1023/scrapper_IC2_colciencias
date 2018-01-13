/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoImpreso;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorGeneracionContenidosImpresos {
    public static ArrayList<GeneracionContenidoImpreso> extraerGeneracionContenidosImpresos(Elements elements) {
        ArrayList<GeneracionContenidoImpreso> contenidosImpresos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoImpreso contenidoImpreso = new GeneracionContenidoImpreso();
            contenidoImpreso.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            contenidoImpreso.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoImpreso=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            contenidoImpreso.setFecha(detalleContenidoImpreso.split(",")[0].substring(1));
            //tring ano=detalleEdicion.split(",")[1].substring(1,5);
            //contenidoImpreso.setAno(Integer.parseInt(ano));
            contenidoImpreso.setMedioCirculacion(detalleContenidoImpreso.split(",")[2].substring(23));
            
            String detalleContenidoImpreso2=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            contenidoImpreso.setLugarPublicacion(detalleContenidoImpreso2.split(",")[0].substring(22));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoImpreso.setAutores(autores);

            contenidosImpresos.add(contenidoImpreso);
        }
        return contenidosImpresos;
    }
}
