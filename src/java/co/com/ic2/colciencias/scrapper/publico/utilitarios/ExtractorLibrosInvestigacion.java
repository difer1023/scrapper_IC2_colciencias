/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.LibroInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroLibroPublicado;
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
 * Clase encargada de extraer la información relacionada con el producto Libro
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorLibrosInvestigacion {

    /**
    * Método encargado de extraer información sobre el producto Libro publicado
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<LibroInvestigacion> extraerLibrosPublicados(Elements elements) {
        ArrayList<LibroInvestigacion> librosPublicados = new ArrayList();
        for (int i = 1; i < elements.size(); i++) {
            LibroInvestigacion libroPublicado = new LibroInvestigacion();
            libroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            libroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            libroPublicado.setPais(detalleLibro.split(",")[0].substring(1));
            libroPublicado.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            libroPublicado.setEditorial(detalleLibro.split(",")[3].substring(1));
            String isbn = detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
            libroPublicado.setIsbn(isbn.substring(1, isbn.length() - 1));

            System.out.println("detalle" + detalleLibro);
            String[] datosAutores = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores = new ArrayList<>();
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Investigador autor = new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            libroPublicado.setAutores(autores);

            librosPublicados.add(libroPublicado);
        }
        return librosPublicados;
    }

    /**
    * Método encargado de extraer información sobre el producto Otro libro publicado
    * Presente en la parte pública del Gruplac
    * en algunos casos pueden ser tipificados como Libros publicados
    */
    public static ArrayList<OtroLibroPublicado> extraerOtrosLibrosPublicados(Elements elements) {
        ArrayList<OtroLibroPublicado> otrosLibrosPublicados = new ArrayList();
        for (int i = 1; i < elements.size(); i++) {
            OtroLibroPublicado otroLibroInvestigacion = new OtroLibroPublicado();
            otroLibroInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            otroLibroInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            otroLibroInvestigacion.setPais(detalleLibro.split(",")[0].substring(1));
            otroLibroInvestigacion.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            otroLibroInvestigacion.setEditorial(detalleLibro.split(",")[3].substring(1));
            String isbn = detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
            otroLibroInvestigacion.setIsbn(isbn.substring(1, isbn.length() - 1));

            System.out.println("detalle" + detalleLibro);
            String[] datosAutores = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores = new ArrayList<>();
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Investigador autor = new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            otroLibroInvestigacion.setAutores(autores);

            otrosLibrosPublicados.add(otroLibroInvestigacion);
        }
        return otrosLibrosPublicados;
    }

    /**
    * Método encargado de extraer información sobre el producto Libro publicado
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<LibroInvestigacion> extraerLibrosPublicadosPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<LibroInvestigacion> librosPublicados = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                LibroInvestigacion libroPublicado = new LibroInvestigacion();
                System.out.println("FILA"+ elements.get(i).text());
                
                libroPublicado.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
               String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
              libroPublicado.setAno(Integer.parseInt(ano));
              libroPublicado.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
                Logger.getLogger(ExtractorLibrosInvestigacion.class.getName()).log(Level.SEVERE, null, ex);
            }
                try {
                String [] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get().split("-");
                libroPublicado.setMes(fechaPublicacion[1]);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe mes");}
                
                
                libroPublicado.setIsbn(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
   
                try {
                String numeroAutores=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get();
                libroPublicado.setNumeroAutores(Integer.parseInt(numeroAutores.split("\\) ")[0].split("\\(")[1]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe numero autores");}
                
                libroPublicado.setEditorial(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get());
                
                
                libroPublicado.setPais(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
                
                //Campo en blanco
                
                String guiaVerificacion=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[8]/td[3]/text()").evaluate(doc).get();
                if(!guiaVerificacion.equals("")||guiaVerificacion!=null){
                libroPublicado.setRequisitosGuiaVerificacion(true);
                }else{
                libroPublicado.setRequisitosGuiaVerificacion(false);
                }
                
                
//                libroPublicado.setCertificacionInstitucion(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());
//                libroPublicado.setBookCitationIndex(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
//                
//                
//                libroPublicado.setReferenciaRevistasD(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
//                
//                libroPublicado.setReferenciaRevistasA1(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
//                
//                String referenciasBCI=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
//                libroPublicado.setNumeroReferenciasBCI(Integer.parseInt(referenciasBCI.split("Index: ")[1].split("\\).")[0].split("\\(")[1]));
//                
//                String referenciasLibB=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[6]/td[3]/text()").evaluate(doc).get();
//                libroPublicado.setNumeroReferenciasLibB(Integer.parseInt(referenciasLibB.split("categoría B")[1].split("\\).")[0].split("\\(")[1]));
//                
                librosPublicados.add(libroPublicado);
            }
        }
        return librosPublicados;
    }
}
