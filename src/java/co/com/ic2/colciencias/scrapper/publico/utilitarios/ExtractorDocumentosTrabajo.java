/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.DocumentoTrabajo;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorDocumentosTrabajo {
    public static ArrayList<DocumentoTrabajo> extraerDocumentosTrabajo(Elements elements) {
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
            ArrayList<Integrante> autores=new ArrayList<>();
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
}
