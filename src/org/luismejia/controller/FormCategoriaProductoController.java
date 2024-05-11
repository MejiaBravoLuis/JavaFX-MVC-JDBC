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
import org.luismejia.dto.CategoriaProductoDTO;
import org.luismejia.model.CategoriaProducto;
import org.luismejia.system.Main;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class FormCategoriaProductoController implements Initializable {
    private Main stage;
    private int op;
    
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    
    @FXML
    Button btnCancelar, btnGuardar;
    @FXML
    TextField tfCategoriaProductosId, tfNombreCategoriaProducto, tfDescripcionCategoria;
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto(null);
            stage.menuCategoriaProductoView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreCategoriaProducto.getText().equals("") && !tfDescripcionCategoria.getText().equals("")){
                    agregarCategoriaProducto();
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(400);
                    stage.menuCategoriaProductoView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(600);
                    if(tfNombreCategoriaProducto.getText().equals("")){
                        tfNombreCategoriaProducto.requestFocus();
                    }else if(tfDescripcionCategoria.getText().equals("")){
                        tfDescripcionCategoria.requestFocus();
                    }
                }
                
            }else if(op == 2){
                if(!tfNombreCategoriaProducto.getText().equals("") && !tfDescripcionCategoria.getText().equals("")){
                    editarCategoriaProducto();
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto(null);
                        SuperKinalAlert.getInstance().mostrarAlertasInformacion(500);
                        stage.menuCategoriaProductoView();
                    }else{
                        stage.menuCategoriaProductoView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(600);
                    if(tfNombreCategoriaProducto.getText().equals("")){
                        tfNombreCategoriaProducto.requestFocus();
                    }else if(tfDescripcionCategoria.getText().equals("")){
                        tfDescripcionCategoria.requestFocus();
                    }
                }
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        if(CategoriaProductoDTO.getCategoriaProductoDTO().getCategoriaProducto() != null){
            cargaDatos(CategoriaProductoDTO.getCategoriaProductoDTO().getCategoriaProducto());
        }
    }  
    
    public void cargaDatos(CategoriaProducto categoriaProducto){
        tfCategoriaProductosId.setText(Integer.toString(categoriaProducto.getCategoriaProductosId()));
        tfNombreCategoriaProducto.setText(categoriaProducto.getNombreCategoria());
        tfDescripcionCategoria.setText(categoriaProducto.getDescripcionCategoria());
    }
    
    public void agregarCategoriaProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCategoriasProducto(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreCategoriaProducto.getText());
            statement.setString(2, tfDescripcionCategoria.getText());
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
    
    public void editarCategoriaProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCategoriaProducto(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCategoriaProductosId.getText()));
            statement.setString(2, tfNombreCategoriaProducto.getText());
            statement.setString(3, tfDescripcionCategoria.getText());
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
