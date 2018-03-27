/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ParticipacionCiudadanaProyectoCTI;
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
 * Clase encargada de extraer información relacionada con el producto Participacion ciudadana en proyectos de CTI
 * Extrae información de la parte privada y la parte pública del gruplac
 * @author Difer
 */
public class ExtractorParticipacionCiudadanaProyectosCTI {
    
    /**
    * Método encargado de extraer información sobre el producto Participación ciudadana en proyectos de CTI
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<ParticipacionCiudadanaProyectoCTI> extraerParticipacionCiudadanaProyectosCTI(Elements elements) {
        ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            ParticipacionCiudadanaProyectoCTI participacionCiudadana= new ParticipacionCiudadanaProyectoCTI();
            participacionCiudadana.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            String detalleEstrategiaPedagogica=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEstrategiaPedagogica.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            participacionCiudadana.setAnoInicio(Integer.parseInt(detalle2[1]));
            
            participacionCiudadanaProyectos.add(participacionCiudadana);
        }
        return participacionCiudadanaProyectos;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Participación ciudadana en proyectos de CTI
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<ParticipacionCiudadanaProyectoCTI> extraerPaticipacionCiudadanaProyectosCTIPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectos = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                ParticipacionCiudadanaProyectoCTI participacionCiudadanaProyecto = new ParticipacionCiudadanaProyectoCTI();
                System.out.println("FILA"+ elements.get(i).text());
                
                participacionCiudadanaProyecto.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
               String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                participacionCiudadanaProyecto.setAnoInicio(Integer.parseInt(ano));
                participacionCiudadanaProyecto.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
                    Logger.getLogger(ExtractorParticipacionCiudadanaProyectosCTI.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                String [] fechaPublicacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get().split("-");
                participacionCiudadanaProyecto.setAnoInicio(Integer.parseInt(fechaPublicacion[0]));
                
                String numeroInvestigadoresPrincipales=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                try{
                participacionCiudadanaProyecto.setNumeroInvestigadoresPrincipales(Integer.parseInt(numeroInvestigadoresPrincipales.split("\\) ")[0].split("\\(")[1]));
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no hay numero de investigadores principales");}

                //Campo en blanco
                try{
                participacionCiudadanaProyecto.setComunidadesParticipantes(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get());
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error campo en blanco");}
                
                //Campo en blanco
                try{
                String [] institucionesTabla= Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split(" \\| ");
                System.out.println(institucionesTabla.length);
                ArrayList<Institucion> instituciones= new ArrayList<>();
                
                for(String nombreInstitucion:institucionesTabla){
                    Institucion institucion= new Institucion();
                    institucion.setNombre(nombreInstitucion);
                    instituciones.add(institucion);
                }
                participacionCiudadanaProyecto.setInstituciones(instituciones);
                } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error campo en blanco");}
                
                participacionCiudadanaProyectos.add(participacionCiudadanaProyecto);
            }
        }
        return participacionCiudadanaProyectos;
    }
}
