/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.EspacioParticipacionCiudadanaCTI;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto Espacios de participación ciudadana
 * Extrae información de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorEspaciosParticipacionCiudadana {
    
    /**
    * Método encargado de extraer información sobre el producto Espacios de participación cudadana
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<EspacioParticipacionCiudadanaCTI> extraerEspaciosParticipacionCiudadana(Elements elements) {
        ArrayList<EspacioParticipacionCiudadanaCTI> espaciosParticipacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EspacioParticipacionCiudadanaCTI participacionCiudadana= new EspacioParticipacionCiudadanaCTI();
            participacionCiudadana.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
    
            String detalleEspacioParticipacion=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEspacioParticipacion.split("- hasta");
            String [] detalle2= detalle1[0].split(" ");
            String [] detalle3=detalle2[2].split("-");
            participacionCiudadana.setAnoInicio(Integer.parseInt(detalle3[0]));
            
            String detalleEspacio=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            try{
            participacionCiudadana.setNumeroParticipantes(Integer.parseInt(detalleEspacio.split("Número de participantes: ")[1].split("\\, Página web")[0]));
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("error numero participantes");};
            espaciosParticipacion.add(participacionCiudadana);
        }
        return espaciosParticipacion;
    }
}
