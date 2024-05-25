/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.luismejia.dao.Conexion;
import org.luismejia.dto.ProductoDTO;
import org.luismejia.model.CategoriaProducto;
import org.luismejia.model.Distribuidor;
import org.luismejia.model.Producto;
import org.luismejia.system.Main;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class FormProductosController implements Initializable {
    private Main stage;
    private int op;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    private List<File> files = null;
    
    @FXML
    Button btnCancelar,btnGuardar;
   
    @FXML
    TextField tfProductoId,tfNombreProducto,
            tfDescripcionProducto,tfCantidadStock,
            tfPrecioVentaU,tfPrecioVentaM,tfPrecioCompra;

    @FXML
    ComboBox cmbDistribuidores,cmbCategoriasProductos;
    
    @FXML
    ImageView Cargarimg, Mostrarimg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ProductoDTO.getProductoDTO().getProducto() != null){
            cargaDatos(ProductoDTO.getProductoDTO().getProducto());
        }
        cmbDistribuidores.setItems(listarDistribuidores());
        cmbCategoriasProductos.setItems(listarCategoriaProductos());
        
    }
    
    public void cargaDatos(Producto producto) {
        try {
            tfProductoId.setText(Integer.toString(producto.getProductosId()));
            tfNombreProducto.setText(producto.getNombreProducto());
            tfDescripcionProducto.setText(producto.getDescripcionProducto());
            tfCantidadStock.setText(Integer.toString(producto.getCantidadStock()));
            tfPrecioVentaU.setText(Double.toString(producto.getPrecioVentaUnitario()));
            tfPrecioVentaM.setText(Double.toString(producto.getPrecioVentaMayor()));
            tfPrecioCompra.setText(Double.toString(producto.getPrecioCompra()));

            if (producto.getImagenProducto() != null) {
                InputStream file = producto.getImagenProducto().getBinaryStream();
                Image image = new Image(file);
                Mostrarimg.setImage(image); 
            } else {
                Mostrarimg.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnCancelar){
            ProductoDTO.getProductoDTO().setProducto(null);
            stage.menuProductosView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreProducto.getText().equals("") && !tfCantidadStock.getText().equals("") && !tfPrecioVentaU.getText().equals("") && !tfPrecioVentaM.getText().equals("") && !tfPrecioCompra.getText().equals("")){
                    agregarProducto();
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(400);
                    stage.menuProductosView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(600);
                    if(tfNombreProducto.getText().equals("")){
                        tfNombreProducto.requestFocus();
                    }else if(tfCantidadStock.getText().equals("")){
                        tfCantidadStock.requestFocus();
                    }else if(tfPrecioVentaU.getText().equals("")){
                        tfPrecioVentaU.requestFocus();
                    }else if(tfPrecioVentaM.getText().equals("")){
                        tfPrecioVentaM.requestFocus();
                    }else if(tfPrecioCompra.getText().equals("")){
                        tfPrecioCompra.requestFocus();
                    }
                }

            }else if(op == 2){
                if(!tfNombreProducto.getText().equals("") && !tfCantidadStock.getText().equals("") && !tfPrecioVentaU.getText().equals("") && !tfPrecioVentaM.getText().equals("") && !tfPrecioCompra.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarProducto();
                        ProductoDTO.getProductoDTO().setProducto(null);
                        SuperKinalAlert.getInstance().mostrarAlertasInformacion(500);
                        stage.menuProductosView();
                    }else{
                        stage.menuProductosView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(33);
                    if(tfNombreProducto.getText().equals("")){
                        tfNombreProducto.requestFocus();
                    }else if(tfCantidadStock.getText().equals("")){
                        tfCantidadStock.requestFocus();
                    }else if(tfPrecioVentaU.getText().equals("")){
                        tfPrecioVentaU.requestFocus();
                    }else if(tfPrecioVentaM.getText().equals("")){
                        tfPrecioVentaM.requestFocus();
                    }else if(tfPrecioCompra.getText().equals("")){
                        tfPrecioCompra.requestFocus();
                    }
                }
                
            }
        }
    }
    
    @FXML
    public void handleOnDrop(DragEvent event){
        try{
            files = event.getDragboard().getFiles();
            FileInputStream file = new FileInputStream(files.get(0));
            Image image = new Image(file);
            Cargarimg.setImage(image);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleOnDrag(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    public void agregarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarProducto(?,?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreProducto.getText());
            statement.setString(2, tfDescripcionProducto.getText());
            statement.setString(3, tfCantidadStock.getText());
            statement.setString(4, tfPrecioVentaU.getText());
            statement.setString(5, tfPrecioVentaM.getText());
            statement.setString(6, tfPrecioCompra.getText());

            if (files != null && !files.isEmpty() && files.get(0) != null) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(7, img);
            } else {
                statement.setBinaryStream(7, null); 
            }
            statement.setInt(8,((Distribuidor)cmbDistribuidores.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(9,((CategoriaProducto)cmbCategoriasProductos.getSelectionModel().getSelectedItem()).getCategoriaProductosId());
            statement.execute();
        }catch(Exception e){
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
    
    public void editarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarProducto(?,?,?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));
            statement.setString(2, tfNombreProducto.getText());
            statement.setString(3, tfDescripcionProducto.getText());
            statement.setString(4, tfCantidadStock.getText());
            statement.setString(5, tfPrecioVentaU.getText());
            statement.setString(6, tfPrecioVentaM.getText());
            statement.setString(7, tfPrecioCompra.getText());
            if (files != null && !files.isEmpty() && files.get(0) != null) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(8, img);
            } else {
                statement.setBinaryStream(8, null); 
            }
            statement.setInt(9,((Distribuidor)cmbDistribuidores.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(10,((CategoriaProducto)cmbCategoriasProductos.getSelectionModel().getSelectedItem()).getCategoriaProductosId());
            statement.execute();
        }catch(Exception e){
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
    
     public ObservableList<Distribuidor> listarDistribuidores(){
        ArrayList<Distribuidor> distribuidores = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarDistribuidores()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("nitDistribuidor");
                String telefonoDistribuidor = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
            
                distribuidores.add(new Distribuidor(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                
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
        
        
        return FXCollections.observableList(distribuidores);
    }
     
    public ObservableList<CategoriaProducto> listarCategoriaProductos(){
        ArrayList<CategoriaProducto> categoriaProductos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarCategoriasProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int categoriaPId = resultSet.getInt("categoriaproductosId");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
            
                categoriaProductos.add(new CategoriaProducto(categoriaPId, nombreCategoria, descripcionCategoria));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                
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
        
        
        return FXCollections.observableList(categoriaProductos);
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
