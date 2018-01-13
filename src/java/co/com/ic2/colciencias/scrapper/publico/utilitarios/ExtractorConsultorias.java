/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Consultoria;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorConsultorias {
    public static ArrayList<Consultoria> extraerConsultorias(Elements elements) {
        ArrayList<Consultoria> consultorias = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Consultoria consultoria = new Consultoria();
            consultoria.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            consultoria.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleConsultoria=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            consultoria.setPais(detalleConsultoria.split(",")[0].substring(1));
            String ano=detalleConsultoria.split(",")[1].substring(1,5);
            consultoria.setAno(Integer.parseInt(ano));
            consultoria.setIdioma(detalleConsultoria.split(",")[2].substring(9));
            consultoria.setDisponibilidad(detalleConsultoria.split(",")[3].substring(17));
            consultoria.setNumContrato(detalleConsultoria.split(",")[4].substring(22));
            
            String detalleConsultoriaI=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            consultoria.setInstitucion(detalleConsultoriaI.split(":")[1].substring(1));
        
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            consultoria.setAutores(autores);

            consultorias.add(consultoria);
        }
        return consultorias;
    }
}
