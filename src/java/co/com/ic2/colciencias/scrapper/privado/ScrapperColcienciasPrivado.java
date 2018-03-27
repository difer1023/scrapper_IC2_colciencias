/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.privado;

import co.com.ic2.colciencias.constants.ConstantesScrapper;
import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorApoyoProgramasFormacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorArticulosInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorCapitulosLibroInvestigacion;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorDocumentosTrabajo;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEdiciones;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorEmpresasBaseTecnologica;
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
 * Clase que se encarga de extraer la información de la parte privada de un grupo de investigación
 * Requiere de los datos necesarios para hacer Log In
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
        String loginFormUrl = ConstantesScrapper.urlGruplac+"/gruplac/";
        String loginActionUrl = ConstantesScrapper.urlGruplac+"/gruplac/LoginGruplac/login.do";

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
            
            //Pieza de codigo para extraer productos de grupos de lideres con mas de un grupo de investigacion
             //System.out.println(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/b[2]/text()").evaluate(homePage.parse()).getElements());
//            Elements enlaces = Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/li").evaluate(homePage.parse()).getElements();
//
//            System.out.println(enlaces);
//
//            String enlaceGrupo = ConstantesScrapper.urlGruplac+"/gruplac" + (Xsoup.compile("/a/@href").evaluate(enlaces.get(1)).get().replace("..", ""));
//
//            System.out.println(enlaceGrupo);
//
//            Connection.Response home = Jsoup.connect(enlaceGrupo)
//                    .cookies(cookies)
//                    .method(Connection.Method.GET)
//                    .userAgent(USER_AGENT)
//                    .execute();
            //////////////////
            try{
            grupoInvestigacion.setArticulosInvestigacion(ExtractorArticulosInvestigacion.extraerArticulosPublicadosPrivado(extraerTablaProductos("ART", "19", "GNC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer articulos");
            }
            try{
            grupoInvestigacion.setLibrosResultadoInvestigacion(ExtractorLibrosInvestigacion.extraerLibrosPublicadosPrivado(extraerTablaProductos("LIB", "19", "GNC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer libros");
            }
            try{
            grupoInvestigacion.setCapituloDeLibro(ExtractorCapitulosLibroInvestigacion.extraerCapitulosLibroPrivado(extraerTablaProductos("CAP_LIB", "19", "GNC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer capítulos de libro");
            }
            try{
            grupoInvestigacion.setSoftware(ExtractorSoftwares.extraerSoftwaresPrivado(extraerTablaProductos("SF", "19", "DTI", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer software");
            }
            try{
            grupoInvestigacion.setEmpresaBaseTecnologica(ExtractorEmpresasBaseTecnologica.extraerEmpresaBaseTecnologicaPrivado(extraerTablaProductos("EBT", "19", "DTI", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer empresas de base tecnologica");
            }
            try{
            grupoInvestigacion.setParticipacionCiudadanaProyectoCTI(ExtractorParticipacionCiudadanaProyectosCTI.extraerPaticipacionCiudadanaProyectosCTIPrivado(extraerTablaProductos("PCI", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer participacion ciudadana");
            }
            try{
            grupoInvestigacion.setEstrategiaPedagogicaFomentoCTI(ExtractorEstrategiasPedagogicasFomentoCTI.extraerEstrategiasPedagogicasFomentoCTIPrivado(extraerTablaProductos("EPA", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer estrategia pedagogica");
            }
            try{
            grupoInvestigacion.setGeneracionContenidoImpreso(ExtractorGeneracionContenidosImpresos.extraerGeneracionContenidosImpresosPrivado(extraerTablaProductos("GC_I", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer generación de contenidos impresos");
            }
            try{
            grupoInvestigacion.setEventoCientifico(ExtractorEventosCientificos.extraerEventosCientificosPrivado(extraerTablaProductos("EC", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer eventos cientificos");
            }
            try{
            grupoInvestigacion.setRedConocimiento(ExtractorRedesConocimiento.extraerRedesConocimientoPrivado(extraerTablaProductos("RC", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al redes de conocimiento");
            }
            try{
            grupoInvestigacion.setDocumentoTrabajo(ExtractorDocumentosTrabajo.extraerDocumentosTrabajoPrivado(extraerTablaProductos("WP", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer documentos de trabajo");
            }
            try{
            grupoInvestigacion.setEdicion(ExtractorEdiciones.extraerEdicionesPrivado(extraerTablaProductos("ERL", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer ediciones");
            }
            try{
            grupoInvestigacion.setInformeInvestigacion(ExtractorInformesInvestigacion.extraerInformesInvestigacionPrivado(extraerTablaProductos("IFI", "19", "ASC", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer informes de investigacion");
            }
            try{
            grupoInvestigacion.setTrabajoDirigido(ExtractorTrabajosDirigidos.extraerTesisDoctoradoPrivado(extraerTablaProductos("TD", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer tesis de doctorado");
            }
            try{
            grupoInvestigacion.setTrabajoDirigido(ExtractorTrabajosDirigidos.extraerTesisMaestriaPrivado(extraerTablaProductos("TM", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer tesis maestria");
            }
            try{
            grupoInvestigacion.setTrabajoDirigido(ExtractorTrabajosDirigidos.extraerTesisPregradoPrivado(extraerTablaProductos("TP", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer tesis pregrado");
            }
            try{
            grupoInvestigacion.setProyecto(ExtractorProyectos.extraerProyectoInvestigacionDesarrolloPrivado(extraerTablaProductos("PID", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer proyecto investigacion y desarrollo");
            }
            try{
            grupoInvestigacion.setProyecto(ExtractorProyectos.extraerProyectoIDIPrivado(extraerTablaProductos("PF", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer proyecto I+D+I");
            }
            try{
            grupoInvestigacion.setProyecto(ExtractorProyectos.extraerProyectoExtensionResponsabilidadSocialPrivado(extraerTablaProductos("PE", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer proyecto extension responsabilidad social");
            }
            try{
            grupoInvestigacion.setApoyoProgramaFormacion(ExtractorApoyoProgramasFormacion.extraerApoyoCreacionProgramasPrivado(extraerTablaProductos("AP", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer apoyo creacion programas");
            }
            try{
            grupoInvestigacion.setApoyoProgramaFormacion(ExtractorApoyoProgramasFormacion.extraerApoyoCreacionCursosPrivado(extraerTablaProductos("AC", "19", "FRH", cookies), cookies));
            }catch(NullPointerException e){
                System.out.println("Error al extraer apoyo a programa de formacion");
            }
//            documento = Jsoup.connect(urlGruplac).get();
//            Elements elements=Xsoup.compile("/html/body/table[1]/tbody/tr").evaluate(documento).getElements();

            Gson gson = new Gson();

            System.out.println(gson.toJson(grupoInvestigacion));
        } catch (IOException ex) {
            Logger.getLogger(ScrapperColcienciasPrivado.class.getName()).log(Level.SEVERE, null, ex);
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
        
            Connection.Response res2 = Jsoup.connect(ConstantesScrapper.urlGruplac+"/gruplac/Medicion/calificacion/redirect.do")
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
