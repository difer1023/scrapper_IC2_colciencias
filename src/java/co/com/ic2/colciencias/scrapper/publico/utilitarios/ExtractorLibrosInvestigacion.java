/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.LibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroLibroPublicado;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorLibrosInvestigacion {
    public static ArrayList<LibroPublicado> extraerLibrosPublicados(Elements elements) {
        ArrayList<LibroPublicado> librosPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            LibroPublicado libroPublicado = new LibroPublicado();
            libroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            libroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            libroPublicado.setPais(detalleLibro.split(",")[0].substring(1));
            libroPublicado.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            libroPublicado.setEditorial(detalleLibro.split(",")[3].substring(1));
            String isbn=detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
            libroPublicado.setIsbn(isbn.substring(1,isbn.length()-1));
            
            System.out.println("detalle"+detalleLibro);
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            libroPublicado.setAutores(autores);
//            libroPublicado.setIsbn(Xsoup.compile("/td[2]/text()[2]").evaluate(elements.get(i)).get());
            //articuloInvestigacion.setAno(Xsoup.compile("/td[2]/text()[3]").evaluate(elements.get(i)).get());
            //articuloInvestigacion.setAutores(Xsoup.compile("/td[2]/text()[5]").evaluate(elements.get(i)).get());
           
            librosPublicados.add(libroPublicado);
        }
        return librosPublicados;
    }
    
    public static ArrayList<OtroLibroPublicado> extraerOtrosLibrosPublicados(Elements elements) {
        ArrayList<OtroLibroPublicado> otrosLibrosPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            OtroLibroPublicado otroLibroPublicado = new OtroLibroPublicado();
            otroLibroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            otroLibroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            otroLibroPublicado.setPais(detalleLibro.split(",")[0].substring(1));
            otroLibroPublicado.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            otroLibroPublicado.setEditorial(detalleLibro.split(",")[3].substring(1));
            String isbn=detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
            otroLibroPublicado.setIsbn(isbn.substring(1,isbn.length()-1));
            
            System.out.println("detalle"+detalleLibro);
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            otroLibroPublicado.setAutores(autores);
   
            otrosLibrosPublicados.add(otroLibroPublicado);
        }
        return otrosLibrosPublicados;
    }
}
