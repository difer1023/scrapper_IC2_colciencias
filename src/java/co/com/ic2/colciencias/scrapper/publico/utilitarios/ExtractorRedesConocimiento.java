/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.RedConocimiento;
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
 *
 * @author Difer
 */
public class ExtractorRedesConocimiento {
    public static ArrayList<RedConocimiento> extraerRedesConocimiento(Elements elements) {
        ArrayList<RedConocimiento> redesConocimiento = new ArrayList();
        for(int i=1;i<elements.size();i++){
            RedConocimiento redConocimiento= new RedConocimiento();
            redConocimiento.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            redConocimiento.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleRed=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String [] detalleRed2=detalleRed.split(", desde");
            redConocimiento.setLugar(detalleRed2[0].substring(4));
            redConocimiento.setFechaInicio(detalleRed2[1].substring(1,20));
           
            redesConocimiento.add(redConocimiento);
        }
        return redesConocimiento;
    }
    
    public static ArrayList<RedConocimiento> extraerRedesConocimientoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<RedConocimiento> redesConocimiento = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                RedConocimiento redConocimiento = new RedConocimiento();
                System.out.println("FILA"+ elements.get(i).text());
                
                redConocimiento.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get().substring(0,4);
                redConocimiento.setAno(Integer.parseInt(ano));
                redConocimiento.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                redConocimiento.setLugar(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(elements.get(i)).get());
                redConocimiento.setInvestigador(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(elements.get(i)).get());
                redConocimiento.setPagWeb(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(elements.get(i)).get());
                
                String numeroComunidades=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()/text()").evaluate(doc).get();
                
                redConocimiento.setComunidadesParticipantes(Integer.parseInt(numeroComunidades.split("registradas: ")[1].split(" ")[0]));
                
                String [] institucionesTabla= Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split(" \\| ");
                ArrayList<Institucion> instituciones= new ArrayList<>();
                
                for(String nombreInstitucion:institucionesTabla){
                    Institucion institucion= new Institucion();
                    institucion.setNombre(nombreInstitucion);
                    instituciones.add(institucion);
                }
                redConocimiento.setInstituciones(instituciones);

                redesConocimiento.add(redConocimiento);
            }
        }
        return redesConocimiento;
    }
}
