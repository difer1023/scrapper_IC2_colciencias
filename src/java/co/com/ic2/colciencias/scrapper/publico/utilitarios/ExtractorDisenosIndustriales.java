/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DisenoIndustrial;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorDisenosIndustriales {
    public static ArrayList<DisenoIndustrial> extraerDisenosIndustriales(Elements elements) {
        ArrayList<DisenoIndustrial> disenosIndustriales = new ArrayList();
        for(int i=1;i<elements.size();i++){
            DisenoIndustrial disenoIndustrial = new DisenoIndustrial();
            disenoIndustrial.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            disenoIndustrial.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDisenoIndustrial=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            disenoIndustrial.setPais(detalleDisenoIndustrial.split(",")[0].substring(1));
            String ano=detalleDisenoIndustrial.split(",")[1].substring(1,5);
            disenoIndustrial.setAno(Integer.parseInt(ano));
            disenoIndustrial.setDisponibilidad(detalleDisenoIndustrial.split(",")[2].substring(17));
            disenoIndustrial.setInstitucion(detalleDisenoIndustrial.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            disenoIndustrial.setAutores(autores);

            disenosIndustriales.add(disenoIndustrial);
        }
        return disenosIndustriales;
    }
}
