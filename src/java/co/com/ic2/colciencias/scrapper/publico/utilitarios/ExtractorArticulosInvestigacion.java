/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ArticuloInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroArticuloPublicado;
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
 * Clase encargada de extraer la información relacionada con el producto Artículo
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorArticulosInvestigacion {

    static String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
                + "          \" 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36\"";
        
    /**
    * Método encargado de extraer información sobre el producto Articulos Publicados
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<ArticuloInvestigacion> extraerArticulosPublicados(Elements elements) {
        ArrayList<ArticuloInvestigacion> articulosPublicados = new ArrayList();
        for (int i = 1; i < elements.size(); i++) {

            ArticuloInvestigacion articuloInvestigacion = new ArticuloInvestigacion();
            articuloInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            
            articuloInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(1));

            String detalleRevista = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            articuloInvestigacion.setPais(detalleRevista.split(",")[0].substring(1));
            String nombreRevista = detalleRevista.split(",")[1].split("ISSN:")[0];
            
            String[] arrayISSN = detalleRevista.split(",")[1].split("ISSN:");
            String ano = detalleRevista.split(",")[2].substring(1, 5);
            if (arrayISSN.length < 2) {
                for (int j = 2; arrayISSN.length < 2; j++) {
                    arrayISSN = detalleRevista.split(",")[j].split("ISSN:");
                    if (arrayISSN.length < 2) {
                        nombreRevista += detalleRevista.split(",")[j];

                    } else {
                        nombreRevista += detalleRevista.split(",")[j].split("ISSN:")[0];
                        ano = detalleRevista.split(",")[j + 1].substring(1, 5);
                    }
                }
            }
            articuloInvestigacion.setRevista(nombreRevista.substring(1, nombreRevista.length() - 1));
            articuloInvestigacion.setIssn(arrayISSN[1].substring(1));
            articuloInvestigacion.setAno(Integer.parseInt(ano));
            
            try{
            String doi=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            articuloInvestigacion.setDoi(doi);
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay DOI");}
            
            articuloInvestigacion.setVolumen(detalleRevista.split("vol:")[1].split(" fasc: ")[0]);
            articuloInvestigacion.setFasciculo(detalleRevista.split(" fasc:")[1].split(" págs: ")[0]);
            try{
            articuloInvestigacion.setPaginas(detalleRevista.split(" págs: ")[1].split(",")[0]); 
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay numero paginas");}
            
            ArrayList<Investigador> autores = new ArrayList<>();
            String[] datosAutores = Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Investigador autor = new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            articuloInvestigacion.setAutores(autores);
            articulosPublicados.add(articuloInvestigacion);
        }
        return articulosPublicados;
    }

    /**
    * Método encargado de extraer información sobre el producto Otros artículos publicados
    * Presente en la parte pública del Gruplac
    * En algunos casos pueden ser tipificados como Artículos publicados
    */
    public static ArrayList<OtroArticuloPublicado> extraerOtrosArticulosPublicados(Elements elements) {
        ArrayList<OtroArticuloPublicado> otrosArticulosPublicados = new ArrayList();
        for (int i = 2; i < elements.size(); i++) {

            OtroArticuloPublicado OtroArticuloPublicado = new OtroArticuloPublicado();
            OtroArticuloPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            OtroArticuloPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(1));

            String detalleRevista = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            OtroArticuloPublicado.setPais(detalleRevista.split(",")[0].substring(1));
            String nombreRevista = detalleRevista.split(",")[1].split("ISSN:")[0];
            String[] arrayISSN = detalleRevista.split(",")[1].split("ISSN:");
            String ano = detalleRevista.split(",")[2].substring(1, 5);
            
            if (arrayISSN.length < 2) {
                for (int j = 2; arrayISSN.length < 2; j++) {
                    arrayISSN = detalleRevista.split(",")[j].split("ISSN:");
                    if (arrayISSN.length < 2) {
                        nombreRevista += detalleRevista.split(",")[j];

                    } else {
                        nombreRevista += detalleRevista.split(",")[j].split("ISSN:")[0];
                        ano = detalleRevista.split(",")[j + 1].substring(1, 5);
                    }
                }
            }

            if (!nombreRevista.equals(" ")) {
                OtroArticuloPublicado.setRevista(nombreRevista.substring(1, nombreRevista.length() - 1));
            }
            OtroArticuloPublicado.setIssn(arrayISSN[1].substring(1));
            OtroArticuloPublicado.setAno(Integer.parseInt(ano));
            
            ArrayList<Investigador> autores = new ArrayList<>();
            String[] datosAutores = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Investigador autor = new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            OtroArticuloPublicado.setAutores(autores);
            
            
            OtroArticuloPublicado.setVolumen(detalleRevista.split("vol:")[1].split(" fasc: ")[0]);
            OtroArticuloPublicado.setFasciculo(detalleRevista.split(" fasc:")[1].split(" págs: ")[0]);
            OtroArticuloPublicado.setPaginas(detalleRevista.split(" págs: ")[1].split(",")[0]); 
            
            otrosArticulosPublicados.add(OtroArticuloPublicado);
        }
        return otrosArticulosPublicados;
    }

    /**
    * Método encargado de extraer información sobre el producto Artículos publicados
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<ArticuloInvestigacion> extraerArticulosPublicadosPrivado(ArrayList<Elements> arrayElements,HashMap<String,String> cookies) {
        ArrayList<ArticuloInvestigacion> articulosPublicados = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                ArticuloInvestigacion articuloInvestigacion = new ArticuloInvestigacion();
                System.out.println("FILA"+ elements.get(i).text());

                articuloInvestigacion.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                articuloInvestigacion.setAno(Integer.parseInt(ano));
                articuloInvestigacion.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
                String enlaceDetalle=(ConstantesScrapper.urlGruplac+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
                System.out.println("enlace"+enlaceDetalle); 
                
                Document doc = null;
                try {
                    Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                        .cookies(cookies)
                        .userAgent(USER_AGENT)
                        .execute();
                    doc=res2.parse();
                } catch (IOException ex) {
                Logger.getLogger(ExtractorArticulosInvestigacion.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                try {
                String numeroAutores=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                articuloInvestigacion.setNumeroAutores(Integer.parseInt(numeroAutores.split(" \\) ")[0].split("\\(")[1]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe numero de autores");}
                
                try{
                articuloInvestigacion.setRevista(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/"
                        + "table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existe revista");}
                
                
                articuloInvestigacion.setMes(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get());
                articuloInvestigacion.setVolumen(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
             
                try{
                articuloInvestigacion.setPagInicial((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[8]/td[3]/text()").evaluate(doc).get()).split("inicial: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existe pagina inicial");}
                try{
                articuloInvestigacion.setPagFinal((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[9]/td[3]/text()").evaluate(doc).get()).split("final: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existe pagina final");}
                
                articuloInvestigacion.setIssn(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[10]/td[3]/text()").evaluate(doc).get());
                
                try{
                articuloInvestigacion.setUrl((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[11]/td[3]/text()").evaluate(doc).get()).split("URL: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existe url");}
                try{
                articuloInvestigacion.setDoi((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[12]/td[3]/text()").evaluate(doc).get()).split("DOI: ")[1]);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existe doi");}
               
                articulosPublicados.add(articuloInvestigacion);
            }
        }
        return articulosPublicados;
    }
}
