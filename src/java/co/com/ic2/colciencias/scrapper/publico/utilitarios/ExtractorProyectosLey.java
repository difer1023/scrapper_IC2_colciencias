/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ProyectoLey;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto 
 * Proyectos de Ley
 * Extrae información de la parte pública del Gruplac
 * @author L
 */
public class ExtractorProyectosLey {
    /**
    * Método encargado de extraer información sobre el producto 
    * Proyecto de Ley
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<ProyectoLey> extraerProyectosLey(Elements elements) {
        ArrayList<ProyectoLey> proyectosLey = new ArrayList();
        for(int i=2;i<elements.size();i++){
            ProyectoLey proyectoLey = new ProyectoLey();
            proyectoLey.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            proyectoLey.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleProyectoLey=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleProyectoLey.split(",")[1].trim();
            proyectoLey.setAno(Integer.parseInt(ano));
            
            String detalleProyectoLey1 = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            proyectoLey.setInstitucionFinanciadora(detalleProyectoLey1.split(":")[1].trim());
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().split(":")[1].split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            proyectoLey.setAutores(autores);

            proyectosLey.add(proyectoLey);
        }
        return proyectosLey;
    }
}
