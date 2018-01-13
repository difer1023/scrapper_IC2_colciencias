/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Institucion;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author L
 */
public class ExtractorInstituciones {
    public static ArrayList<Institucion> extraerInstituciones(Elements elements) {
        ArrayList<Institucion> instituciones = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Institucion institucion = new Institucion();
            institucion.setNombre(Xsoup.compile("/td/text()").evaluate(elements.get(i)).get().split("-")[1]);
            institucion.setAvalado(Xsoup.compile("/td/text()").evaluate(elements.get(i)).get().split("-")[2].contains("(Avalado)"));
            instituciones.add(institucion);
        }
        return instituciones;
    }  
}
