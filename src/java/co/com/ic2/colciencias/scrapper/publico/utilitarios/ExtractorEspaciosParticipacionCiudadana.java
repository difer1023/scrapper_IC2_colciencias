/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.EspacioParticipacionCiudadana;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorEspaciosParticipacionCiudadana {
    public static ArrayList<EspacioParticipacionCiudadana> extraerEspaciosParticipacionCiudadana(Elements elements) {
        ArrayList<EspacioParticipacionCiudadana> espaciosParticipacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EspacioParticipacionCiudadana participacionCiudadana= new EspacioParticipacionCiudadana();
            participacionCiudadana.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
    
            String detalleEspacioParticipacion=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEspacioParticipacion.split("- hasta");
            String [] detalle2= detalle1[0].split(" ");
            String [] detalle3=detalle2[2].split("-");
            participacionCiudadana.setAnoInicio(Integer.parseInt(detalle3[0]));
            
            String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();

            espaciosParticipacion.add(participacionCiudadana);
        }
        return espaciosParticipacion;
    }
}
