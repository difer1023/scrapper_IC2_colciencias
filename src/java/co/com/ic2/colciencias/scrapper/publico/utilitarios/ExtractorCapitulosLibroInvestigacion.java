/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorCapitulosLibroInvestigacion {
    public static ArrayList<CapituloLibroPublicado> extraerCapitulosLibroPublicados(Elements elements) {
        ArrayList<CapituloLibroPublicado> capitulosLibroPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            CapituloLibroPublicado capituloLibroPublicado = new CapituloLibroPublicado();
            capituloLibroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            capituloLibroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleCapLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            capituloLibroPublicado.setPais(detalleCapLibro.split(",")[0].substring(1));
            
            String ano=detalleCapLibro.split(",")[1].substring(1,5);
            capituloLibroPublicado.setAno(Integer.parseInt(ano));
            capituloLibroPublicado.setLibro(detalleCapLibro.split(",")[2].substring(1));
            //System.out.println("detalle"+detalleCapLibro);
            
            String [] detalleCapLibro2=detalleCapLibro.split("ISBN:");
            //System.out.println("Isbn "+detalleCapLibro2[0]);
            //System.out.println("Isbn 2"+detalleCapLibro2[1]);
            //String [] detallegeneral=detalleCapLibro2[1].split(",");
            //System.out.println("algo"+detalleCapLibro2[0]);
            capituloLibroPublicado.setIsbn(detalleCapLibro2[1].split(",")[0].substring(1));
            capituloLibroPublicado.setEditorial(detalleCapLibro2[1].split(",")[3].substring(1));
            
            //capituloLibroPublicado.setIsbn(isbn.substring(1,isbn.length()-1));
            //System.out.println("detalle"+detalleCapLibro);
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            capituloLibroPublicado.setAutores(autores);

            capitulosLibroPublicados.add(capituloLibroPublicado);
        }
        return capitulosLibroPublicados;
    }
}
