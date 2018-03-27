/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DocumentoTrabajo;
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
 * Clase encargada de extraer la información relacionada con el producto Documento de trabajo
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorDocumentosTrabajo {
    
    /**
    * Método encargado de extraer información sobre el producto Documento de trabajo
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<DocumentoTrabajo> extraerDocumentosTrabajo(Elements elements) {
        ArrayList<DocumentoTrabajo> documentosTrabajo = new ArrayList();
        for(int i=1;i<elements.size();i++){
            DocumentoTrabajo documentoTrabajo = new DocumentoTrabajo();
            documentoTrabajo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            documentoTrabajo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDocTrabajo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleDocTrabajo.split(",")[0].substring(1,5);
            documentoTrabajo.setAno(Integer.parseInt(ano));
            documentoTrabajo.setUrl(detalleDocTrabajo.split("URL: ")[1].split("\\, DOI:")[0]);
            documentoTrabajo.setDoi(detalleDocTrabajo.split("DOI:")[1]);
         
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            documentoTrabajo.setAutores(autores);

            documentosTrabajo.add(documentoTrabajo);
        }
        return documentosTrabajo;
    }
    
    
    /**
    * Método encargado de extraer información sobre el producto Documento de trabajo
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<DocumentoTrabajo> extraerDocumentosTrabajoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<DocumentoTrabajo> documentoTrabajo = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                DocumentoTrabajo documentosTrabajo = new DocumentoTrabajo();
                System.out.println("FILA"+ elements.get(i).text());
                
                documentosTrabajo.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                documentosTrabajo.setAno(Integer.parseInt(ano));
                documentosTrabajo.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
                    Logger.getLogger(ExtractorDocumentosTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                String numeroAutores=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get();
                documentosTrabajo.setNumeroAutores(Integer.parseInt(numeroAutores.split("\\) ")[0].split("\\(")[1]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error existe numero autores");}
                try {
                String [] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get().split("-");
                documentosTrabajo.setAno(Integer.parseInt(fechaPublicacion[0]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error existe fecha publicacion");}  
                
                try {
                String [] URL_DOI = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split("DOI: ");
                documentosTrabajo.setUrl(URL_DOI[0].split("URL: ")[1]);
                documentosTrabajo.setDoi(URL_DOI[1]);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error existe fecha publicacion");}  
        
                
                documentoTrabajo.add(documentosTrabajo);
            }
        }
        return documentoTrabajo;
    }
}
