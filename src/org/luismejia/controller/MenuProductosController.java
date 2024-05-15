/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.luismejia.dto.ProductoDTO;
import org.luismejia.model.Producto;
import org.luismejia.system.Main;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class MenuProductosController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblProductos;
    
    @FXML
    TableColumn colProductosId, colNombreProducto, 
            colDescripcionProducto, colCantidadStock,
            colPrecioVentaUnitario,colPrecioVentaMayor, 
            colPrecioCompra, colImagen, colDistribuidorId, 
            colCategoriaProductoId;
    
    @FXML
    Button btnAtras, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    
    @FXML
    TextField tfProductoId;
    
    @FXML 
    private void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAtras){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formProductosView(1);
        }else if(event.getSource() == btnEditar){
            ProductoDTO.getProductoDTO().setProducto((Producto)tblProductos.getSelectionModel().getSelectedItem());
            stage.formProductosView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarProducto(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getProductosId());
                cargaDatos();
            }
        }else if (event.getSource() == btnBuscar){
            tblProductos.getItems().clear();
            if(tfProductoId.getText().equals("")){
                cargaDatos();
            
            }else{
                op = 3;
                cargaDatos();
            }
        }
    }
    
    public void cargaDatos(){
        if(op == 3){
            tblProductos.getItems().add(buscarProducto());
            op = 0;
        }else{
            tblProductos.setItems(listarProductos()); 
        }
            colProductosId.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("productosId"));
            colNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
            colDescripcionProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcionProducto"));
            colCantidadStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidadStock"));
            colPrecioVentaUnitario.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioVentaUnitario"));
            colPrecioVentaMayor.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioVentaMayor"));
            colPrecioCompra.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioCompra"));
            colImagen.setCellValueFactory(new PropertyValueFactory<Producto, Blob>("imagen"));
            colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Producto, String>("distribuidor"));
            colCategoriaProductoId.setCellValueFactory(new PropertyValueFactory<Producto, String>("CategoriaProducto"));
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargaDatos();
    } 
    
    public ObservableList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " call sp_listarProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int productosId = resultSet.getInt("productosId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadStock = resultSet.getInt("cantidadStock");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagen = resultSet.getBlob("imagen");
                String distribuidor = resultSet.getString("distribuidor");
                String categoria = resultSet.getString("categoria");
            
                productos.add(new Producto(productosId, nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagen, distribuidor, categoria));
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
        
        
        return FXCollections.observableList(productos);
    }
    
    public void eliminarProducto(int prodId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,prodId);
            statement.execute();
            
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
    }
    
    public Producto buscarProducto(){
        Producto producto = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfProductoId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                 int productosId = resultSet.getInt("productosId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadStock = resultSet.getInt("cantidadStock");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagen = resultSet.getBlob("imagen");
                String distribuidor = resultSet.getString("distribuidor");
                String categoria = resultSet.getString("categoria");
            
                producto = new Producto(productosId, nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagen, distribuidor, categoria);

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
        return producto;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }  
}
