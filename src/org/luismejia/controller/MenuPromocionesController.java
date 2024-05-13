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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.luismejia.dao.Conexion;
import org.luismejia.dto.PromocionDTO;
import org.luismejia.model.Promocion;
import org.luismejia.system.Main;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class MenuPromocionesController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TableView tblPromociones;
    
    @FXML
    TableColumn colPromocionId, colPrecio, colDescripcion, colFechaInicio, colFechaFin, colProductoId;
    
    @FXML
    Button btnAtras, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    
    @FXML
    TextField tfPromocionId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargaDatos();
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnAtras){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formPromocionesView(1);
        }else if(event.getSource() == btnEditar){
            PromocionDTO.getPromocionDTO().setPromocion((Promocion)tblPromociones.getSelectionModel().getSelectedItem());
            stage.formPromocionesView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarPromocion(((Promocion)tblPromociones.getSelectionModel().getSelectedItem()).getPromocionId());
                cargaDatos();
            }
        }else if (event.getSource() == btnBuscar){
            tblPromociones.getItems().clear();
            if(tfPromocionId.getText().equals("")){
                cargaDatos();
            
            }else{
                op = 3;
                cargaDatos();
            }
        }
    }
    
    public void cargaDatos(){
        
        if(op == 3){
            tblPromociones.getItems().add(buscarPromocion());
            op = 0;
        }else{
            tblPromociones.setItems(listarPromociones()); 
        }
            colPromocionId.setCellValueFactory(new PropertyValueFactory<Promocion, Integer>("promocionId"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<Promocion, Double>("precioPromocion"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<Promocion, String>("descripcionPromocion"));
            colFechaInicio.setCellValueFactory(new PropertyValueFactory<Promocion, Date>("fechaInicio"));
            colFechaFin.setCellValueFactory(new PropertyValueFactory<Promocion, Date>("fechaFinalizacion"));
            colProductoId.setCellValueFactory(new PropertyValueFactory<Promocion, String>("producto"));;
     
    }
    
    public ObservableList<Promocion> listarPromociones(){
        ArrayList<Promocion> promociones = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarPromociones()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int promocionId = resultSet.getInt("promocionesId");
                Double precioProm = resultSet.getDouble("precioPromocion");
                String descripcionProm = resultSet.getString("descripcionPromocion");
                Date fechaI = resultSet.getDate("fechaInicio");
                Date fechaF = resultSet.getDate("fechaFinalizacion");
                String productosId = resultSet.getString("Producto");
            
                promociones.add(new Promocion(promocionId,precioProm,descripcionProm,fechaI,fechaF,productosId));
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
        
        
        return FXCollections.observableList(promociones);
    }
    
    public void eliminarPromocion(int promId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarPromocion(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,promId);
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
    
     public Promocion buscarPromocion(){
        Promocion promocion = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarPromocion(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfPromocionId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int promocionesId = resultSet.getInt("promocionesId");
                Double precioProm = resultSet.getDouble("precioPromocion");
                String descripcionProm = resultSet.getString("descripcionPromocion");
                Date fechaI = resultSet.getDate("fechaInicio");
                Date fechaF = resultSet.getDate("fechaFinalizacion");
                String productosId = resultSet.getString("Producto");
            
                promocion = new Promocion(promocionesId, precioProm, descripcionProm, fechaI, fechaF, productosId);

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
        return promocion;
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
