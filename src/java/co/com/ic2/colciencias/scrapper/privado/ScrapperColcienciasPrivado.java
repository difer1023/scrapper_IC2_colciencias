/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.privado;

import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.ScraperPublico2;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorApoyoProgramasFormacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorCapitulosLibroInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorDocumentosTrabajo;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEdiciones;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEmpresasBaseTeconologica;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEstrategiasPedagogicasFomentoCTI;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEventosCientificos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorGeneracionContenidosImpresos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInformesInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorLibrosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorParticipacionCiudadanaProyectosCTI;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorProyectos;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorRedesConocimiento;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorSoftwares;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorTrabajosDirigidos;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
@WebService(serviceName = "ScrapperColcienciasPrivado")
public class ScrapperColcienciasPrivado {

    /**
     * This is a sample web service operation
     * @param tipoNacionalidad
     * @param paisNacimiento
     * @param nombre
     * @param identificacion
     * @param contrasena
     * @return 
     */
    @WebMethod(operationName = "extraerGrupoInvestigacion")
    public GrupoInvestigacion extraerGrupoInvestigacion(
            @WebParam(name = "tipoNacionalidad") String tipoNacionalidad,
            @WebParam(name = "paisNacimiento") String paisNacimiento,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "identificacion") String identificacion,
            @WebParam(name = "contrasena") String contrasena) {
        GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion();
        Document documento;

        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
                + "          \" 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36\"";
        String loginFormUrl = "http://scienti.colciencias.gov.co:8080/gruplac/";
        String loginActionUrl = "http://scienti.colciencias.gov.co:8080/gruplac/LoginGruplac/login.do";

        String fechaNacimiento = "";

        HashMap<String, String> cookies = new HashMap<>();
        HashMap<String, String> formData = new HashMap<>();

        try {

            Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET).userAgent(USER_AGENT).execute();
            Document loginDoc = loginForm.parse();
            
            cookies.putAll(loginForm.cookies()); // save the cookies, this will be passed on to next request  
            cookies.put("_ga","GA1.3.358109058.1506213293");
            
            formData.put("tpo_nacionalidade", tipoNacionalidad);  
            formData.put("sgl_pais_nasc", paisNacimiento);  
            formData.put("nme_rh", nombre);  
            formData.put("cpf_rh", identificacion);  
            formData.put("dta_nasc_string", fechaNacimiento); 
            formData.put("txt_senha_cnpq", contrasena); 
            
            Connection.Response homePage = Jsoup.connect(loginActionUrl)
                    .cookies(cookies)
                    .data(formData)
                    .method(Connection.Method.POST)
                    .userAgent(USER_AGENT)
                    .execute();
            
            //grupoInvestigacion.setArticulosInvestigacion(ExtractorArticulosInvestigacion.extraerArticulosPublicadosPrivado(extraerTablaProductos("ART", "19", "GNC", cookies), cookies));
            //grupoInvestigacion.setLibrosResultadoInvestigacion(ExtractorLibrosInvestigacion.extraerLibrosPublicadosPrivado(extraerTablaProductos("LIB", "19", "GNC", cookies), cookies));
            //grupoInvestigacion.setCapituloDeLibro(ExtractorCapitulosLibroInvestigacion.extraerCapitulosLibroPrivado(extraerTablaProductos("GC_CAP_LIB", "19", "GNC", cookies), cookies));
            //grupoInvestigacion.setSoftware(ExtractorSoftwares.extraerSoftwaresPrivado(extraerTablaProductos("SF", "19", "DTI", cookies), cookies));
            //grupoInvestigacion.setEmpresaBaseTecnologica(ExtractorEmpresasBaseTeconologica.extraerEmpresaBaseTecnologicaPrivado(extraerTablaProductos("EBT", "19", "DTI", cookies), cookies));
            //grupoInvestigacion.setParticipacionCiudadanaProyectoCTI(ExtractorParticipacionCiudadanaProyectosCTI.extraerPaticipacionCiudadanaProyectosCTIPrivado(extraerTablaProductos("PCI", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setEstrategiaPedagogicaFomentoCTI(ExtractorEstrategiasPedagogicasFomentoCTI.extraerEstrategiasPedagogicasFomentoCTIPrivado(extraerTablaProductos("EPA", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setGeneracionContenidoImpreso(ExtractorGeneracionContenidosImpresos.extraerGeneracionContenidosImpresosPrivado(extraerTablaProductos("GC_I", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setEventoCientifico(ExtractorEventosCientificos.extraerEventosCientificosPrivado(extraerTablaProductos("EC", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setRedConocimiento(ExtractorRedesConocimiento.extraerRedesConocimientoPrivado(extraerTablaProductos("RC", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setDocumentoTrabajo(ExtractorDocumentosTrabajo.extraerDocumentosTrabajoPrivado(extraerTablaProductos("WP", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setEdicion(ExtractorEdiciones.extraerEdicionesPrivado(extraerTablaProductos("buscar", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setInformeInvestigacion(ExtractorInformesInvestigacion.extraerInformesInvestigacionPrivado(extraerTablaProductos("IFI", "19", "ASC", cookies), cookies));
            //grupoInvestigacion.setTrabajoDirigido(ExtractorTrabajosDirigidos.extraerTesisDoctoradoPrivado(extraerTablaProductos("TD", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setTrabajoDirigido(ExtractorTrabajosDirigidos.extraerTesisMaestriaPrivado(extraerTablaProductos("TM", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setTrabajoDirigido(ExtractorTrabajosDirigidos.extraerTesisPregradoPrivado(extraerTablaProductos("TP", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setProyecto(ExtractorProyectos.extraerProyectoInvestigacionDesarrolloPrivado(extraerTablaProductos("PID", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setProyecto(ExtractorProyectos.extraerProyectoIDIPrivado(extraerTablaProductos("PF", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setProyecto(ExtractorProyectos.extraerProyectoExtensionResponsabilidadSocialPrivado(extraerTablaProductos("buscar", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setApoyoProgramaFormacion(ExtractorApoyoProgramasFormacion.extraerApoyoCreacionProgramasPrivado(extraerTablaProductos("AP", "19", "FRH", cookies), cookies));
            //grupoInvestigacion.setApoyoProgramaFormacion(ExtractorApoyoProgramasFormacion.extraerApoyoCreacionCursosPrivado(extraerTablaProductos("AC", "19", "FRH", cookies), cookies));
            try{
            grupoInvestigacion.setApoyoProgramaFormacion(ExtractorApoyoProgramasFormacion.extraerApoyoCreacionCursosPrivado(extraerTablaProductos("PR", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer apoyo a programa de formacion");
            }
//            documento = Jsoup.connect(urlGruplac).get();
//            Elements elements=Xsoup.compile("/html/body/table[1]/tbody/tr").evaluate(documento).getElements();

            Gson gson = new Gson();

            System.out.println(gson.toJson(grupoInvestigacion));
        } catch (IOException ex) {
            Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupoInvestigacion;
    }
    
    private ArrayList<Elements> extraerTablaProductos(String tipoProducto,String idConvocatoria,String claseProduccion,HashMap<String, String> cookies) throws IOException, NullPointerException{
       
        ArrayList<Elements> elementosTabla=new ArrayList<>();
        
        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
                + "          \" 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36\"";
        
        boolean recorrerTabla=true;
        
        int pagina=1;
        while(recorrerTabla){
        HashMap<String, String> datosProductosConvocatoria = new HashMap<>();
            
            datosProductosConvocatoria.put(tipoProducto+"_TABLE_p_", String.valueOf(pagina));  
            datosProductosConvocatoria.put(tipoProducto+"_TABLE_mr_", "15");  
            datosProductosConvocatoria.put(tipoProducto+"_TABLE_tr_", "true");  
            datosProductosConvocatoria.put("cod_convocatoria", idConvocatoria);  
            datosProductosConvocatoria.put("sgl_categoria", tipoProducto); 
            datosProductosConvocatoria.put("clase_prod", claseProduccion); 
//            System.out.println("HOME>>>>>>>>>>>>>>>>>>>>>>>>>>"+homePage.parse().html());
        
            Connection.Response res2 = Jsoup.connect("http://scienti.colciencias.gov.co:8080/gruplac/Medicion/calificacion/redirect.do")
            .data(datosProductosConvocatoria)
            .method(Connection.Method.GET)
            .cookies(cookies)
            .userAgent(USER_AGENT)
            .execute();
            
            Elements elements=Xsoup.compile("/html/body/div/form/div/table/tbody[1]/tr").evaluate(res2.parse()).getElements();
            
            String resultadosTabla=Xsoup.compile("/html/body/div/form/div/table/tbody[2]/tr/td/text()").evaluate(res2.parse()).get();
            
            String [] registros=(resultadosTabla.split(" - ")[1]).split(" de ");
            
            int numeroActual=Integer.parseInt(registros[0]);
            
            int numeroTotal=Integer.parseInt(registros[1].substring(0, registros[1].length()-1));
            
            System.out.println("RESULTADPS "+resultadosTabla);
            System.out.println(numeroActual+"de"+numeroTotal);
            if(numeroActual<numeroTotal){
                pagina++;
            }else{
                recorrerTabla=false;
            }
            
            elementosTabla.add(elements);
//            System.out.println("TABLA>>>>>>>>>>>>>>>>"+res2.parse().html());
        
        }
        
        
    return elementosTabla;
    }
}
