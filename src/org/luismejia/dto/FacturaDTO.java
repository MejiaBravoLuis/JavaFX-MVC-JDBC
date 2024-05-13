/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.dto;

import org.luismejia.model.Facturas;

/**
 *
 * @author Luis Mejia
 */
public class FacturaDTO {
    private static FacturaDTO instance;
    private Facturas facturas;
    
    private FacturaDTO(){
    
    }
    
    public static FacturaDTO getFacturaDTO(){
        if(instance == null){
            instance = new FacturaDTO();
        }
        
        return instance;
    }

    public Facturas getFactura() {
        return facturas;
    }

    public void setFactura(Facturas factura) {
        this.facturas = factura;
    }
}
