/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.productosInvestigacion.EventoCientifico;
import java.util.ArrayList;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorEventosCientificos {
    public static ArrayList<EventoCientifico> extraerEventosCientificos(Elements elements) {
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
}
