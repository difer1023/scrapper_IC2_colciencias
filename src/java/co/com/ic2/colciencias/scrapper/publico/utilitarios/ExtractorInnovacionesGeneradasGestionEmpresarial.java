/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InnovacionGeneradaGestionEmpresarial;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto 
 * Innovación generada en la Gestión Empresarial
 * Extrae información de la parte pública del Gruplac
 * @author L
 */
public class ExtractorInnovacionesGeneradasGestionEmpresarial {
    /**
    * Método encargado de extraer información sobre el producto 
    * Innovación Generada en la Gestión Empresarial
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<InnovacionGeneradaGestionEmpresarial> extraerInnovacionesGeneradasGestionEmpresarial(Elements elements) {
        ArrayList<InnovacionGeneradaGestionEmpresarial> innovacionesGestion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            InnovacionGeneradaGestionEmpresarial innovacionGestion = new InnovacionGeneradaGestionEmpresarial();
            innovacionGestion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            innovacionGestion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleInnovacionGestion=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            innovacionGestion.setPais(detalleInnovacionGestion.split(",")[0].substring(1));
            String ano=detalleInnovacionGestion.split(",")[1].substring(1,5);
            innovacionGestion.setAno(Integer.parseInt(ano));
            
            innovacionGestion.setInstitucionFinanciadora(detalleInnovacionGestion.split(",")[3].split(":")[1].trim());
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            innovacionGestion.setAutores(autores);

            innovacionesGestion.add(innovacionGestion);
        }
        return innovacionesGestion;
    }
}
