/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.RedConocimiento;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorRedesConocimiento {
    public static ArrayList<RedConocimiento> extraerRedesConocimiento(Elements elements) {
        ArrayList<RedConocimiento> redesConocimiento = new ArrayList();
        for(int i=1;i<elements.size();i++){
            RedConocimiento redConocimiento= new RedConocimiento();
            redConocimiento.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            redConocimiento.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleRed=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String [] detalleRed2=detalleRed.split(", desde");
            redConocimiento.setLugar(detalleRed2[0].substring(4));
            redConocimiento.setFechaInicio(detalleRed2[1].substring(1,20));
           
            redesConocimiento.add(redConocimiento);
        }
        return redesConocimiento;
    }
}
