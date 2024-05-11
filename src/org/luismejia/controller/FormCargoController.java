/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.luismejia.dao.Conexion;
import org.luismejia.dto.CargoDTO;
import org.luismejia.model.Cargo;
import org.luismejia.system.Main;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormCargoController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    
    @FXML
    TextField tfCargoId, tfNombreCargo, tfDescripcionCargo;
    @FXML
    Button btnGuardar, btnCancelar;
    @FXML
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            CargoDTO.getCargoDTO().setCargo(null);
            stage.menuCargoView();
        }else if(event.getSource() == btnGuardar){
                if (op == 1) {
                    if(!tfNombreCargo.getText().equals("") && !tfDescripcionCargo.getText().equals("")){
                        agregarCargo();
                        SuperKinalAlert.getInstance().mostrarAlertasInformacion(400);
                        stage.menuCargoView();
                    }else {
                        SuperKinalAlert.getInstance().mostrarAlertasInformacion(600);
                        tfNombreCargo.requestFocus();//Enfocar un textfield de forma dinámica
                    }
                    
                }else if (op == 2) {
                    if(!tfNombreCargo.getText().equals("") && !tfDescripcionCargo.getText().equals("")){
                        if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                            editarCargo();
                            CargoDTO.getCargoDTO().setCargo(null);
                            SuperKinalAlert.getInstance().mostrarAlertasInformacion(500);
                            stage.menuCargoView();
                        }else{
                            stage.menuCargoView();    
                        }
                    }
                    else{
                        SuperKinalAlert.getInstance().mostrarAlertasInformacion(600);
                        tfNombreCargo.requestFocus();
                }
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CargoDTO.getCargoDTO().getCargo() != null){
            cargaDatos(CargoDTO.getCargoDTO().getCargo());
        }
    }  
    
    public void cargaDatos(Cargo cargo){
    tfCargoId.setText(Integer.toString(cargo.getCargoId())) ;
    tfNombreCargo.setText(cargo.getNombreCargo());
    tfDescripcionCargo.setText(cargo.getDescripcionCargo());
  }
    
  public void agregarCargo(){
      try{
          conexion = Conexion.getInstance().obtenerConexion();
          String sql = "call sp_agregarCargo(?, ?)";
          statement = conexion.prepareStatement(sql);
          statement.setString(1, tfNombreCargo.getText());
          statement.setString(2, tfDescripcionCargo.getText());
          statement.execute();
      }catch(SQLException e){
          System.out.println(e.getMessage());
      }finally{
          try{
              if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
          }catch(SQLException e){
              System.out.println(e.getMessage());
          }
      }
  }  
  
  public void editarCargo(){
      try{
          conexion = Conexion.getInstance().obtenerConexion();
          String sql = "call sp_editarCargo(?, ?, ?);";
          statement = conexion.prepareStatement(sql);
          statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
          statement.setString(2, tfNombreCargo.getText());
          statement.setString(3, tfDescripcionCargo.getText());
          statement.execute();
      }catch(SQLException e){
          System.out.println(e.getMessage());
      }finally{
          try{
              if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
          }catch(SQLException e){
              System.out.println(e.getMessage());
          }
      }
  }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public void setOp(int op) {
        this.op = op;
    }


}
