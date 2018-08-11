/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesModelo;
import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.Investigador;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoImpreso;
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
 * Clase encargada de extraer la información relacionada con el producto Generación de contenido impreso
 * Extrae información de la parte pública y la parte privada del gruplac
 * @author Difer
 */
public class ExtractorGeneracionContenidosImpresos {
    
    /**
    * Método encargado de extraer información sobre el producto Generación de contenido impreso
    * Presente en la parte pública del Gruplac
    */
    public static ArrayList<GeneracionContenidoImpreso> extraerGeneracionContenidosImpresos(Elements elements) {
        ArrayList<GeneracionContenidoImpreso> contenidosImpresos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoImpreso contenidoImpreso = new GeneracionContenidoImpreso();
            contenidoImpreso.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            contenidoImpreso.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoImpreso=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            try{
                String ano=detalleContenidoImpreso.split(",")[0].substring(1,5);
                contenidoImpreso.setAno(Integer.parseInt(ano));
            }catch(StringIndexOutOfBoundsException e){
                System.out.println("Error en extraerGeneracionContenidosImpresos ano");
            }
            contenidoImpreso.setMedioCirculacion(detalleContenidoImpreso.split(",")[2].substring(23));
            
            String detalleContenidoImpreso2=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            contenidoImpreso.setLugarPublicacion(detalleContenidoImpreso2.split(",")[0].substring(22));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Investigador> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Investigador autor=new Investigador();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoImpreso.setAutores(autores);

            contenidosImpresos.add(contenidoImpreso);
        }
        return contenidosImpresos;
    }
    
    /**
    * Método encargado de extraer información sobre el producto Generación de contenido impreso
    * Presente en la parte privada del Gruplac
    */
    public static ArrayList<GeneracionContenidoImpreso> extraerGeneracionContenidosImpresosPrivado(ArrayList<Elements> arrayElements,HashMap<String,String> cookies,int anoFinVentanaObservacion) {
        ArrayList<GeneracionContenidoImpreso> contenidosImpresos = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {

                GeneracionContenidoImpreso contenidoImpreso = new GeneracionContenidoImpreso();
//                System.out.println("FILA"+ elements.get(i).text());
                int ano = Integer.parseInt(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
                contenidoImpreso.setAno(ano);
                
                int anoInicioVentanaObservacion=anoFinVentanaObservacion-(ConstantesModelo.VO_GC-1);
                if(ano<=anoFinVentanaObservacion && ano>=anoInicioVentanaObservacion){
                
                    contenidoImpreso.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());

                    contenidoImpreso.setCategoria(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
                    String categoria=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get();
                    if(!categoria.equals("No cumple existencia") && !categoria.equals("Cumple con existencia")){
                        contenidoImpreso.setCategoria(categoria);
                        contenidoImpreso.setClasificado(true);
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
                        Logger.getLogger(ExtractorGeneracionContenidosImpresos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try{  
                    String numeroAutores=Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get();  
                    contenidoImpreso.setNumeroAutores(Integer.parseInt(numeroAutores.split(" \\) ")[0].split("\\(")[1]));  
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Error extraerGeneracionContenidosImpresosPrivado no existe numero autores");}
                    contenidoImpreso.setRevista(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/"
                            + "table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());
                    contenidoImpreso.setMes(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[6]/td[3]/text()").evaluate(doc).get());
                    contenidoImpreso.setVolumen(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[7]/td[3]/text()").evaluate(doc).get());
                    try{
                    contenidoImpreso.setPagInicial((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[8]/td[3]/text()").evaluate(doc).get()).split("inicial: ")[1]);
                    } catch(ArrayIndexOutOfBoundsException e){System.out.println("Error extraerGeneracionContenidosImpresosPrivado no existe página inicial");}
                    try{
                    contenidoImpreso.setPagFinal((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[9]/td[3]/text()").evaluate(doc).get()).split("final: ")[1]);
                    } catch(ArrayIndexOutOfBoundsException | NullPointerException e){System.out.println("Error extraerGeneracionContenidosImpresosPrivado no existe página final");}
                    contenidoImpreso.setIssn(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[10]/td[3]/text()").evaluate(doc).get());
                    try{
                    contenidoImpreso.setUrl((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[11]/td[3]/text()").evaluate(doc).get()).split("URL: ")[1]);
                    } catch(ArrayIndexOutOfBoundsException | NullPointerException e){System.out.println("Error extraerGeneracionContenidosImpresosPrivado no existe url");}
                    try{
                    contenidoImpreso.setDoi((Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[12]/td[3]/text()").evaluate(doc).get()).split("DOI: ")[1]);
                    } catch(ArrayIndexOutOfBoundsException | NullPointerException e){System.out.println("Error extraerGeneracionContenidosImpresosPrivado no existe doi");}

                    contenidosImpresos.add(contenidoImpreso);
                }
            }
        }
        return contenidosImpresos;
    }
}
