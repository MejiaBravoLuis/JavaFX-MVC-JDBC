/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.dto;
import org.luismejia.model.Cargo;
/**
 *
 * @author informatica
 */
public class CargoDTO {
    
    private static CargoDTO instance;
    private Cargo cargo;
    
    
    private CargoDTO(){
    
    }
    
    public static CargoDTO getCargoDTO(){
        if(instance == null){
            instance = new CargoDTO();
        }
        return instance;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "CargoDTO{" + "cargo=" + cargo + '}';
    }

    
    
}
