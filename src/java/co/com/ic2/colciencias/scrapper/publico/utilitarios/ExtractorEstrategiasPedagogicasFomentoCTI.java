/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EstrategiaPedagogicaFomentoCTI;
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
 * Clase encargada de extraer la información relacionada con el producto Estrategia pedágogica de fomento a la CTI
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorEstrategiasPedagogicasFomentoCTI {
    
    /**
    * Método encargado de extraer información sobre el producto Estrategia pedagógica de fomento a la CTI
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<EstrategiaPedagogicaFomentoCTI> extraerEstrategiasPedagogicasFomentoCTI(Elements elements) {
        ArrayList<EstrategiaPedagogicaFomentoCTI> estrategiasPedagogicas = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EstrategiaPedagogicaFomentoCTI estrategiaPedagogica= new EstrategiaPedagogicaFomentoCTI();
            estrategiaPedagogica.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            String detalleEstrategiaPedagogica=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEstrategiaPedagogica.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            estrategiaPedagogica.setAnoInicio(Integer.parseInt(detalle2[1]));
            
            String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            estrategiaPedagogica.setDescripcion(detalleEstrategia.substring(14));

            estrategiasPedagogicas.add(estrategiaPedagogica);
        }
        return estrategiasPedagogicas;
    }
    
    
    /**
    * Método encargado de extraer información sobre el producto Estrategia pedagógica de fomento a la CTI
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<EstrategiaPedagogicaFomentoCTI> extraerEstrategiasPedagogicasFomentoCTIPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<EstrategiaPedagogicaFomentoCTI> estrategiasPedagogicas = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                EstrategiaPedagogicaFomentoCTI estrategiaPedagogica = new EstrategiaPedagogicaFomentoCTI();
                System.out.println("FILA"+ elements.get(i).text());
                
                estrategiaPedagogica.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                estrategiaPedagogica.setAnoInicio(Integer.parseInt(ano));
                estrategiaPedagogica.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
                 Logger.getLogger(ExtractorEstrategiasPedagogicasFomentoCTI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try{
                String [] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get().split("-");
                estrategiaPedagogica.setAnoInicio(Integer.parseInt(fechaPublicacion[0]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe fecha publicacion");}
                //Campo en blanco
                try{
                String [] institucionesTabla= Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get().split(" \\| ");
                System.out.println(institucionesTabla.length);
                ArrayList<Institucion> instituciones= new ArrayList<>();
                
                for(String nombreInstitucion:institucionesTabla){
                    Institucion institucion= new Institucion();
                    institucion.setNombre(nombreInstitucion);
                    instituciones.add(institucion);
                }
                estrategiaPedagogica.setInstituciones(instituciones);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error campo en blanco");}
                
                //Campo en blanco
                String numeroInvestigadoresPrincipales=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get();
                try{
                estrategiaPedagogica.setNumeroInvestigadoresPrincipales(Integer.parseInt(numeroInvestigadoresPrincipales.split("\\) ")[0].split("\\(")[1]));
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay numero de investigadores principales");}
                
                estrategiasPedagogicas.add(estrategiaPedagogica);
            }
        }
        return estrategiasPedagogicas;
    }
}
