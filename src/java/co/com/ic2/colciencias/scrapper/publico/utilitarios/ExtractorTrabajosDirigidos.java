/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.TrabajoDirigido;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorTrabajosDirigidos {
    public static ArrayList<TrabajoDirigido> extraerTrabajosDirigidos(Elements elements) {
        ArrayList<TrabajoDirigido> trabajosDirigidos = new ArrayList();
        for(int i=1;i<elements.size();i++){
            TrabajoDirigido trabajoDirigido = new TrabajoDirigido();
            trabajoDirigido.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            if(!trabajoDirigido.getTipo().equalsIgnoreCase("Trabajos dirigidos/Tutorías de otro tipo")){
            trabajoDirigido.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(3));
            System.out.println("tipo "+trabajoDirigido.getTipo());
            String detalleTrabajoDirigido=Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            String [] detalleTrabajo1=detalleTrabajoDirigido.split(" hasta ");
            String [] detalleTrabajo2= detalleTrabajo1[1].split(",");
            String [] detalleTrabajo3= detalleTrabajo2[0].split(" ");
            
            System.out.println("DETALLETRABAJODIRIGIDO "+detalleTrabajoDirigido);
            System.out.println("DETALLETRABAJO2 "+detalleTrabajo2[1]);
            System.out.println("DETALLETRABAJO3 "+detalleTrabajo3[0]);
            
            if(detalleTrabajo3.length>=2){
                System.out.println("DETALLETRABAJO3 "+detalleTrabajo3[1]);
            trabajoDirigido.setAnoFin(Integer.parseInt(detalleTrabajo3[1]));
            }
            String detalleTrabajoDirigido2=Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get();
            trabajoDirigido.setValoracion(detalleTrabajoDirigido2.split(",")[1].substring(13));
            trabajoDirigido.setInstitucion(detalleTrabajoDirigido2.split(",")[2].substring(14));
            
            String[] datosAutores=Xsoup.compile("/td[2]/text(6)").evaluate(elements.get(i)).get().substring(9).split(",");
            ArrayList<Integrante> autores=new ArrayList<>();
            for(int k=0;k<datosAutores.length-1;k++){
                Integrante autor=new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            trabajoDirigido.setAutores(autores);

            trabajosDirigidos.add(trabajoDirigido);
            }
        }
        return trabajosDirigidos;
    }
}
