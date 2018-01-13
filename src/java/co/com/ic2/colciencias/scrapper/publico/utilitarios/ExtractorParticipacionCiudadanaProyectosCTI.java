/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.ParticipacionCiudadanaProyectoCTI;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorParticipacionCiudadanaProyectosCTI {
    public static ArrayList<ParticipacionCiudadanaProyectoCTI> extraerParticipacionCiudadanaProyectosCTI(Elements elements) {
        ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            ParticipacionCiudadanaProyectoCTI participacionCiudadana= new ParticipacionCiudadanaProyectoCTI();
            participacionCiudadana.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            String detalleEstrategiaPedagogica=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEstrategiaPedagogica.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            participacionCiudadana.setAnoInicio(Integer.parseInt(detalle2[1]));
            //System.out.println("FechaFin"+detalle2[1]);
            
            //String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            //estrategiaPedagogica.setDescripcion(detalleEstrategia.substring(14));

            participacionCiudadanaProyectos.add(participacionCiudadana);
        }
        return participacionCiudadanaProyectos;
    }
}
