/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EmpresaBaseTecnologica;
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
public class ExtractorEmpresasBaseTeconologica {
    public static ArrayList<EmpresaBaseTecnologica> extraerEmpresasBaseTecnologica(Elements elements) {
        ArrayList<EmpresaBaseTecnologica> empresasBT = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EmpresaBaseTecnologica empresaBT = new EmpresaBaseTecnologica();
            empresaBT.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            empresaBT.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEmpresaBT=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            empresaBT.setFecha(detalleEmpresaBT.split(",")[0].substring(1));
            //String ano=detalleempresaBT.split(",")[1].substring(1,5);
            //empresaBT.setAno(Integer.parseInt(ano));
            empresaBT.setNit(detalleEmpresaBT.split(",")[1].substring(6));
            empresaBT.setFechaRegistro(detalleEmpresaBT.split(",")[2].substring(32));
            
            String detalleEmpresaBTEstado=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            empresaBT.setEstado(detalleEmpresaBTEstado.substring(1));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            empresaBT.setAutores(autores);

            empresasBT.add(empresaBT);
        }
        return empresasBT;
    }
    
    public static ArrayList<EmpresaBaseTecnologica> extraerEmpresaBaseTecnologicaPrivado(ArrayList<Elements> arrayElements, HashMap<String, String> cookies) {
        ArrayList<EmpresaBaseTecnologica> empresasBT = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {
                EmpresaBaseTecnologica empresaBT = new EmpresaBaseTecnologica();
                System.out.println("FILA"+ elements.get(i).text());
                
                empresaBT.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
                
                String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
                empresaBT.setAno(Integer.parseInt(ano));
                empresaBT.setClasificacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get());
              
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
            
                
                empresaBT.setCertificadoCamaraComercio(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[3]/td[3]/text()").evaluate(doc).get());
                empresaBT.setNit(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[4]/td[3]/text()").evaluate(doc).get());
                empresaBT.setCertificacionInstitucional(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/text()/text()").evaluate(doc).get());
                
                empresasBT.add(empresaBT);
            }
        }
        return empresasBT;
    }
}
