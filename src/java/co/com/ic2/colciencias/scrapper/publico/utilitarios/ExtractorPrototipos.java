/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.PrototipoIndustrial;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto Prototipo
 * Extrae información de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorPrototipos {
    
    /**
    * Método encargado de extraer información sobre el producto Prototipo
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<PrototipoIndustrial> extraerPrototipos(Elements elements) {
        ArrayList<PrototipoIndustrial> prototipos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            PrototipoIndustrial prototipo = new PrototipoIndustrial();
            prototipo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            prototipo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePrototipo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            prototipo.setPais(detallePrototipo.split(",")[0].substring(1));
            String ano=detallePrototipo.split(",")[1].substring(1,5);
            prototipo.setAno(Integer.parseInt(ano));
            prototipo.setDisponibilidad(detallePrototipo.split(",")[2].substring(17));
            prototipo.setInstitucion(detallePrototipo.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            prototipo.setAutores(autores);

            prototipos.add(prototipo);
        }
        return prototipos;
    }
}
