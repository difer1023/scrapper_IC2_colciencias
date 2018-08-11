/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.gruplac.Investigador;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 * Clase encargada de extraer información relacionada con los integrantes
 * Extrae los integrantes de un grupo de investigación
 * Extrae los integrantes relacionados con un producto presente en el grupo de investigación
 * @author Difer
 */
public class ExtractorIntegrantes {
    
    static final Logger LOG=Logger.getLogger(ExtractorIntegrantes.class.getName());
    
    public static ArrayList<Investigador> extraerIntegrantes(Elements elements,String lider,boolean integrantesDetalles) {
        ArrayList<Investigador> integrantes = new ArrayList();
       for(int i=2;i<elements.size();i++){
            Investigador integrante = new Investigador();
            SimpleDateFormat dateFormatGruplac = new SimpleDateFormat("yyyy/mm"); 
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String inicioVinculacion=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[0].split(" ")[1];
            String finVinculacion=Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[1].split(" ")[1];
            
            integrante.setNombreCompleto(Xsoup.compile("/td[1]/a/text()").evaluate(elements.get(i)).get());
            integrante.setHorasDedicacion(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
            try {
                integrante.setInicioVinculacion(dateFormat.format(dateFormatGruplac.parse(inicioVinculacion)));
            
                if(!finVinculacion.equals("Actual")){
                integrante.setFinVinculacion(dateFormat.format(dateFormatGruplac.parse(finVinculacion)));
            }
            } catch (ParseException ex) {
                Logger.getLogger(ExtractorIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
            }
            integrante.setLider(integrante.getNombreCompleto().equals(lider));
            if(integrantesDetalles){
                String url= (Xsoup.compile("/td[1]/a/@href").evaluate(elements.get(i)).get());
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY).get();
                } catch (IOException ex) {
                    Logger.getLogger(ExtractorIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
                }

                String categoriaIntegrante=Xsoup.compile("/html/body/table/tbody/tr[2]/td/blockquote/table/tbody/tr[1]/td[1]/text()").evaluate(doc).get();
                if(categoriaIntegrante==null){

                    String campo = Xsoup.compile("/html/body/div/div[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/text()").evaluate(doc).get();
                    if(campo!=null && StringUtils.stripAccents(campo).equalsIgnoreCase("Categoria")){
                    categoriaIntegrante=Xsoup.compile("/html/body/div/div[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/text()").evaluate(doc).get();
                    integrante.setCategoria(categoriaIntegrante.split(" \\(")[0]);
                    }
                }else{
                    if(StringUtils.stripAccents(categoriaIntegrante).equalsIgnoreCase("Categoria")){
                    categoriaIntegrante=Xsoup.compile("/html/body/table/tbody/tr[2]/td/blockquote/table/tbody/tr[1]/td[2]/text()").evaluate(doc).get();
                    integrante.setCategoria(categoriaIntegrante.split(" \\(")[0]);
                    }
                }
            }
            integrantes.add(integrante);
        }
        return integrantes;
    }
    
    public static ArrayList<GrupoInvestigacion> extraerGruposIntegrante(Investigador investigador) {
        ArrayList<GrupoInvestigacion> gruposInvestigacion = new ArrayList();
        
        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
                + "          \" 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36\"";
        String loginFormUrl = ConstantesScrapper.urlGruplac+"/gruplac/";
        String loginActionUrl = "http://scienti.colciencias.gov.co:8083/ciencia-war/busquedaGruposGeneral.do?buscar=buscar";
        
         HashMap<String, String> cookies = new HashMap<>();
        HashMap<String, String> formData = new HashMap<>();
        try {
//        Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET).userAgent(USER_AGENT).proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY).execute();
//        
//            Document loginDoc = loginForm.parse();
//        
//            
//            cookies.putAll(loginForm.cookies()); // save the cookies, this will be passed on to next request  
//            cookies.put("_ga","GA1.3.358109058.1506213293");
            
            formData.put("paraBuscar", "\""+investigador.getNombreCompleto()+"\"");
            formData.put("grupo", "Integrante");
            
            Connection.Response homePage = Jsoup.connect(loginActionUrl)
                    .cookies(cookies)
                    .data(formData)
                    .method(Connection.Method.POST)
                    .userAgent(USER_AGENT)
                    .proxy(ConstantesScrapper.proxy?new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ConstantesScrapper.urlProxy, ConstantesScrapper.puertoProxy) ):Proxy.NO_PROXY)
                    .execute();
            
            Elements grupos=Xsoup.compile("//*[@id=\"listGrupos\"]/tbody/tr/td[3]").evaluate(homePage.parse()).getElements();
            for (int i=0;i<grupos.size();i++) {
                GrupoInvestigacion grupoInvestigacion=new GrupoInvestigacion();
                grupoInvestigacion.setNombre(Xsoup.compile("/a/text()").evaluate(grupos.get(i)).get());
                grupoInvestigacion.setUrlGruplac(Xsoup.compile("/a/@href").evaluate(grupos.get(i)).get());
                gruposInvestigacion.add(grupoInvestigacion);
            }
           // System.out.println("Contenido"+ homePage.body());
        } catch (IOException ex) {
            Logger.getLogger(ExtractorIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gruposInvestigacion;
    }
}
