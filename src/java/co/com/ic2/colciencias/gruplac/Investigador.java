/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author L
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Investigador", namespace = "gruplac.colciencias.ic2.com.co")
public class Investigador {

    @XmlElement(name = "nombreCompleto")
    private String nombreCompleto;
    @XmlElement(name = "horasDedicacion")
    private String horasDedicacion;
    @XmlElement(name = "inicioVinculacion")
    private String inicioVinculacion;
    @XmlElement(name = "finVinculacion")
    private String finVinculacion;
    @XmlElement(name = "categoria")
    private String categoria;
    @XmlElement(name = "vigenciaCategoria")
    private String vigenciaCategoria;
    @XmlElement(name = "lider")
    private boolean lider;
    @XmlElement(name = "integranteGrupo")
    private int integranteGrupo;
    @XmlElement(name = "gruposColaboracion")
    private int gruposColaboracion;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getHorasDedicacion() {
        return horasDedicacion;
    }

    public void setHorasDedicacion(String horasDedicacion) {
        this.horasDedicacion = horasDedicacion;
    }

    public String getInicioVinculacion() {
        return inicioVinculacion;
    }

    public void setInicioVinculacion(String inicioVinculacion) {
        this.inicioVinculacion = inicioVinculacion;
    }

    public String getFinVinculacion() {
        return finVinculacion;
    }

    public void setFinVinculacion(String finVinculacion) {
        this.finVinculacion = finVinculacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getVigenciaCategoria() {
        return vigenciaCategoria;
    }

    public void setVigenciaCategoria(String vigenciaCategoria) {
        this.vigenciaCategoria = vigenciaCategoria;
    }

    public boolean isLider() {
        return lider;
    }

    public void setLider(boolean lider) {
        this.lider = lider;
    }

    public int getIntegranteGrupo() {
        return integranteGrupo;
    }

    public void setIntegranteGrupo(int integranteGrupo) {
        this.integranteGrupo = integranteGrupo;
    }

    public int getGruposColaboracion() {
        return gruposColaboracion;
    }

    public void setGruposColaboracion(int gruposColaboracion) {
        this.gruposColaboracion = gruposColaboracion;
    }

    
}
