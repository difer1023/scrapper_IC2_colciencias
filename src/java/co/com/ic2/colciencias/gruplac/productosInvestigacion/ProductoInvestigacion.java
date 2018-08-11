/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.gruplac.productosInvestigacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa los atributos principales de un producto de investigación 
 * @author L
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ProductoInvestigacion",namespace = "productosInvestigacion.gruplac.colciencias.ic2.com.co")
public abstract class ProductoInvestigacion {
	
    @XmlElement(name = "fechaProducto")
    private String fechaProducto;
    @XmlElement(name = "subtipo")
    private String subtipo;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "categoria")
    private String categoria;
    @XmlElement(name = "clasificado")
    private boolean clasificado;
    @XmlElement(name = "codigo")
    private int codigo;
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
            
    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isClasificado() {
            return clasificado;
    }

    public void setClasificado(boolean clasificado) {
            this.clasificado = clasificado;
    }

    public String getFechaProducto() {
        return fechaProducto;
    }

    public void setFechaProducto(String fechaProducto) {
        this.fechaProducto = fechaProducto;
    }
}
