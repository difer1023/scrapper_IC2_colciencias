/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.scrapper.publico.utilitarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Difer
 */
public class Utilidades {
    public static String transformarMesANumero(String textoMes){
        System.out.println(textoMes);
        if(textoMes.equalsIgnoreCase("Enero")){return "1";}
        if(textoMes.equalsIgnoreCase("Febrero")){return "2";}
        if(textoMes.equalsIgnoreCase("Marzo")){return "3";}
        if(textoMes.equalsIgnoreCase("Abril")){return "4";}
        if(textoMes.equalsIgnoreCase("Mayo")){return "5";}
        if(textoMes.equalsIgnoreCase("Junio")){return "6";}
        if(textoMes.equalsIgnoreCase("Julio")){return "7";}
        if(textoMes.equalsIgnoreCase("Agosto")){return "8";}
        if(textoMes.equalsIgnoreCase("Septiembre")){return "9";}
        if(textoMes.equalsIgnoreCase("Octubre")){return "10";}
        if(textoMes.equalsIgnoreCase("Noviembre")){return "11";}
        if(textoMes.equalsIgnoreCase("Diciembre")){return "12";}
        
        return null;
    }
    public static String transformarFechaMesANumeroResumido(String textoFecha){
        
        String [] transformMes=textoFecha.split("-");

        if(transformMes[1].equalsIgnoreCase("ENE")){return transformMes[0]+"-01-"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("FEB")){return transformMes[0]+"-02"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("MAR")){return transformMes[0]+"-03"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("ABR")){return transformMes[0]+"-04"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("MAY")){return transformMes[0]+"-05"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("JUN")){return transformMes[0]+"-06"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("JUL")){return transformMes[0]+"-07"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("AGO")){return transformMes[0]+"-08"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("SEP")){return transformMes[0]+"-09"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("OCT")){return transformMes[0]+"-10"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("NOV")){return transformMes[0]+"-11"+transformMes[2];}
        if(transformMes[1].equalsIgnoreCase("DIC")){return transformMes[0]+"-12"+transformMes[2];}
        
        return null;
    }
    //"dd-MM-yyyy"
    public static String transformarFormatoFecha(String formatoOrigen,String formatoDestino,String fecha){
        
        try {
            SimpleDateFormat formatoOrigenTransformacion = new SimpleDateFormat(formatoOrigen);
            SimpleDateFormat formatoDestinoTransformacion = new SimpleDateFormat(formatoDestino);
            Date fechaTransformacion=formatoOrigenTransformacion.parse(fecha);
            return formatoDestinoTransformacion.format(fechaTransformacion);
            
        } catch (ParseException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
            return fecha;
        }
        
    }
    
    public static String getFechaActual() {
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(fechaActual);
    }
}
