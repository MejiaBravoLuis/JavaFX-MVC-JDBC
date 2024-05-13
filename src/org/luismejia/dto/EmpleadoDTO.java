/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.dto;

import org.luismejia.model.Empleado;

/**
 *
 * @author Luis Mejia
 */
public class EmpleadoDTO {
    private static EmpleadoDTO instance;
    private Empleado empleado;
    
    private EmpleadoDTO(){
    
    }
    
    public static EmpleadoDTO getEmpleadoDTO(){
        if(instance == null){
            instance = new EmpleadoDTO();
        }
        
        return instance;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
