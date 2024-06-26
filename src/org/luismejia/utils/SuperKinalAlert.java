/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Luis Mejia
 */
public class SuperKinalAlert {
    private static SuperKinalAlert instance;
    
    private SuperKinalAlert(){
    
    }
    
    public static SuperKinalAlert getInstance(){
        if(instance == null){
            instance = new SuperKinalAlert();
        }
        return instance;
    }
    public void mostrarAlertasInformacion(int code){
        if(code == 400){ //COidgo 400 sirve para confirmación de registros.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación de registro");
            alert.setHeaderText("Confirmación de registro");
            alert.setContentText("¡Registro realizado con éxito!");
            alert.showAndWait();
        }else if(code == 500){ // COidgo 500 sirve para confirmación de registros.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¡Edición realizada con éxito!");
            alert.showAndWait();
        }else if(code == 600){// Código 600 sirve para alerta de campos pendientes.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Pendientes");
            alert.setHeaderText("Faltan algunos campos que neceistan ser llenados");
            alert.setContentText("Algunos campos necesarios para el registro están vacios");
            alert.showAndWait();
        }else if(code == 602){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario Incorrecto brotastic");
            alert.setHeaderText("El usuario que intentas ingresar parece que no existe");
            alert.setContentText("Verifique que su usuario sea correcto e intente nuevamente");
            alert.showAndWait();
        }else if(code == 701){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Contraseña Incorrecta bro");
            alert.setHeaderText("La contraseña que ingresaste es incorrecta");
            alert.setContentText("Verifique que la contraseña");
            alert.showAndWait();
        }
    }
    
    public Optional<ButtonType> mostrarAlertaConfirmacion(int code){
        Optional<ButtonType> action = null;
        if(code == 404){ //Codigo 404 sirve para confirmación de un registro
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminación Registro");
            alert.setHeaderText("Eliminación Registro");
            alert.setContentText("¿Estas seguro de elimnar el registro?");
            action = alert.showAndWait();
        }else if(code == 505){ // Código 505 sirve para confirmar la edición del registro
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¿Desea confirmar la edición del registro?");
            action = alert.showAndWait();
        }
        
        return action;

    }
    
    public void alertaSaludo(String usuario){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Nuevo inicio de sesión!");
        alert.setHeaderText("Bienvenido de vuelta " + usuario);
        alert.setContentText("¿Como te va ?, ¿suerte en tu día!");
        alert.showAndWait();
    }
    
    public void alertaDespedida(String usuario){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cerrar sesión");
        alert.setHeaderText("Cerrando sesión para: " + usuario);
        alert.setContentText("¡Nos vemos!");
        alert.show();
    }
    
}

