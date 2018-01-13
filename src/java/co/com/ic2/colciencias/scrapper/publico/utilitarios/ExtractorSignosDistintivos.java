/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.SignoDistintivo;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorSignosDistintivos {
    public static ArrayList<SignoDistintivo> extraerSignosDistintivos(Elements elements) {
        ArrayList<SignoDistintivo> signosDistintivos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            SignoDistintivo signoDistintivo = new SignoDistintivo();
            signoDistintivo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            signoDistintivo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePrototipo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            signoDistintivo.setPais(detallePrototipo.split(",")[0].substring(1));
            String ano=detallePrototipo.split(",")[1].substring(1,5);
            signoDistintivo.setAno(Integer.parseInt(ano));
            signoDistintivo.setNumRegistro(detallePrototipo.split(",")[2].substring(22));
            signoDistintivo.setNombreTitular(detallePrototipo.split(",")[3].substring(21));
            
            signosDistintivos.add(signoDistintivo);
        }
        return signosDistintivos;
    }
}
