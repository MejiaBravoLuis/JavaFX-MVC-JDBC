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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.luismejia.dao.Conexion;
import org.luismejia.model.Usuario;
import org.luismejia.system.Main;
import org.luismejia.utils.PasswordUtils;
import org.luismejia.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis Mejia
 */
public class FormInicioController implements Initializable {
    private Main stage;
    private int op = 0;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TextField tfUsuario;
    @FXML
    PasswordField tfContraseña;
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
            Usuario usuario = buscarUsuario();
            if(op == 0){
                if(usuario != null){
                    if(PasswordUtils.getInstance().checkPassword(tfContraseña.getText(), usuario.getContrasenia())){
                        SuperKinalAlert.getInstance().alertaSaludo(usuario.getUsuario());
                        if(usuario.getNivelAccesoId() == 1){
                            btnRegistro.setDisable(false);
                            btnInicioS.setText("Ir al menú principal");
                            op = 1;
                        }else if(usuario.getNivelAccesoId() == 2){
                            stage.menuPrincipalView();
                        }
                    }else{
                        SuperKinalAlert.getInstance().mostrarAlertasInformacion(701);
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertasInformacion(602);
                }
            }else{
                stage.menuPrincipalView();
            }
  
        }else if(event.getSource() == btnRegistro){
            stage.formRegistroView();
        }
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public Usuario buscarUsuario(){
        Usuario usuario = null;
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarUsuario(?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUsuario.getText());
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int usuarioId = resultSet.getInt("usuarioId");
                String user = resultSet.getString("usuario");
                String contrasenia = resultSet.getString("contrasenia");
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                int empleadoId = resultSet.getInt("empleadoId");
                
                usuario = new Usuario(usuarioId, user, contrasenia, nivelAccesoId, empleadoId);
            }
            
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
                if(resultSet != null){
                    resultSet.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return usuario;
    }
    
}
