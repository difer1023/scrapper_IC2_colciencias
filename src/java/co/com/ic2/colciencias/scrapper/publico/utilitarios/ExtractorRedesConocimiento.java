/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesModelo;
import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.RedConocimiento;
import static co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion.USER_AGENT;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
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
 * Clase encargada de extraer la información relacionada con el producto Red de conocimiento
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorRedesConocimiento {
    
    /**
    * Método encargado de extraer información sobre el producto Red de conocimiento
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<RedConocimiento> extraerRedesConocimiento(Elements elements) {
        ArrayList<RedConocimiento> redesConocimiento = new ArrayList();
        for(int i=1;i<elements.size();i++){
            RedConocimiento redConocimiento= new RedConocimiento();
            redConocimiento.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            redConocimiento.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleRed=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String [] detalleRed2=detalleRed.split(", desde");
            redConocimiento.setLugar(detalleRed2[0].substring(4));
            redConocimiento.setFechaInicio(detalleRed2[1].split("- hasta")[0].substring(1,11));
           
            redesConocimiento.add(redConocimiento);
        }
        return redesConocimiento;
    }
    
    
    /**
    * Método encargado de extraer información sobre el producto Red de conocimiento
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<RedConocimiento> extraerRedesConocimientoPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies,int anoFinVentanaObservacion) {
        ArrayList<RedConocimiento> redesConocimiento = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                RedConocimiento redConocimiento = new RedConocimiento();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get().substring(0,4));
                redConocimiento.setAno(ano);
                
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_RC-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){

                    redConocimiento.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());



                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        redConocimiento.setCategoria(categoria);
                        redConocimiento.setClasificado(true);
                    }
                    String enlaceDetalle=(ConstantesScrapper.urlGruplac+Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
    //                System.out.println("enlace"+enlaceDetalle); 
                    Document doc = null;
                    try {
                        Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                            .cookies(cookies)
                            .userAgent(USER_AGENT)
                            .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
                            .execute();
                        doc=res2.parse();
                    } catch (IOException ex) {
                        Logger.getLogger(ExtractorRedesConocimiento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    redConocimiento.setLugar(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(elements.get(i)).get());
                    redConocimiento.setInvestigador(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(elements.get(i)).get());
                    redConocimiento.setPagWeb(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(elements.get(i)).get());

                    try{
                    String numeroComunidades=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()/text()").evaluate(doc).get();
                    redConocimiento.setComunidadesParticipantes(Integer.parseInt(numeroComunidades.split("registradas: ")[1].split(" ")[0]));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe numero comunidades");}

                    try{
                    String [] institucionesTabla= Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split(" \\| ");
                    ArrayList<Institucion> instituciones= new ArrayList<>();

                    for(String nombreInstitucion:institucionesTabla){
                        Institucion institucion= new Institucion();
                        institucion.setNombre(nombreInstitucion);
                        instituciones.add(institucion);
                    }
                    redConocimiento.setInstituciones(instituciones);
                    } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existen instituciones");}

                    redesConocimiento.add(redConocimiento);
                }
            }
        }
        return redesConocimiento;
    }
}
