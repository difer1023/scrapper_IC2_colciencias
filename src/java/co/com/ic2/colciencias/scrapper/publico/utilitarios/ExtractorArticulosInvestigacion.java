/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import co.com.ic2.colciencias.gruplac.Integrante;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.ArticuloInvestigacion;
import co.com.ic2.colciencias.gruplac.productosInvestigacion.OtroArticuloPublicado;
import co.com.ic2.colciencias.scrapper.publico.ScraperPublico2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Difer
 */
public class ExtractorArticulosInvestigacion {

    public static ArrayList<ArticuloInvestigacion> extraerArticulosPublicados(Elements elements) {
        ArrayList<ArticuloInvestigacion> articulosPublicados = new ArrayList();
        for (int i = 2; i < elements.size(); i++) {

            ArticuloInvestigacion articuloInvestigacion = new ArticuloInvestigacion();
            articuloInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            articuloInvestigacion.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(1));

            String detalleRevista = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            articuloInvestigacion.setPais(detalleRevista.split(",")[0].substring(1));
            String nombreRevista = detalleRevista.split(",")[1].split("ISSN:")[0];
            String[] arrayISSN = detalleRevista.split(",")[1].split("ISSN:");
            String ano = detalleRevista.split(",")[2].substring(1, 5);
            System.out.println("detalle" + detalleRevista);
            if (arrayISSN.length < 2) {
                for (int j = 2; arrayISSN.length < 2; j++) {
                    arrayISSN = detalleRevista.split(",")[j].split("ISSN:");
                    if (arrayISSN.length < 2) {
                        nombreRevista += detalleRevista.split(",")[j];

                    } else {
                        nombreRevista += detalleRevista.split(",")[j].split("ISSN:")[0];
                        ano = detalleRevista.split(",")[j + 1].substring(1, 5);
                    }
                }
            }
            articuloInvestigacion.setRevista(nombreRevista.substring(1, nombreRevista.length() - 1));
            articuloInvestigacion.setIssn(arrayISSN[1].substring(1));
            articuloInvestigacion.setAno(Integer.parseInt(ano));
            ArrayList<Integrante> autores = new ArrayList<>();
            String[] datosAutores = Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Integrante autor = new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            articuloInvestigacion.setAutores(autores);
            articulosPublicados.add(articuloInvestigacion);
        }
        return articulosPublicados;
    }

    public static ArrayList<OtroArticuloPublicado> extraerOtrosArticulosPublicados(Elements elements) {
        ArrayList<OtroArticuloPublicado> otrosArticulosPublicados = new ArrayList();
        for (int i = 2; i < elements.size(); i++) {

            OtroArticuloPublicado OtroArticuloPublicado = new OtroArticuloPublicado();
            OtroArticuloPublicado.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
            OtroArticuloPublicado.setNombre(Xsoup.compile("/td[2]/text(2)").evaluate(elements.get(i)).get().substring(1));

            String detalleRevista = Xsoup.compile("/td[2]/text(3)").evaluate(elements.get(i)).get();
            OtroArticuloPublicado.setPais(detalleRevista.split(",")[0].substring(1));
            String nombreRevista = detalleRevista.split(",")[1].split("ISSN:")[0];
            String[] arrayISSN = detalleRevista.split(",")[1].split("ISSN:");
            String ano = detalleRevista.split(",")[2].substring(1, 5);
            System.out.println("detalle" + detalleRevista);
            if (arrayISSN.length < 2) {
                for (int j = 2; arrayISSN.length < 2; j++) {
                    arrayISSN = detalleRevista.split(",")[j].split("ISSN:");
                    if (arrayISSN.length < 2) {
                        nombreRevista += detalleRevista.split(",")[j];

                    } else {
                        nombreRevista += detalleRevista.split(",")[j].split("ISSN:")[0];
                        ano = detalleRevista.split(",")[j + 1].substring(1, 5);
                    }
                }
            }

            System.out.println("REVISTA" + nombreRevista);
            if (!nombreRevista.equals(" ")) {
                OtroArticuloPublicado.setRevista(nombreRevista.substring(1, nombreRevista.length() - 1));
            }
            OtroArticuloPublicado.setIssn(arrayISSN[1].substring(1));
            OtroArticuloPublicado.setAno(Integer.parseInt(ano));
            ArrayList<Integrante> autores = new ArrayList<>();
            String[] datosAutores = Xsoup.compile("/td[2]/text(4)").evaluate(elements.get(i)).get().substring(9).split(",");
            for (int k = 0; k < datosAutores.length - 1; k++) {
                Integrante autor = new Integrante();
                autor.setNombreCompleto(datosAutores[k].substring(1));
                autores.add(autor);
            }
            OtroArticuloPublicado.setAutores(autores);
            otrosArticulosPublicados.add(OtroArticuloPublicado);
        }
        return otrosArticulosPublicados;
    }

    public static ArrayList<ArticuloInvestigacion> extraerArticulosPublicadosPrivado(ArrayList<Elements> arrayElements,HashMap<String,String> cookie) {
        ArrayList<ArticuloInvestigacion> articulosPublicados = new ArrayList();
        for (Elements elements : arrayElements) {
            for (int i = 0; i < elements.size(); i++) {

                ArticuloInvestigacion articuloInvestigacion = new ArticuloInvestigacion();
                System.out.println("FILA"+ elements.get(i).text());
//                articuloInvestigacion.setTipo(Xsoup.compile("/td[2]/strong[1]/text()").evaluate(elements.get(i)).get());
                articuloInvestigacion.setNombre(Xsoup.compile("/td[2]/text()").evaluate(elements.get(i)).get());
              String ano = Xsoup.compile("/td[3]/text()").evaluate(elements.get(i)).get();
              articuloInvestigacion.setAno(Integer.parseInt(ano));
              
              String enlaceDetalle=Xsoup.compile("/td[3]/a/@href").evaluate(elements.get(i)).get();
//                
                Document doc = null;
            try {
                doc = Jsoup.connect(enlaceDetalle).get();
            } catch (IOException ex) {
                Logger.getLogger(ScraperPublico2.class.getName()).log(Level.SEVERE, null, ex);
            }
//                
//                
                articuloInvestigacion.setRevista(Xsoup.compile("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[3]/"
                        + "table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[2]/td[3]/text()").evaluate(doc).get());
//                articuloInvestigacion.setIssn(arrayISSN[1].substring(1));
                
//                ArrayList<Integrante> autores = new ArrayList<>();
//                String[] datosAutores = Xsoup.compile("/td[2]/text(5)").evaluate(elements.get(i)).get().substring(9).split(",");
//                for (int k = 0; k < datosAutores.length - 1; k++) {
//                    Integrante autor = new Integrante();
//                    autor.setNombreCompleto(datosAutores[k].substring(1));
//                    autores.add(autor);
//                }
//                articuloInvestigacion.setAutores(autores);
//                articulosPublicados.add(articuloInvestigacion);
            }
        }
        return articulosPublicados;
    }
}
