/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico;

import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import co.com.ic2.colciencias.gruplac.Institucion;
import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.LineaInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ArticuloInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.AsesoriaProgramaOndas;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.CapituloLibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Consultoria;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DisenoIndustrial;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DocumentoTrabajo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Edicion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EmpresaBaseTecnologica;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EspacioParticipacionCiudadana;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EsquemaCircuito;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EstrategiaComunicacionConocimiento;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EstrategiaPedagogicaFomentoCTI;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EventoCientifico;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoImpreso;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoMultimedia;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.GeneracionContenidoVirtual;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InformeInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.InnovacionProceso;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.LibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroArticuloPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroLibroPublicado;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ParticipacionCiudadanaProyectoCTI;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.PlantaPiloto;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Prototipo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Proyecto;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.RedConocimiento;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.SignoDistintivo;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.Software;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoDirigido;
import co.com.ic2.colciencias.scrapper.publico.utilitarios.ExtractorInstituciones;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;
/**
 *
 * @author L
 */
public class ScraperPublico2 {
      
      public void extraerGrupoInvestigacion(
              String urlGruplac,
              boolean instituciones,
              boolean lineasInvestigacion,
              boolean integrantes,
              boolean productos){
          
          Document doc = null;
        try {
            doc = Jsoup.connect(urlGruplac).get();
            Elements elements=Xsoup.compile("/html/body/table[1]/tbody/tr").evaluate(doc).getElements();
            GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion();
            grupoInvestigacion.setAnoMesFormacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(1)).get());
            grupoInvestigacion.setDepartamento(Xsoup.compile("/td[2]/text()").evaluate(elements.get(2)).get().split("-")[0]);
            grupoInvestigacion.setCiudad(Xsoup.compile("/td[2]/text()").evaluate(elements.get(2)).get().split("-")[1]);
            grupoInvestigacion.setLider(Xsoup.compile("/td[2]/text()").evaluate(elements.get(3)).get());
            grupoInvestigacion.setInfoCertificacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(4)).get());
            grupoInvestigacion.setPagWeb(Xsoup.compile("/td[2]/text()").evaluate(elements.get(5)).get());
            grupoInvestigacion.setEmail(Xsoup.compile("/td[2]/text()").evaluate(elements.get(6)).get());
            grupoInvestigacion.setClasificacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(7)).get());
            grupoInvestigacion.setProgramaCienciaTecnologia(Xsoup.compile("/td[2]/text()").evaluate(elements.get(8)).get());
            grupoInvestigacion.setProgramaCienciaTecnologiaSecundario(Xsoup.compile("/td[2]/text()").evaluate(elements.get(9)).get());
            
            if(instituciones){
            elements=Xsoup.compile("/html/body/table[2]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setInstituciones(extraerInstituciones(elements));
            }
            
            if(lineasInvestigacion){
            //elements=Xsoup.compile("/html/body/table[4]/tbody/tr").evaluate(doc).getElements();
            //grupoInvestigacion.setLineasInvestigacion(extraerLineasInvestigacion(elements));
            }
            
            if(integrantes){
//            elements=Xsoup.compile("/html/body/table[6]/tbody/tr").evaluate(doc).getElements();
//            grupoInvestigacion.setIntegrantes(extraerIntegrantes(elements));
            }
            
            if(productos){
            elements=Xsoup.compile("/html/body/table[8]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setArticulosInvestigacion(extraerArticulosPublicados(elements));
            
            elements=Xsoup.compile("/html/body/table[9]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setLibrosResultadoInvestigacion(extraerLibrosPublicados(elements));
            
            elements=Xsoup.compile("/html/body/table[10]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setCapituloDeLibro(extraerCapitulosLibroPublicados(elements));
            
            elements=Xsoup.compile("/html/body/table[11]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setDocumentoTrabajo(extraerDocumentosTrabajo(elements));
            
            elements=Xsoup.compile("/html/body/table[13]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setOtroArticuloPublicado(extraerOtrosArticulosPublicados(elements));
            
            elements=Xsoup.compile("/html/body/table[14]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setOtroLibroPublicado(extraerOtrosLibrosPublicados(elements));
            
            
            elements=Xsoup.compile("/html/body/table[18]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setConsultoria(extraerConsultorias(elements));
            
            elements=Xsoup.compile("/html/body/table[19]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setDisenoIndustrial(extraerDisenosIndustriales(elements));
            
            elements=Xsoup.compile("/html/body/table[20]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEsquemaCircuito(extraerEsquemasCircuito(elements));
            
            elements=Xsoup.compile("/html/body/table[21]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setInnovacionProceso(extraerInnovacionProcesos(elements));
            
            elements=Xsoup.compile("/html/body/table[25]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setPlantaPiloto(extraerPlantasPiloto(elements));
            
            elements=Xsoup.compile("/html/body/table[27]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setPrototipo(extraerPrototipos(elements));
            
            elements=Xsoup.compile("/html/body/table[32]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setSignoDistintivo(extraerSignosDistintivos(elements));
            
            elements=Xsoup.compile("/html/body/table[33]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setSoftware(extraerSoftwares(elements));
            
            elements=Xsoup.compile("/html/body/table[34]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEmpresaBaseTecnologica(extraerEmpresasBaseTecnologica(elements));
            
            elements=Xsoup.compile("/html/body/table[36]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEdicion(extraerEdiciones(elements));
            
            elements=Xsoup.compile("/html/body/table[37]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEventoCientifico(extraerEventosCientificos(elements));
            
            elements=Xsoup.compile("/html/body/table[38]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setInformeInvestigacion(extraerInformesInvestigacion(elements));
            
            elements=Xsoup.compile("/html/body/table[39]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setRedConocimiento(extraerRedesConocimiento(elements));
            
            elements=Xsoup.compile("/html/body/table[40]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setGeneracionContenidoImpreso(extraerGeneracionContenidosImpresos(elements));
           
            elements=Xsoup.compile("/html/body/table[41]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setGeneracionContenidoMultimedia(extraerGeneracionContenidosMultimedia(elements));
            
            elements=Xsoup.compile("/html/body/table[42]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setGeneracionContenidoVirtual(extraerGeneracionContenidosVirtuales(elements));
            
            elements=Xsoup.compile("/html/body/table[43]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEstrategiaComunicacionConocimiento(extraerEstrategiasComunicacionConocimiento(elements));
            
            elements=Xsoup.compile("/html/body/table[44]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEstrategiaPedagogicaFomentoCTI(extraerEstrategiasPedagogicasFomentoCTI(elements));
            
            elements=Xsoup.compile("/html/body/table[45]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setEspacioParticipacionCiudadana(extraerEspaciosParticipacionCiudadana(elements));
            
            elements=Xsoup.compile("/html/body/table[46]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setParticipacionCiudadanaProyectoCTI(extraerParticipacionCiudadanaProyectosCTI(elements));
            
            elements=Xsoup.compile("/html/body/table[49]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setAsesoriaProgramaOndas(extraerAsesoriasProgramaOndas(elements));
            
            elements=Xsoup.compile("/html/body/table[51]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setTrabajoDirigido(extraerTrabajosDirigidos(elements));
            
            elements=Xsoup.compile("/html/body/table[56]/tbody/tr").evaluate(doc).getElements();
            grupoInvestigacion.setProyecto(extraerProyectos(elements));
            }
            Gson gson = new Gson();
            
            System.out.println(gson.toJson(grupoInvestigacion));
        } catch (IOException ex) {
            Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        //Giira software, edicion, informes de investigación, redes de conocimiento, participacion ciudadana proyectos CTI
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000001394");
        //documentos de trabajo
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000004910");
        //consultoria, empresa base tecnologica Virtus, generacion contenido impreso
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000006360");
        //diseños industriales y cartas, planta piloto, prototipos
        new ScraperPublico2().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000016232",true,true,true,true);
        //esquemas de circuito innovaciones en proceso y procedimientos, evento cientifico 105 tiene mas de una institucion
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000001495");
        //prototipos, signos distintivos 
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000015930");
        //Generacion contenido multimedia y virtual
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000004146");
        //Estrategias de Comunicación del Conocimiento, espacios participacion ciudadana, programa ondas
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000008364");
        //Estrategias pedagogicas fomento
        //new Scraper().extraerGrupoInvestigacion("http://scienti.colciencias.gov.co:8080/gruplac/jsp/visualiza/visualizagr.jsp?nro=00000000012847");
    }

    private ArrayList<Institucion> extraerInstituciones(Elements elements) {
//        ArrayList<Institucion> instituciones = new ArrayList();
//        for(int i=1;i<elements.size();i++){
//            Institucion institucion = new Institucion();
//            institucion.setNombre(Xsoup.compile("/td/text()").evaluate(elements.get(i)).get().split("-")[1]);
//            institucion.setAvalado(Xsoup.compile("/td/text()").evaluate(elements.get(i)).get().split("-")[2].contains("(Avalado)"));
//            instituciones.add(institucion);
//        }
        return ExtractorInstituciones.extraerInstituciones(elements);
    }

    private ArrayList<LineaInvestigacion> extraerLineasInvestigacion(Elements elements) {
        ArrayList<LineaInvestigacion> lineasInvestigacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            LineaInvestigacion lineaInvestigacion = new LineaInvestigacion();
            String nombre=Xsoup.compile("/td/text()").evaluate(elements.get(i)).get();
            lineaInvestigacion.setNombre(nombre.substring(4));
            lineasInvestigacion.add(lineaInvestigacion);
        }
        return lineasInvestigacion;
    }

    private ArrayList<Integrante> extraerIntegrantes(Elements elements) {
        ArrayList<Integrante> integrantes = new ArrayList();
       for(int i=2;i<elements.size();i++){
            Integrante integrante = new Integrante();
            integrante.setNombreCompleto(Xsoup.compile("/td[1]/a/text()").evaluate(elements.get(i)).get());
            integrante.setVinculacion(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
            integrante.setHorasDedicacion(Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get());
            integrante.setInicioVinculacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[0]);
            integrante.setFinVinculacion(Xsoup.compile("/td[4]/text()").evaluate(elements.get(i)).get().split("-")[1]);
            
            String url= (Xsoup.compile("/td[1]/a/@href").evaluate(elements.get(i)).get());
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException ex) {
                Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String categoriaIntegrante=Xsoup.compile("/html/body/table/tbody/tr[2]/td/blockquote/table/tbody/tr[1]/td[1]/text()").evaluate(doc).get();
            
            if(categoriaIntegrante==null){

                String campo = Xsoup.compile("/html/body/div/div[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/text()").evaluate(doc).get();
                
                if(campo!=null && StringUtils.stripAccents(campo).equalsIgnoreCase("Categoria ")){
                categoriaIntegrante=Xsoup.compile("/html/body/div/div[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/text()").evaluate(doc).get();
                integrante.setCategoria(categoriaIntegrante);
                }
            }else{
                categoriaIntegrante=categoriaIntegrante.replace("Categoría", "");
                integrante.setCategoria(categoriaIntegrante);
            }
            integrantes.add(integrante);
        }
        return integrantes;
    }
    
    //problemas con grupos muy grandes como giira
    //No scrapea el primer artículo, pendiente scrapear volumen, numero pag inicial y final y doi
    private ArrayList<ArticuloInvestigacion> extraerArticulosPublicados(Elements elements) {
        ArrayList<ArticuloInvestigacion> articulosPublicados = new ArrayList();
        for(int i=2;i<elements.size();i++){

            ArticuloInvestigacion articuloInvestigacion = new ArticuloInvestigacion();
            articuloInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            articuloInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(1));

            String detalleRevista=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            articuloInvestigacion.setPais(detalleRevista.split(",")[0].substring(1));
            String nombreRevista=detalleRevista.split(",")[1].split("ISSN:")[0];
            String[] arrayISSN=detalleRevista.split(",")[1].split("ISSN:");
            String ano=detalleRevista.split(",")[2].substring(1,5);
            System.out.println("detalle"+detalleRevista);
            if(arrayISSN.length<2){
                for(int j=2;arrayISSN.length<2;j++){
                    arrayISSN=detalleRevista.split(",")[j].split("ISSN:");
                    if(arrayISSN.length<2){
                    nombreRevista+=detalleRevista.split(",")[j];
                    
                    }else{
                    nombreRevista+=detalleRevista.split(",")[j].split("ISSN:")[0];
                    ano=detalleRevista.split(",")[j+1].substring(1,5);
                    }
                }
            }
            articuloInvestigacion.setRevista(nombreRevista.substring(1, nombreRevista.length()-1));
            articuloInvestigacion.setIssn(arrayISSN[1].substring(1));
            articuloInvestigacion.setAno(Integer.parseInt(ano));
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            articuloInvestigacion.setAutores(autores);
            articulosPublicados.add(articuloInvestigacion);
        }
        return articulosPublicados;
    }
    
    
    //pendientes espacio blanco despues de la editorial
    private ArrayList<LibroPublicado> extraerLibrosPublicados(Elements elements) {
        ArrayList<LibroPublicado> librosPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            LibroPublicado libroPublicado = new LibroPublicado();
            libroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            libroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            libroPublicado.setPais(detalleLibro.split(",")[0].substring(1));
            libroPublicado.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            libroPublicado.setEditorial(detalleLibro.split(",")[3].substring(1));
            String isbn=detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
            libroPublicado.setIsbn(isbn.substring(1,isbn.length()-1));
            
            System.out.println("detalle"+detalleLibro);
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            libroPublicado.setAutores(autores);
//            libroPublicado.setIsbn(Xsoup.compile("/td[2]/text()[2]").evaluate(elements.get(i)).get());
            //articuloInvestigacion.setAno(Xsoup.compile("/td[2]/text()[3]").evaluate(elements.get(i)).get());
            //articuloInvestigacion.setAutores(Xsoup.compile("/td[2]/text()[5]").evaluate(elements.get(i)).get());
           
            librosPublicados.add(libroPublicado);
        }
        return librosPublicados;
    }
    
    //pendiente campos en orden,  espacio en blanco al final de editorial  
     private ArrayList<CapituloLibroPublicado> extraerCapitulosLibroPublicados(Elements elements) {
        ArrayList<CapituloLibroPublicado> capitulosLibroPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            CapituloLibroPublicado capituloLibroPublicado = new CapituloLibroPublicado();
            capituloLibroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            capituloLibroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleCapLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            capituloLibroPublicado.setPais(detalleCapLibro.split(",")[0].substring(1));
            
            String ano=detalleCapLibro.split(",")[1].substring(1,5);
            capituloLibroPublicado.setAno(Integer.parseInt(ano));
            capituloLibroPublicado.setTituloLibro(detalleCapLibro.split(",")[2].substring(1));
            //System.out.println("detalle"+detalleCapLibro);
            
            String [] detalleCapLibro2=detalleCapLibro.split("ISBN:");
            //System.out.println("Isbn "+detalleCapLibro2[0]);
            //System.out.println("Isbn 2"+detalleCapLibro2[1]);
            //String [] detallegeneral=detalleCapLibro2[1].split(",");
            //System.out.println("algo"+detalleCapLibro2[0]);
            capituloLibroPublicado.setIsbn(detalleCapLibro2[1].split(",")[0].substring(1));
            capituloLibroPublicado.setEditorial(detalleCapLibro2[1].split(",")[3].substring(1));
            
            //capituloLibroPublicado.setIsbn(isbn.substring(1,isbn.length()-1));
            //System.out.println("detalle"+detalleCapLibro);
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            capituloLibroPublicado.setAutores(autores);

            capitulosLibroPublicados.add(capituloLibroPublicado);
        }
        return capitulosLibroPublicados;
    }
     
     //Pendiente scrapear instituciones
     private ArrayList<DocumentoTrabajo> extraerDocumentosTrabajo(Elements elements) {
        ArrayList<DocumentoTrabajo> documentosTrabajo = new ArrayList();
        for(int i=1;i<elements.size();i++){
            DocumentoTrabajo documentoTrabajo = new DocumentoTrabajo();
            documentoTrabajo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            documentoTrabajo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDocTrabajo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleDocTrabajo.split(",")[0].substring(1,5);
            documentoTrabajo.setAno(Integer.parseInt(ano));
            documentoTrabajo.setNumPaginas(detalleDocTrabajo.split(",")[1].substring(15));
         
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            documentoTrabajo.setAutores(autores);

            documentosTrabajo.add(documentoTrabajo);
        }
        return documentosTrabajo;
    }
     
    //No scrapea el primer artículo, pendiente scrapear volumen, numero pag inicial y final y doi
    private ArrayList<OtroArticuloPublicado> extraerOtrosArticulosPublicados(Elements elements) {
        ArrayList<OtroArticuloPublicado> otrosArticulosPublicados = new ArrayList();
        for(int i=2;i<elements.size();i++){

            OtroArticuloPublicado OtroArticuloPublicado = new OtroArticuloPublicado();
            OtroArticuloPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            OtroArticuloPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(1));

            String detalleRevista=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            OtroArticuloPublicado.setPais(detalleRevista.split(",")[0].substring(1));
            String nombreRevista=detalleRevista.split(",")[1].split("ISSN:")[0];
            String[] arrayISSN=detalleRevista.split(",")[1].split("ISSN:");
            String ano=detalleRevista.split(",")[2].substring(1,5);
            System.out.println("detalle"+detalleRevista);
            if(arrayISSN.length<2){
                for(int j=2;arrayISSN.length<2;j++){
                    arrayISSN=detalleRevista.split(",")[j].split("ISSN:");
                    if(arrayISSN.length<2){
                    nombreRevista+=detalleRevista.split(",")[j];
                    
                    }else{
                    nombreRevista+=detalleRevista.split(",")[j].split("ISSN:")[0];
                    ano=detalleRevista.split(",")[j+1].substring(1,5);
                    }
                }
            }
            OtroArticuloPublicado.setRevista(nombreRevista.substring(1, nombreRevista.length()-1));
            OtroArticuloPublicado.setIssn(arrayISSN[1].substring(1));
            OtroArticuloPublicado.setAno(Integer.parseInt(ano));
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            OtroArticuloPublicado.setAutores(autores);
            otrosArticulosPublicados.add(OtroArticuloPublicado);
        }
        return otrosArticulosPublicados;
    }
    
    
    //pendientes espacio blanco despues de la editorial
    private ArrayList<OtroLibroPublicado> extraerOtrosLibrosPublicados(Elements elements) {
        ArrayList<OtroLibroPublicado> otrosLibrosPublicados = new ArrayList();
        for(int i=1;i<elements.size();i++){
            OtroLibroPublicado otroLibroPublicado = new OtroLibroPublicado();
            otroLibroPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            otroLibroPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleLibro=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            otroLibroPublicado.setPais(detalleLibro.split(",")[0].substring(1));
            otroLibroPublicado.setAno(Integer.parseInt(detalleLibro.split(",")[1]));
            otroLibroPublicado.setEditorial(detalleLibro.split(",")[3].substring(1));
            String isbn=detalleLibro.split(",")[2].split("vol:")[0].split("ISBN:")[1];
            otroLibroPublicado.setIsbn(isbn.substring(1,isbn.length()-1));
            
            System.out.println("detalle"+detalleLibro);
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            otroLibroPublicado.setAutores(autores);
   
            otrosLibrosPublicados.add(otroLibroPublicado);
        }
        return otrosLibrosPublicados;
    }
     
    //no hay que extraer disponibilidad ni institucion, espacio en blanco al final de institucion
     private ArrayList<Consultoria> extraerConsultorias(Elements elements) {
        ArrayList<Consultoria> consultorias = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Consultoria consultoria = new Consultoria();
            consultoria.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            consultoria.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleConsultoria=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            consultoria.setPais(detalleConsultoria.split(",")[0].substring(1));
            String ano=detalleConsultoria.split(",")[1].substring(1,5);
            consultoria.setAno(Integer.parseInt(ano));
            consultoria.setIdioma(detalleConsultoria.split(",")[2].substring(9));
            consultoria.setDisponibilidad(detalleConsultoria.split(",")[3].substring(17));
            consultoria.setNumContrato(detalleConsultoria.split(",")[4].substring(22));
            
            String detalleConsultoriaI=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            consultoria.setInstitucion(detalleConsultoriaI.split(":")[1].substring(1));
        
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            consultoria.setAutores(autores);

            consultorias.add(consultoria);
        }
        return consultorias;
    }
     
     //espacio en blanco al final de institucion, no necesita disponibilidad ni institución
     private ArrayList<DisenoIndustrial> extraerDisenosIndustriales(Elements elements) {
        ArrayList<DisenoIndustrial> disenosIndustriales = new ArrayList();
        for(int i=1;i<elements.size();i++){
            DisenoIndustrial disenoIndustrial = new DisenoIndustrial();
            disenoIndustrial.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            disenoIndustrial.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDisenoIndustrial=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            disenoIndustrial.setPais(detalleDisenoIndustrial.split(",")[0].substring(1));
            String ano=detalleDisenoIndustrial.split(",")[1].substring(1,5);
            disenoIndustrial.setAno(Integer.parseInt(ano));
            disenoIndustrial.setDisponibilidad(detalleDisenoIndustrial.split(",")[2].substring(17));
            disenoIndustrial.setInstitucion(detalleDisenoIndustrial.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            disenoIndustrial.setAutores(autores);

            disenosIndustriales.add(disenoIndustrial);
        }
        return disenosIndustriales;
    }
     
     //espacio en blanco al final de institucion, no necesita disponibilidad ni institución
     private ArrayList<EsquemaCircuito> extraerEsquemasCircuito(Elements elements) {
        ArrayList<EsquemaCircuito> esquemasCircuito = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EsquemaCircuito esquemaCircuito = new EsquemaCircuito();
            esquemaCircuito.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            esquemaCircuito.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDisenoIndustrial=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            esquemaCircuito.setPais(detalleDisenoIndustrial.split(",")[0].substring(1));
            String ano=detalleDisenoIndustrial.split(",")[1].substring(1,5);
            esquemaCircuito.setAno(Integer.parseInt(ano));
            esquemaCircuito.setDisponibilidad(detalleDisenoIndustrial.split(",")[2].substring(17));
            esquemaCircuito.setInstitucion(detalleDisenoIndustrial.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            esquemaCircuito.setAutores(autores);

            esquemasCircuito.add(esquemaCircuito);
        }
        return esquemasCircuito;
    }
     
     //espacio en blanco al final de institucion, no necesita disponibilidad
     private ArrayList<InnovacionProceso> extraerInnovacionProcesos(Elements elements) {
        ArrayList<InnovacionProceso> innovacionProcesos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            InnovacionProceso innovacionProceso = new InnovacionProceso();
            innovacionProceso.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            innovacionProceso.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleDisenoIndustrial=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            innovacionProceso.setPais(detalleDisenoIndustrial.split(",")[0].substring(1));
            String ano=detalleDisenoIndustrial.split(",")[1].substring(1,5);
            innovacionProceso.setAno(Integer.parseInt(ano));
            innovacionProceso.setDisponibilidad(detalleDisenoIndustrial.split(",")[2].substring(17));
            innovacionProceso.setInstitucion(detalleDisenoIndustrial.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            innovacionProceso.setAutores(autores);

            innovacionProcesos.add(innovacionProceso);
        }
        return innovacionProcesos;
    }
     
     //No necesita disponibilidad ni nombre comercial, espacio en blanco despues de institucion
     private ArrayList<PlantaPiloto> extraerPlantasPiloto(Elements elements) {
        ArrayList<PlantaPiloto> plantasPiloto = new ArrayList();
        for(int i=1;i<elements.size();i++){
            PlantaPiloto plantaPiloto = new PlantaPiloto();
            plantaPiloto.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            plantaPiloto.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePlantaPiloto=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            plantaPiloto.setPais(detallePlantaPiloto.split(",")[0].substring(1));
            String ano=detallePlantaPiloto.split(",")[1].substring(1,5);
            plantaPiloto.setAno(Integer.parseInt(ano));
            plantaPiloto.setDisponibilidad(detallePlantaPiloto.split(",")[2].substring(17));
            plantaPiloto.setNombreComercial(detallePlantaPiloto.split(",")[3].substring(19));
            
            String detallePlantaPilotoI=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            plantaPiloto.setInstitucion(detallePlantaPilotoI.split(":")[1].substring(1));
        
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            plantaPiloto.setAutores(autores);

            plantasPiloto.add(plantaPiloto);
        }
        return plantasPiloto;
    }
     
     //No necesita disponibilidad ni nombre comercial, espacio en blanco despues de institucion
     private ArrayList<Prototipo> extraerPrototipos(Elements elements) {
        ArrayList<Prototipo> prototipos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Prototipo prototipo = new Prototipo();
            prototipo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            prototipo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePrototipo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            prototipo.setPais(detallePrototipo.split(",")[0].substring(1));
            String ano=detallePrototipo.split(",")[1].substring(1,5);
            prototipo.setAno(Integer.parseInt(ano));
            prototipo.setDisponibilidad(detallePrototipo.split(",")[2].substring(17));
            prototipo.setInstitucion(detallePrototipo.split(",")[3].substring(27));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            prototipo.setAutores(autores);

            prototipos.add(prototipo);
        }
        return prototipos;
    }
     
     
     private ArrayList<SignoDistintivo> extraerSignosDistintivos(Elements elements) {
        ArrayList<SignoDistintivo> signosDistintivos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            SignoDistintivo signoDistintivo = new SignoDistintivo();
            signoDistintivo.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            signoDistintivo.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detallePrototipo=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            signoDistintivo.setPais(detallePrototipo.split(",")[0].substring(1));
            String ano=detallePrototipo.split(",")[1].substring(1,5);
            signoDistintivo.setAno(Integer.parseInt(ano));
            signoDistintivo.setNumRegistro(detallePrototipo.split(",")[2].substring(22));
            signoDistintivo.setNombreTitular(detallePrototipo.split(",")[3].substring(21));
            
            signosDistintivos.add(signoDistintivo);
        }
        return signosDistintivos;
    }
     
     //Pendiente scrapear nombre comercial y del producto, no sé si es necesario pais autores disponibilidad
     //espacio en blanco al final de la institucion
     private ArrayList<Software> extraerSoftwares(Elements elements) {
        ArrayList<Software> softwares = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Software software = new Software();
            software.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            software.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleSoftware=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            software.setPais(detalleSoftware.split(",")[0].substring(1));
            String ano=detalleSoftware.split(",")[1].substring(1,5);
            software.setAno(Integer.parseInt(ano));
            software.setDisponibilidad(detalleSoftware.split(",")[2].substring(17));
            software.setSitioWeb(detalleSoftware.split(",")[3].substring(12));
           
            //String detalleSoftwareNombres=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            //software.setNombreComercial(detalleSoftwareNombres.split(":")[1].substring(12).split(",")[0]);
            //software.setNombreProyecto(detalleSoftwareNombres.split(":")[3].substring(12).split(",")[2]);
            
            String detalleSoftwareI=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            software.setInstitucion(detalleSoftwareI.split(":")[1].substring(1));
           
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            software.setAutores(autores);

            softwares.add(software);
        }
        return softwares;
    }
     
     //Espacio en blanco al final del estado, fecha de registro tal vez necesita substring
     //No sé si es necesario scrapear autor, y cual de las dos fechas
     private ArrayList<EmpresaBaseTecnologica> extraerEmpresasBaseTecnologica(Elements elements) {
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
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
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
     
     //No sé qué campos hay que scrapear exactamente
     private ArrayList<Edicion> extraerEdiciones(Elements elements) {
        ArrayList<Edicion> ediciones = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Edicion edicion = new Edicion();
            edicion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            edicion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEdicion=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            edicion.setPais(detalleEdicion.split(",")[0].substring(1));
            String ano=detalleEdicion.split(",")[1].substring(1,5);
            edicion.setAno(Integer.parseInt(ano));
            edicion.setEditorial(detalleEdicion.split(",")[2].substring(12));
            edicion.setIdiomas(detalleEdicion.split(",")[3].substring(10));
            edicion.setNumPaginas(detalleEdicion.split(",")[4].substring(10));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            edicion.setAutores(autores);

            ediciones.add(edicion);
        }
        return ediciones;
    }
     
     //No sé si hay que scrapear ambito, faltan instituciones patrocinadoras 13 giiira hay mas de una
     //falta posible substring fechas, espacio en blanco al final de ambas
     private ArrayList<EventoCientifico> extraerEventosCientificos(Elements elements) {
        ArrayList<EventoCientifico> eventosCientificos= new ArrayList();
        for(int i=1;i<elements.size();i++){
            EventoCientifico eventoCientifico = new EventoCientifico();
            eventoCientifico.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            eventoCientifico.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEvento=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            //System.out.println("detalle Evento"+detalleEvento);
            String [] detalleEvento2=detalleEvento.split(", desde");
            eventoCientifico.setLugar(detalleEvento2[0].substring(1));
            //System.out.println("Ciudad"+detalleEvento2[0]);
            String [] fechas=detalleEvento2[1].split("- hasta");
            eventoCientifico.setFechaInicio(fechas[0].substring(1));
            //System.out.println("FechaInicio"+fechas[0]);
            eventoCientifico.setFechaFin(fechas[1].substring(1));
            //System.out.println("FechaFin"+fechas[1]);
            
            String detalleEventoP=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            eventoCientifico.setTipoParticipacion(detalleEventoP.split(",")[1].substring(25));
            
            eventosCientificos.add(eventoCientifico);
        }
        return eventosCientificos;
    }
    
     //no sé si es necesario scrapear nombre del proyecto
     private ArrayList<InformeInvestigacion> extraerInformesInvestigacion(Elements elements) {
        ArrayList<InformeInvestigacion> informesInvestigacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            InformeInvestigacion informeInvestigacion= new InformeInvestigacion();
            informeInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            informeInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleIfi=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleIfi.split(",")[0].substring(1,5);
            informeInvestigacion.setAno(Integer.parseInt(ano));
            informeInvestigacion.setProyecto(detalleIfi.split(",")[1].substring(28));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            informeInvestigacion.setAutores(autores);

            informesInvestigacion.add(informeInvestigacion);
        }
        return informesInvestigacion;
    }
     
     //formato fecha, no se si scrapear fecha fin y numero de participantes
     private ArrayList<RedConocimiento> extraerRedesConocimiento(Elements elements) {
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
     //espacio blanco medio de circulacion final
     //no sé si scrapear ambito y sitio web
     //formato fecha
     private ArrayList<GeneracionContenidoImpreso> extraerGeneracionContenidosImpresos(Elements elements) {
        ArrayList<GeneracionContenidoImpreso> contenidosImpresos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoImpreso contenidoImpreso = new GeneracionContenidoImpreso();
            contenidoImpreso.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            contenidoImpreso.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoImpreso=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            contenidoImpreso.setFecha(detalleContenidoImpreso.split(",")[0].substring(1));
            //tring ano=detalleEdicion.split(",")[1].substring(1,5);
            //contenidoImpreso.setAno(Integer.parseInt(ano));
            contenidoImpreso.setMedioCirculacion(detalleContenidoImpreso.split(",")[2].substring(23));
            
            String detalleContenidoImpreso2=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            contenidoImpreso.setLugarPublicacion(detalleContenidoImpreso2.split(",")[0].substring(22));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoImpreso.setAutores(autores);

            contenidosImpresos.add(contenidoImpreso);
        }
        return contenidosImpresos;
    }
     
     //No sé si scrapear país, idioma, sitio web, emisora
     //pendiente array de instituciones 
     private ArrayList<GeneracionContenidoMultimedia> extraerGeneracionContenidosMultimedia(Elements elements) {
        ArrayList<GeneracionContenidoMultimedia> contenidosMultimedia = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoMultimedia contenidoMultimedia = new GeneracionContenidoMultimedia();
            contenidoMultimedia.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            contenidoMultimedia.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoMultimedia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            String ano=detalleContenidoMultimedia.split(",")[0].substring(1,5);
            contenidoMultimedia.setAno(Integer.parseInt(ano));
            
            
            String detalleContenidoMultimedia2=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            contenidoMultimedia.setMedioCirculacion(detalleContenidoMultimedia2.split(",")[0].substring(23));
            
            //String detalleContenidoMultimedia3=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            //contenidoMultimedia.setInstituciones(detalleContenidoMultimedia3.split(",")[0].substring(22));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoMultimedia.setAutores(autores);

            contenidosMultimedia.add(contenidoMultimedia);
        }
        return contenidosMultimedia;
    }
     
     //pendiente array de instituciones 
     //formato fecha
     private ArrayList<GeneracionContenidoVirtual> extraerGeneracionContenidosVirtuales(Elements elements) {
        ArrayList<GeneracionContenidoVirtual> contenidosVirtuales = new ArrayList();
        for(int i=1;i<elements.size();i++){
            GeneracionContenidoVirtual contenidoVirtual= new GeneracionContenidoVirtual();
            contenidoVirtual.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            contenidoVirtual.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleContenidoVirtual=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            
            //String ano=detalleContenidoMultimedia.split(",")[0].substring(1,5);
            //contenidoVirtual.setAno(Integer.parseInt(ano));
            contenidoVirtual.setFecha(detalleContenidoVirtual.split(",")[0]);
            
           
            String[] datosAutores=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            contenidoVirtual.setAutores(autores);

            contenidosVirtuales.add(contenidoVirtual);
        }
        return contenidosVirtuales;
    }
     
     //No se si scrapear año fin
     //No sé si scrapear descripcion, espacio en blanco al final de descripcion
     //cambio de tipo a nombre
     private ArrayList<EstrategiaComunicacionConocimiento> extraerEstrategiasComunicacionConocimiento(Elements elements) {
        ArrayList<EstrategiaComunicacionConocimiento> EstrategiasComunicacionConocimiento = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EstrategiaComunicacionConocimiento comunicacionConocimiento= new EstrategiaComunicacionConocimiento();
            comunicacionConocimiento.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            //comunicacionConocimiento.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().split(": ").substring(3));
            String detalleComunicacionConocimiento=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
            
            String [] detalle1=detalleComunicacionConocimiento.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            comunicacionConocimiento.setAnoInicio(Integer.parseInt(detalle2[1]));
            
            String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            comunicacionConocimiento.setDescripcion(detalleEstrategia.substring(14));
     
            EstrategiasComunicacionConocimiento.add(comunicacionConocimiento);
        }
        return EstrategiasComunicacionConocimiento;
    }
     
     //No se si scrapear año fin
     //No sé si scrapear descripcion, espacio en blanco al final de descripcion
     //cambio de tipo a nombre
     private ArrayList<EstrategiaPedagogicaFomentoCTI> extraerEstrategiasPedagogicasFomentoCTI(Elements elements) {
        ArrayList<EstrategiaPedagogicaFomentoCTI> estrategiasPedagogicas = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EstrategiaPedagogicaFomentoCTI estrategiaPedagogica= new EstrategiaPedagogicaFomentoCTI();
            estrategiaPedagogica.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            //estrategiaPedagogica.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            String detalleEstrategiaPedagogica=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEstrategiaPedagogica.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            estrategiaPedagogica.setAnoInicio(Integer.parseInt(detalle2[1]));
            //System.out.println("FechaFin"+detalle2[1]);
            
            String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            estrategiaPedagogica.setDescripcion(detalleEstrategia.substring(14));

            estrategiasPedagogicas.add(estrategiaPedagogica);
        }
        return estrategiasPedagogicas;
    }
     
     //no se si scrapear fecha fin (no hay aparentemente) y descripcion
     //cambio de tipo a nombre
     private ArrayList<EspacioParticipacionCiudadana> extraerEspaciosParticipacionCiudadana(Elements elements) {
        ArrayList<EspacioParticipacionCiudadana> espaciosParticipacion = new ArrayList();
        for(int i=1;i<elements.size();i++){
            EspacioParticipacionCiudadana participacionCiudadana= new EspacioParticipacionCiudadana();
            participacionCiudadana.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
    
            String detalleEspacioParticipacion=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEspacioParticipacion.split("- hasta");
            String [] detalle2= detalle1[0].split(" ");
            String [] detalle3=detalle2[2].split("-");
            participacionCiudadana.setAnoInicio(Integer.parseInt(detalle3[0]));
            
            String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();

            espaciosParticipacion.add(participacionCiudadana);
        }
        return espaciosParticipacion;
    }
     
     private ArrayList<ParticipacionCiudadanaProyectoCTI> extraerParticipacionCiudadanaProyectosCTI(Elements elements) {
        ArrayList<ParticipacionCiudadanaProyectoCTI> participacionCiudadanaProyectos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            ParticipacionCiudadanaProyectoCTI participacionCiudadana= new ParticipacionCiudadanaProyectoCTI();
            participacionCiudadana.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            String detalleEstrategiaPedagogica=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleEstrategiaPedagogica.split("desde ");
            String [] detalle2= detalle1[1].split(" ");
            participacionCiudadana.setAnoInicio(Integer.parseInt(detalle2[1]));
            //System.out.println("FechaFin"+detalle2[1]);
            
            //String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            //estrategiaPedagogica.setDescripcion(detalleEstrategia.substring(14));

            participacionCiudadanaProyectos.add(participacionCiudadana);
        }
        return participacionCiudadanaProyectos;
    }
     
     //no se si scrapear fecha fin, participacion feria y nombre feria
//cambio de tipo a nombre
     private ArrayList<AsesoriaProgramaOndas> extraerAsesoriasProgramaOndas(Elements elements) {
        ArrayList<AsesoriaProgramaOndas> asesoriasProgramaOndas= new ArrayList();
        for(int i=1;i<elements.size();i++){
            AsesoriaProgramaOndas asesoriaProgramaOndas= new AsesoriaProgramaOndas();
            asesoriaProgramaOndas.setNombre(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            
            String detalleProgramaOndas=Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get();
      
            String [] detalle1=detalleProgramaOndas.split("en ");
            asesoriaProgramaOndas.setLugar(detalle1[1]);
            
            String detalleProgramaOndas1=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleOndas=detalleProgramaOndas1.split("- hasta");
            String [] detalleOndas2= detalleOndas[0].split(" ");
            String [] detalleOndas3=detalleOndas2[2].split("-");
            asesoriaProgramaOndas.setAnoInicio(Integer.parseInt(detalleOndas3[0]));
            
            String detalleProgramaOndasI=Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get();
            //String [] detalle2= detalle1[1].split(" ");
            asesoriaProgramaOndas.setInstitucion(detalleProgramaOndasI.split(": ")[1]);
            //System.out.println("FechaFin"+detalle1[1]);
            
            //String detalleEstrategia=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            //asesoriaProgramaOndas.setDescripcion(detalleEstrategia.substring(14));

            asesoriasProgramaOndas.add(asesoriaProgramaOndas);
        }
        return asesoriasProgramaOndas;
    }
     
     //No sé si scrapear tipo de orientacion, nombre estudiante, programa académico,número de páginas
     private ArrayList<TrabajoDirigido> extraerTrabajosDirigidos(Elements elements) {
        ArrayList<TrabajoDirigido> trabajosDirigidos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            TrabajoDirigido trabajoDirigido = new TrabajoDirigido();
            trabajoDirigido.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            trabajoDirigido.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            
            String detalleTrabajoDirigido=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleTrabajo1=detalleTrabajoDirigido.split(" hasta ");
            String [] detalleTrabajo2= detalleTrabajo1[1].split(" ");
            trabajoDirigido.setAnoFin(Integer.parseInt(detalleTrabajo2[1].substring(0,4)));
            
            String detalleTrabajoDirigido2=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            trabajoDirigido.setValoracion(detalleTrabajoDirigido2.split(",")[1].substring(13));
            trabajoDirigido.setInstitucion(detalleTrabajoDirigido2.split(",")[2].substring(14));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<Integrante>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            trabajoDirigido.setAutores(autores);

            trabajosDirigidos.add(trabajoDirigido);
        }
        return trabajosDirigidos;
    }
     
     //No sé si scrapear fecha fin ni como hacerlo
     //A veces no existe la línea de la fecha, es necesario un ciclo?
     private ArrayList<Proyecto> extraerProyectos(Elements elements) {
        ArrayList<Proyecto> proyectos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            Proyecto proyecto = new Proyecto();
            proyecto.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            proyecto.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(2));
            
            String detalleProyecto=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleProyecto1=detalleProyecto.split(" - ");
            String [] ano=detalleProyecto1[0].split("/");
            proyecto.setAnoInicio(Integer.parseInt(ano[0].substring(1)));
                    
            proyectos.add(proyecto);
        }
        return proyectos;
    }
}
