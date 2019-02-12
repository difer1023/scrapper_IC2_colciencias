/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.NuevaVariedadAnimal;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con el producto 
 * Nueva Variedad Animal
 * Extrae información de la parte pública del Gruplac
 * @author L
 */
public class ExtractorNuevasVariedadesAnimales {
    /**
    * Método encargado de extraer información sobre el producto 
    * Nueva Variedad Animal
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<NuevaVariedadAnimal> extraerNuevasVariedadesAnimales(Elements elements) {
        ArrayList<NuevaVariedadAnimal> variedadesAnimales = new ArrayList();
        for(int i=1;i<elements.size();i++){
            NuevaVariedadAnimal variedadAnimal = new NuevaVariedadAnimal();
            variedadAnimal.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            variedadAnimal.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleVariedadAnimal=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleVariedadAnimal.split(",")[1].trim();
            variedadAnimal.setAno(Integer.parseInt(ano));
            
            variedadAnimal.setActoAdministrativoIca(detalleVariedadAnimal.split(",")[2].split(":")[1].trim());
            variedadAnimal.setInstitucionFinanciadora(detalleVariedadAnimal.split(",")[3].split(":")[1].trim());
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().split(":")[1].split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            variedadAnimal.setAutores(autores);

            variedadesAnimales.add(variedadAnimal);
        }
        return variedadesAnimales;
    }
    
}
