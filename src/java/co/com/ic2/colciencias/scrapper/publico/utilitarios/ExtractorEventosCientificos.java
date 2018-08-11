/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesModelo;
import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EventoCientifico;
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
 * Clase encargada de extraer la información relacionada con el producto Evento científico
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorEventosCientificos {

    /**
    * Método encargado de extraer información sobre el producto Evento científico
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<EventoCientifico> extraerEventosCientificos(Elements elements) {
        ArrayList<EventoCientifico> eventosCientificos = new ArrayList();
        for (int i = 1; i < elements.size(); i++) {
            EventoCientifico eventoCientifico = new EventoCientifico();
            eventoCientifico.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            eventoCientifico.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEvento = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String[] detalleEvento2 = detalleEvento.split(", desde");
            eventoCientifico.setLugar(detalleEvento2[0].substring(1));
            String[] fechas = detalleEvento2[1].split("- hasta");
            eventoCientifico.setFechaInicio(fechas[0].substring(1,11));
            
            try{
            eventoCientifico.setFechaFin(fechas[1].substring(1,11));    
            }catch(StringIndexOutOfBoundsException e){System.out.println("error fecha fin");}
            
            String detalleEventoP = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            eventoCientifico.setTipoParticipacion(detalleEventoP.split(",")[1].substring(25));
      
            try{
                Elements institucionesEvento=Xsoup.compile("/td[2]/ul").evaluate(elements.get(i)).getElements();
                ArrayList<Institucion> instituciones = new ArrayList<>();

                for (int j=0;j<institucionesEvento.size();j++) {
                    Institucion institucion = new Institucion();
                    institucion.setNombre(Xsoup.compile("/li/text(1)").evaluate(institucionesEvento.get(j)).get());
                    instituciones.add(institucion);
                }
                eventoCientifico.setInstituciones(instituciones);
                } catch(ArrayIndexOutOfBoundsException | NullPointerException e){System.out.println("Error no nombre de institucion");}

            eventosCientificos.add(eventoCientifico);
        }
        return eventosCientificos;
    }

    /**
    * Método encargado de extraer información sobre el producto Evento científico
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<EventoCientifico> extraerEventosCientificosPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies, int anoFinVentanaObservacion) {
        ArrayList<EventoCientifico> eventosCientíficos = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                EventoCientifico eventoCientifico = new EventoCientifico();
//                System.out.println("FILA" + elements.get(i).text());
                String fechaInicio = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                int ano=Integer.parseInt(fechaInicio.substring(0,4));
                
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_EC-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    eventoCientifico.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                    eventoCientifico.setAno(ano);
                    eventoCientifico.setFechaInicio(fechaInicio);
                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        eventoCientifico.setCategoria(categoria);
                        eventoCientifico.setClasificado(true);
                    }

                    String enlaceDetalle = (ConstantesScrapper.urlGruplac + Xsoup.compile("/td[5]/a/@href").evaluate(elements.get(i)).get()).replaceAll(" ", "%20");
    //                System.out.println("enlace" + enlaceDetalle);
                    Document doc = null;

                    try {
                        Connection.Response res2 = Jsoup.connect(enlaceDetalle).method(Connection.Method.GET)
                                .cookies(cookies)
                                .userAgent(USER_AGENT)
                                .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
                                .execute();
                        doc = res2.parse();
                    } catch (IOException ex) {
                        Logger.getLogger(ExtractorEventosCientificos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        String numeroAutores = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();
                        eventoCientifico.setNumeroAutores(Integer.parseInt(numeroAutores.split("registrados: ")[1].split(" ")[0]));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error no existe numero autores");}

                    try {
                        String tipoParticipacion = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[2]/tbody/tr[2]/td[3]/text()").evaluate(doc).get();
                        String [] tiposParticipacion=tipoParticipacion.split("\\] \\[");
                        if(tiposParticipacion.length>1){
                            for(String tipo:tiposParticipacion){
                                if(tipo.contains("Ponente")){
                                    eventoCientifico.setTipoParticipacion(tipo.replace("[","").replace("]","").trim());
                                }
                            }
                        }else{
                        eventoCientifico.setTipoParticipacion(tipoParticipacion.replace("[","").replace("]","").trim());
                        }
                        System.out.println("TIPO PARTICIPACION "+eventoCientifico.getTipoParticipacion());
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error extraendo tipo participacion evento");}

                    try{
                    String[] institucionesTabla = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()").evaluate(doc).get().split(" \\| ");
                    ArrayList<Institucion> instituciones = new ArrayList<>();

                    for (String nombreInstitucion : institucionesTabla) {
                        Institucion institucion = new Institucion();
                        institucion.setNombre(nombreInstitucion);
                        instituciones.add(institucion);
                    }
                    eventoCientifico.setInstituciones(instituciones);
                    } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error no existen instituciones");}

                    eventosCientíficos.add(eventoCientifico);
                }
            }
        }
        return eventosCientíficos;
    }
}
