/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.Proyecto;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorProyectos {
    public static ArrayList<Proyecto> extraerProyectos(Elements elements) {
        ArrayList<Proyecto> proyectos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Proyecto proyecto = new Proyecto();
            System.out.println("ELEMENTO "+Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
            proyecto.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            proyecto.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(2));
            
            String detalleProyecto=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            System.out.println("DETALLE PROYECTO "+detalleProyecto);
            if(!detalleProyecto.equals(" ")){
            String [] detalleProyecto1=detalleProyecto.split(" - ");
            String [] ano=detalleProyecto1[0].split("/");
            proyecto.setAnoInicio(Integer.parseInt(ano[0].substring(1)));
            }        
            proyectos.add(proyecto);
        }
        return proyectos;
    }
}
