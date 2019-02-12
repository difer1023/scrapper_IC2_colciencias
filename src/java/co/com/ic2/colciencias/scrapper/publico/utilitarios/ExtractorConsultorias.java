/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Consultoria;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto Consultoría
 * Extrae información de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorConsultorias {
    
    /**
    * Método encargado de extraer información sobre el producto Consultoria
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<Consultoria> extraerConsultorias(Elements elements) {
        ArrayList<Consultoria> consultorias = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Consultoria consultoria = new Consultoria();
            consultoria.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get().substring(3));
            consultoria.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get());
            String detalleConsultoria=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleConsultoria.split(",")[0].split(":")[1].trim();
            consultoria.setAno(Integer.parseInt(ano));
            
            String detalleConsultoria2=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            consultoria.setNumContrato(detalleConsultoria2.split(",")[0].split(":")[1].trim());
            consultoria.setInstitucion(detalleConsultoria2.split(",")[1].split(":")[1].trim());

            consultorias.add(consultoria);
        }
        return consultorias;
    }
}
