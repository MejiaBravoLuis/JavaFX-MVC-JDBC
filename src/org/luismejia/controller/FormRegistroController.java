/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.luismejia.dao.Conexion;
import org.luismejia.model.Empleado;
import org.luismejia.model.NivelAcceso;
import org.luismejia.system.Main;
import org.luismejia.utils.PasswordUtils;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class FormRegistroController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    Button btnRegresar, btnRegistro, btnEmpleado;
    @FXML
    TextField tfUsuario, tfContraseña;
    @FXML
    ComboBox cmbEmpleado, cmbNivelAcceso;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbEmpleado.setItems(listarEmpleados());
        cmbNivelAcceso.setItems(listarNivelesAcceso());
    }    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.formInicioView();
        }else if(event.getSource() == btnRegistro){
            agregarUsuario();
            SuperKinalAlert.getInstance().mostrarAlertasInformacion(400);
            stage.formInicioView();
        }else if(event.getSource() == btnEmpleado){
            
            stage.formEmpleadosView(3);
            
        }
    }
    
    public ObservableList<NivelAcceso> listarNivelesAcceso(){
        ArrayList<NivelAcceso> nivelesAcceso = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarNivelAcceso();";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                String nivelAcceso = resultSet.getString("nivelAcceso");
                
                nivelesAcceso.add(new NivelAcceso (nivelAccesoId, nivelAcceso));
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableArrayList(nivelesAcceso);
    }
    
    public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                Time horaDeEntrada = resultSet.getTime("horaDeEntrada");
                Time horaDeSalida = resultSet.getTime("horaDeSalida");
                String cargoId = resultSet.getString("cargo");
                String encargadoId = resultSet.getString("nombreEncargado");
            
                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaDeEntrada, horaDeSalida,cargoId,encargadoId));
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
        return FXCollections.observableList(empleados);
    }
    
    public void agregarUsuario(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarUsuario(?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUsuario.getText());
            statement.setString(2, PasswordUtils.getInstance().encryptedPassword(tfContraseña.getText()));
            statement.setInt(3, ((NivelAcceso)cmbNivelAcceso.getSelectionModel().getSelectedItem()).getNivelAccesoId());
            statement.setInt(4, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement !=null ){
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
    
    
}
