/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InformeTecnico;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto Diseño industrial
 * Extrae información de la parte pública del Gruplac
 * @author L
 */
public class ExtractorInformesTecnicos {
    
     /**
    * Método encargado de extraer información sobre el producto Informe Técnico
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<InformeTecnico> extraerInformesTecnicos(Elements elements) {
        ArrayList<InformeTecnico> informesTecnicos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            InformeTecnico informeTecnico = new InformeTecnico();
            informeTecnico.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get().substring(3));
            informeTecnico.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get());
            String detalleInformeTecnico=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            

            String ano=detalleInformeTecnico.split(",")[0].split(":")[1].trim();
            informeTecnico.setAno(Integer.parseInt(ano));
            
            String detalleContratoInformeTecnico=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            informeTecnico.setNumeroContrato(detalleContratoInformeTecnico.split(",")[2].split(":")[1].trim());
            informeTecnico.setInstitucionServicio(detalleContratoInformeTecnico.split(",")[3].split(":")[1].trim());
            
            informesTecnicos.add(informeTecnico);
        }
        return informesTecnicos;
    }
}
    

