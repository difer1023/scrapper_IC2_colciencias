/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InformeInvestigacion;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorInformesInvestigacion {
    public static ArrayList<InformeInvestigacion> extraerInformesInvestigacion(Elements elements) {
        ArrayList<InformeInvestigacion> informesInvestigacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            InformeInvestigacion informeInvestigacion= new InformeInvestigacion();
            informeInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            informeInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleIfi=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleIfi.split(",")[0].substring(1,5);
            informeInvestigacion.setAno(Integer.parseInt(ano));
            informeInvestigacion.setProyecto(detalleIfi.split(",")[1].substring(28));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            informeInvestigacion.setAutores(autores);

            informesInvestigacion.add(informeInvestigacion);
        }
        return informesInvestigacion;
    }
}
