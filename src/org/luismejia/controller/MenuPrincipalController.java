/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import org.luismejia.system.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
/**
 *
 * @author Luis Mejia
 */
public class MenuPrincipalController implements Initializable{
    private Main stage;
    @FXML
    MenuItem btnClientes, btnTicketSoporte, btnCargos, btnCategoria, btnDistribuidores, btnEmpleados, btnProductos, btnPromociones, btnCompras, btnFacturas;
    
    @FXML
    public void handleButtonAction(ActionEvent event)throws Exception{
        if(event.getSource() == btnClientes){
            stage.menuClienteView();
        }else if(event.getSource() == btnTicketSoporte){
            stage.menuTicketSoporteView();
        }else if(event.getSource() == btnCargos){
            stage.menuCargoView();
        }else if(event.getSource() == btnCategoria){
            stage.menuCategoriaProductoView();
        }else if(event.getSource() == btnDistribuidores){
            stage.menuDistribuidorView();
        }else if(event.getSource() == btnEmpleados){
            stage.menuEmpleadosView();
        }else if(event.getSource() == btnProductos){
            stage.menuProductosView();
        }else if(event.getSource() == btnPromociones){
            stage.menuPromocionesView();
        }else if(event.getSource() == btnCompras){
            stage.menuComprasView();
        }else if(event.getSource() == btnFacturas){
            stage.menuFacturasView();
        }
    }
    

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources){
    
    }
  
}