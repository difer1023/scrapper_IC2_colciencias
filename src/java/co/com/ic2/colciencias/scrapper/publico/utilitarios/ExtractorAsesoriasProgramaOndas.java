/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.AsesoriaProgramaOndas;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorAsesoriasProgramaOndas {
    public static ArrayList<AsesoriaProgramaOndas> extraerAsesoriasProgramaOndas(Elements elements) {
        ArrayList<AsesoriaProgramaOndas> asesoriasProgramaOndas= new ArrayList();
        for(int i=1;i<elements.size();i++){
            AsesoriaProgramaOndas asesoriaProgramaOndas= new AsesoriaProgramaOndas();
            asesoriaProgramaOndas.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            
            String detalleProgramaOndas=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleProgramaOndas.split("en ");
            asesoriaProgramaOndas.setLugar(detalle1[1]);
            
            String detalleProgramaOndas1=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleOndas=detalleProgramaOndas1.split("- hasta");
            String [] detalleOndas2= detalleOndas[0].split(" ");
            String [] detalleOndas3=detalleOndas2[2].split("-");
            asesoriaProgramaOndas.setAnoInicio(Integer.parseInt(detalleOndas3[0]));
            
            String detalleProgramaOndasI=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            //String [] detalle2= detalle1[1].split(" ");
            asesoriaProgramaOndas.setInstitucion(detalleProgramaOndasI.split(": ")[1]);
            //System.out.println("FechaFin"+detalle1[1]);
            
            //String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            //asesoriaProgramaOndas.setDescripcion(detalleEstrategia.substring(14));

            asesoriasProgramaOndas.add(asesoriaProgramaOndas);
        }
        return asesoriasProgramaOndas;
    }
}
