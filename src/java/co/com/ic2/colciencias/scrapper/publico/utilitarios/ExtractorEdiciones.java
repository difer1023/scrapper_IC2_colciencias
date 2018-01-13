/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Edicion;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorEdiciones {
    public static ArrayList<Edicion> extraerEdiciones(Elements elements) {
        ArrayList<Edicion> ediciones = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Edicion edicion = new Edicion();
            edicion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            edicion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEdicion=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            edicion.setPais(detalleEdicion.split(",")[0].substring(1));
            String ano=detalleEdicion.split(",")[1].substring(1,5);
            edicion.setAno(Integer.parseInt(ano));
            edicion.setEditorial(detalleEdicion.split(",")[2].substring(12));
            edicion.setIdiomas(detalleEdicion.split(",")[3].substring(10));
            edicion.setNumPaginas(detalleEdicion.split(",")[4].substring(10));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            edicion.setAutores(autores);

            ediciones.add(edicion);
        }
        return ediciones;
    }
}
