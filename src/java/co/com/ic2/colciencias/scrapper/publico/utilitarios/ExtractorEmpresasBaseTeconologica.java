/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.EmpresaBaseTecnologica;
import java.util.ArrayList;
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
}
