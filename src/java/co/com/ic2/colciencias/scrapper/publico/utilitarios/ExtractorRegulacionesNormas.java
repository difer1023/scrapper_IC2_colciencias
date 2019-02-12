/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.RegulacionNorma;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto 
 * Regulaciones y Normas
 * Extrae información de la parte pública del Gruplac
 * @author L
 */
public class ExtractorRegulacionesNormas {
    /**
    * Método encargado de extraer información sobre el producto 
    * Regulaciones Y Normas
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<RegulacionNorma> extraerRegulacionesNormas(Elements elements) {
        ArrayList<RegulacionNorma> regulacionesNormas = new ArrayList();
        for(int i=1;i<elements.size();i++){
            RegulacionNorma regulacionNorma = new RegulacionNorma();
            regulacionNorma.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            regulacionNorma.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleRegulacionNorma=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            regulacionNorma.setPais(detalleRegulacionNorma.split(",")[0]);
            regulacionNorma.setAmbito(detalleRegulacionNorma.split(",")[2].split(":")[1].trim());
            
            String fechaP=detalleRegulacionNorma.split(",")[3].split(":")[1].split("-")[0].trim();
            regulacionNorma.setFechaPublicacion(Integer.parseInt(fechaP));
            
            String detalleRegulacionNorma1 = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            regulacionNorma.setInstitucionFinanciadora(detalleRegulacionNorma1.split(":")[1].trim());
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().split(":")[1].split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            regulacionNorma.setAutores(autores);

            regulacionesNormas.add(regulacionNorma);
        }
        return regulacionesNormas;
    }
}
