/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.EstrategiaComunicacionConocimiento;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto Estrategias de comunicación del conocimiento
 * Extrae información de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorEstrategiasComunicacionConocimiento {
    
    /**
    * Método encargado de extraer información sobre el producto Estrategias de comunicación de conocimiento
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<EstrategiaComunicacionConocimiento> extraerEstrategiasComunicacionConocimiento(Elements elements) {
        ArrayList<EstrategiaComunicacionConocimiento> EstrategiasComunicacionConocimiento = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EstrategiaComunicacionConocimiento comunicacionConocimiento= new EstrategiaComunicacionConocimiento();
            comunicacionConocimiento.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            String detalleComunicacionConocimiento=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
            
            String [] detalle1=detalleComunicacionConocimiento.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            comunicacionConocimiento.setAnoInicio(Integer.parseInt(detalle2[1]));
            
            String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            comunicacionConocimiento.setDescripcion(detalleEstrategia.substring(14));
     
            EstrategiasComunicacionConocimiento.add(comunicacionConocimiento);
        }
        return EstrategiasComunicacionConocimiento;
    }
}
