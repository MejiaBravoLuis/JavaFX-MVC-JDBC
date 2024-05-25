/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.luismejia.system.Main;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class FormInicioController implements Initializable {
    private Main stage;
    
    @FXML
    TextField tfUsuario, tfContraseña;
    @FXML
    Button btnInicioS, btnRegistro;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnInicioS){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnRegistro){}
            
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
