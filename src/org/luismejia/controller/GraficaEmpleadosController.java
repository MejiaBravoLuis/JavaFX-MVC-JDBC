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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.luismejia.dao.Conexion;
import org.luismejia.model.Empleado;
import org.luismejia.system.Main;

/**
 *
 * @author Luis Mejia
 */
public class GraficaEmpleadosController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion;
    private PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    BarChart<String, Number> grfEmpleados;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Empleado> empleados = listarEmpleados();
        
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        grfEmpleados.setTitle("Gráfica de empleados");
        xAxis.setLabel("Nombre del Empleado");
        yAxis.setLabel("Sueldo");
        
        grfEmpleados.getData().clear();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Random rand = new Random();
        for(Empleado empleado : empleados){
            XYChart.Data<String, Number> data = new XYChart.Data<>(empleado.getNombreEmpleado(), empleado.getSueldo());
        String color = String.format("#%06x", rand.nextInt(0xffffff + 1) );
        data.nodeProperty().addListener((obs, oldNode, newNode)  ->{
            if(newNode != null){
                newNode.setStyle("-fx-bar-fill: " + color + ";");
                }
            });
        series.getData().add(data);
        }
        grfEmpleados.getData().add(series);
    }
    
    public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " call sp_listarEmpleados()";
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
    
    private Empleado encontrarEmpeladoConMayorSueldo(ObservableList<Empleado> empleados){
        Empleado empleadoConMayorSueldo = null;
        double sueldoMasAlto = Double.MIN_VALUE;
        
        for(Empleado empleado : empleados){
            if(empleado.getSueldo() > sueldoMasAlto){
                sueldoMasAlto = empleado.getSueldo();
            }
        }
        return empleadoConMayorSueldo;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

}
