/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.luismejia.dao.Conexion;
import org.luismejia.dto.CompraDTO;
import org.luismejia.model.Compra;
import org.luismejia.system.Main;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class FormComprasController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    Button btnCancelar, btnGuardar;
   
    @FXML
    TextField tfCompraId, tfFechaCompra;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CompraDTO.getCompraDTO().getCompra() != null){
            cargaDatos(CompraDTO.getCompraDTO().getCompra());
        }
    }    
    
    public void cargaDatos(Compra compra) {
    tfCompraId.setText(Integer.toString(compra.getCompraId()));

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
    tfFechaCompra.setText(formatoFecha.format(compra.getFechaCompra()));
}
 
    @FXML
    private void handleButtonAction(ActionEvent event) {
    if (event.getSource() == btnCancelar) {
        CompraDTO.getCompraDTO().setCompra(null);
        stage.menuComprasView();
    } else if (event.getSource() == btnGuardar) {
        
            stage.menuComprasView();
        if (op == 2) {
            if (SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK) {
                editarCompras();
                CompraDTO.getCompraDTO().setCompra(null);
                SuperKinalAlert.getInstance().mostrarAlertasInformacion(500);
                stage.menuComprasView();
            } else {
                stage.menuComprasView();
            }
        }
    }
}
    
    public void editarCompras(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_actualizarCompra(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCompraId.getText()));
            statement.setString(2,tfFechaCompra.getText());
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
