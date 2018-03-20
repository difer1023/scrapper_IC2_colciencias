/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.LineaInvestigacion;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con las líneas de investigación de un grupo de investigación
 * @author Difer
 */
public class ExtractorLineasInvestigacion {
    public static ArrayList<LineaInvestigacion> extraerLineasInvestigacion(Elements elements) {
        ArrayList<LineaInvestigacion> lineasInvestigacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            LineaInvestigacion lineaInvestigacion = new LineaInvestigacion();
            String nombre=Xsoup.compile("/td/text()").evaluate(elements.get(i)).get();
            lineaInvestigacion.setNombre(nombre.substring(4));
            lineasInvestigacion.add(lineaInvestigacion);
        }
        return lineasInvestigacion;
    }
}
