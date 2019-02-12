/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ReglamentoTecnico;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto 
 * Reglamentos Tecnicos
 * Extrae información de la parte pública del Gruplac
 * @author L
 */
public class ExtractorReglamentosTecnicos {
    /**
    * Método encargado de extraer información sobre el producto 
    * reglamentos Técnicos
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<ReglamentoTecnico> extraerReglamentosTecnicos(Elements elements) {
        ArrayList<ReglamentoTecnico> reglamentosTecnicos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            ReglamentoTecnico reglamentoTecnico = new ReglamentoTecnico();
            reglamentoTecnico.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            reglamentoTecnico.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleReglamentoTecnico=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            reglamentoTecnico.setPais(detalleReglamentoTecnico.split(",")[0]);
            
            String ano=detalleReglamentoTecnico.split(",")[1].trim();
            reglamentoTecnico.setAno(Integer.parseInt(ano));
            
            String detalleReglamentoTecnico1 = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            reglamentoTecnico.setInstitucionFinanciadora(detalleReglamentoTecnico1.split(":")[1].trim());
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().split(":")[1].split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            reglamentoTecnico.setAutores(autores);

            reglamentosTecnicos.add(reglamentoTecnico);
        }
        return reglamentosTecnicos;
    }
}
