/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.PlantaPiloto;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorPlantasPiloto {
    public static ArrayList<PlantaPiloto> extraerPlantasPiloto(Elements elements) {
        ArrayList<PlantaPiloto> plantasPiloto = new ArrayList();
        for(int i=1;i<elements.size();i++){
            PlantaPiloto plantaPiloto = new PlantaPiloto();
            plantaPiloto.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            plantaPiloto.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePlantaPiloto=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            plantaPiloto.setPais(detallePlantaPiloto.split(",")[0].substring(1));
            String ano=detallePlantaPiloto.split(",")[1].substring(1,5);
            plantaPiloto.setAno(Integer.parseInt(ano));
            plantaPiloto.setDisponibilidad(detallePlantaPiloto.split(",")[2].substring(17));
            plantaPiloto.setNombreComercial(detallePlantaPiloto.split(",")[3].substring(19));
            
            String detallePlantaPilotoI=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            plantaPiloto.setInstitucion(detallePlantaPilotoI.split(":")[1].substring(1));
        
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            plantaPiloto.setAutores(autores);

            plantasPiloto.add(plantaPiloto);
        }
        return plantasPiloto;
    }
}
