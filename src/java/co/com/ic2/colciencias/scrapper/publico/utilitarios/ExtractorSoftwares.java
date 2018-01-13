/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorSoftwares {
    public static ArrayList<Software> extraerSoftwares(Elements elements) {
        ArrayList<Software> softwares = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Software software = new Software();
            software.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            software.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleSoftware=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            software.setPais(detalleSoftware.split(",")[0].substring(1));
            String ano=detalleSoftware.split(",")[1].substring(1,5);
            software.setAno(Integer.parseInt(ano));
            software.setDisponibilidad(detalleSoftware.split(",")[2].substring(17));
            software.setSitioWeb(detalleSoftware.split(",")[3].substring(12));
           
            //String detalleSoftwareNombres=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            //software.setNombreComercial(detalleSoftwareNombres.split(":")[1].substring(12).split(",")[0]);
            //software.setNombreProyecto(detalleSoftwareNombres.split(":")[3].substring(12).split(",")[2]);
            
            String detalleSoftwareI=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            software.setInstitucion(detalleSoftwareI.split(":")[1].substring(1));
           
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            software.setAutores(autores);

            softwares.add(software);
        }
        return softwares;
    }
}
