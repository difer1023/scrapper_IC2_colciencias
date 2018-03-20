/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoVirtual;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información ralacionada con el producto Generación de contenido virtual
 * Extrae información de la parte pública del Gruplac
 * @author Difer
 */
public class ExtractorGeneracionContenidosVirtuales {
    
    /**
    * Método encargado de extraer información sobre el producto Generación de contenido virtual
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<GeneracionContenidoVirtual> extraerGeneracionContenidosVirtuales(Elements elements) {
        ArrayList<GeneracionContenidoVirtual> contenidosVirtuales = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoVirtual contenidoVirtual= new GeneracionContenidoVirtual();
            contenidoVirtual.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            //System.out.println(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get());
            contenidoVirtual.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoVirtual=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleContenidoVirtual.split(",")[0].substring(1,5);
            contenidoVirtual.setAno(Integer.parseInt(ano));
            
            String [] institucionesContenido=detalleContenidoVirtual.split("Entidades vinculadas: ")[1].split(" Sitio web:")[0].split(",");
            ArrayList<Institucion> instituciones=new ArrayList<>();
            for(int j=0;j<institucionesContenido.length;j++){
                Institucion institucion=new Institucion();
                institucion.setNombre(institucionesContenido[j]);
                instituciones.add(institucion);
            }
            contenidoVirtual.setInstituciones(instituciones);
            try{
            contenidoVirtual.setUrl(detalleContenidoVirtual.split("Sitio web: ")[1]);
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("Error con url");};
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoVirtual.setAutores(autores);

            contenidosVirtuales.add(contenidoVirtual);
        }
        return contenidosVirtuales;
    }
}
