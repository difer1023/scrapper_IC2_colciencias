/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Prototipo;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorPrototipos {
    public static ArrayList<Prototipo> extraerPrototipos(Elements elements) {
        ArrayList<Prototipo> prototipos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Prototipo prototipo = new Prototipo();
            prototipo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            prototipo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePrototipo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            prototipo.setPais(detallePrototipo.split(",")[0].substring(1));
            String ano=detallePrototipo.split(",")[1].substring(1,5);
            prototipo.setAno(Integer.parseInt(ano));
            prototipo.setDisponibilidad(detallePrototipo.split(",")[2].substring(17));
            prototipo.setInstitucion(detallePrototipo.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            prototipo.setAutores(autores);

            prototipos.add(prototipo);
        }
        return prototipos;
    }
}
