/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author informatica
 */
public class Conexion {
    private static Conexion instance;
    private String jdburl = "jdbc:mysql://localhost:3306/SuperKinalDB?serverTimezone=GMT-6&useSSL=false";
    private String user = "root";
    private String password = "admin";
    
    
    private Conexion(){ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Conexion getInstance(){
        if(instance == null){
            instance = new Conexion();
        }
        return instance;
    }
    
    public Connection obtenerConexion()throws SQLException{
        return DriverManager.getConnection(jdburl, user,password);
    }
    
}
