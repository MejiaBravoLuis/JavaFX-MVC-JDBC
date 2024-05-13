/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.dto;

import org.luismejia.model.Producto;

/**
 *
 * @author Luis Mejia
 */
public class ProductoDTO {
    private static ProductoDTO instance;
    private Producto producto;
    
    private ProductoDTO(){
    
    }
    
    public static ProductoDTO getProductoDTO(){
        if(instance == null){
            instance = new ProductoDTO();
        }
        
        return instance;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
