/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import co.com.ic2.colciencias.scrapper.publico.ScraperPublico2;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion.USER_AGENT;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer la información relacionada con el producto Capítulo de libro
 * Extrae información de la parte pública y la parte privada del gruplac
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
            capituloLibroPublicado.setTituloLibro(detalleCapLibro.split(",")[2].substring(1));
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
    
    public static ArrayList<CapituloLibroPublicado> extraerCapitulosLibroPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<CapituloLibroPublicado> capitulosLibro = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                CapituloLibroPublicado capituloLibro = new CapituloLibroPublicado();
                System.out.println("FILA"+ elements.get(i).text());
                
                capituloLibro.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
               String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
              capituloLibro.setAno(Integer.parseInt(ano));
              capituloLibro.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
              String enlaceDetalle=("http://scienti.colciencias.gov.co:8080"+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                Document doc = null;
            try {
                Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                doc=res2.parse();
            } catch (IOException ex) {
                Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                capituloLibro.setTituloLibro(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());
                
                capituloLibro.setIsbn(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                
                String [] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get().split("-");
                
                try {
                capituloLibro.setAno(Integer.parseInt(fechaPublicacion[0]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe año");}
                String numeroAutores=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
                try {
                capituloLibro.setNumeroAutores(Integer.parseInt(numeroAutores.split("\\) ")[0].split("\\(")[1]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Errorno existe numero autores");}
                capituloLibro.setEditorial(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
                
               
                capituloLibro.setPais(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[8]/td[3]/text()").evaluate(doc).get());
                
                //Campo en blanco
                capituloLibro.setRequisitosGuiaVerificacion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[9]/td[3]/text()").evaluate(doc).get());
                
                
                capitulosLibro.add(capituloLibro);
            }
        }
        return capitulosLibro;
    }
}
