/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.luismejia.controller.FormAsignarEncargadoController;
import org.luismejia.controller.FormCargoController;
import org.luismejia.controller.FormCategoriaProductoController;
import org.luismejia.controller.FormClienteController;
import org.luismejia.controller.FormComprasController;
import org.luismejia.controller.FormDetalleCompraController;
import org.luismejia.controller.FormDetalleFacturasController;
import org.luismejia.controller.FormDistribuidoresController;
import org.luismejia.controller.FormEmpleadosController;
import org.luismejia.controller.FormFacturasController;
import org.luismejia.controller.FormInicioController;
import org.luismejia.controller.FormProductosController;
import org.luismejia.controller.FormPromocionesController;
import org.luismejia.controller.FormRegistroController;
import org.luismejia.controller.MenuCargoController;
import org.luismejia.controller.MenuCategoriaProductosController;
import org.luismejia.controller.MenuClienteController;
import org.luismejia.controller.MenuComprasController;
import org.luismejia.controller.MenuDistribuidorController;
import org.luismejia.controller.MenuEmpleadosController;
import org.luismejia.controller.MenuFacturasController;
import org.luismejia.controller.MenuPrincipalController;
import org.luismejia.controller.MenuProductosController;
import org.luismejia.controller.MenuPromocionesController;
import org.luismejia.controller.MenuTicketSoporteController;

/**
 *
 * @author Luis Mejia
 */
public class Main extends Application {
    private final String URLVIEW = "/org/luismejia/view/";
    private Stage stage;
    private Scene scene;
    private int op;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Kinal APP -------- 2020266");
        formInicioView();
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName,int width, int heigth)throws Exception{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane) loader.load(file), width, heigth);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable) loader.getController();
        
        return resultado;
    }
    
    public void formInicioView(){
        try{
            FormInicioController formInicioView = (FormInicioController)switchScene("FormInicioView.fxml", 500, 750);
            formInicioView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formRegistroView(){
        try{
            FormRegistroController formRegistroView = (FormRegistroController) switchScene("FormRegistroView.fxml", 500, 750);
            formRegistroView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    
    public void menuPrincipalView (){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 950, 675);
            menuPrincipalView.setStage(this);
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuClienteView(){
        try{
            MenuClienteController menuClienteView = (MenuClienteController) switchScene("MenuClienteView.fxml", 1200, 750);
            menuClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void formClientesView(int op){
        try{
            FormClienteController formClientesView = (FormClienteController)switchScene("FormClienteView.fxml", 500, 750);
            formClientesView.setOp(op);
            formClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuCargoView(){
        try{
            MenuCargoController menuCargoView = (MenuCargoController) switchScene("MenuCargoView.fxml", 1200, 750);
            menuCargoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCargoView(int op){
        try{
            FormCargoController formCargoView = (FormCargoController)switchScene("FormCargoView.fxml", 500, 750);
            formCargoView.setOp(op);
            formCargoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCategoriaProductoView(){
        try{
            MenuCategoriaProductosController menuCategoriaProductosView = (MenuCategoriaProductosController)switchScene("MenuCategoriaProductosView.fxml", 1200, 750);
            menuCategoriaProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCategoriaView(int op){
        try{
            FormCategoriaProductoController formCategoriaProductoView = (FormCategoriaProductoController)switchScene("FormCategoriaView.fxml", 500, 750);
            formCategoriaProductoView.setOp(op);
            formCategoriaProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1200, 750);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuDistribuidorView(){
        try{
            MenuDistribuidorController menuDistribuidorView = (MenuDistribuidorController)switchScene("MenuDistribuidorView.fxml", 1200, 750);
            menuDistribuidorView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDistribuidorView(int op){
            try{
                FormDistribuidoresController formDistribuidor = (FormDistribuidoresController)switchScene("FormDistribuidoresView.fxml", 500, 750);
                formDistribuidor.setOp(op);
                formDistribuidor.setStage(this);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
    }
    
    public void menuEmpleadosView(){
        try{
            MenuEmpleadosController menuEmpleado = (MenuEmpleadosController)switchScene("MenuEmpleadosView.fxml", 1200, 750);
            menuEmpleado.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formEmpleadosView(int op){
        try{
            FormEmpleadosController formEmpleado = (FormEmpleadosController)switchScene("FormEmpleadosView.fxml", 500, 750);
            formEmpleado.setOp(op);
            formEmpleado.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
      
    
    public void formAsignarEncargadoView(){
        try{
        FormAsignarEncargadoController formAsignarEncargado = (FormAsignarEncargadoController)switchScene("FormAsignarEncargadoView.fxml", 500, 750);
        formAsignarEncargado.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuProductosView(){
        try{
            MenuProductosController menuProductos = (MenuProductosController)switchScene("MenuProductosView.fxml", 1200, 750);
            menuProductos.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formProductosView(int op){
        try{
            FormProductosController formProductos = (FormProductosController)switchScene("FormProductosView.fxml", 1200, 750);
            formProductos.setOp(op);
            formProductos.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuPromocionesView(){
        try{
            MenuPromocionesController menuPromociones = (MenuPromocionesController)switchScene("MenuPromocionesView.fxml", 1200, 750);
            menuPromociones.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formPromocionesView(int op){
        try{
            FormPromocionesController formPromociones = (FormPromocionesController)switchScene("FormPromocionesView.fxml", 500, 750);
            formPromociones.setOp(op);
            formPromociones.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuFacturasView(){
        try{
            MenuFacturasController menuFacturas = (MenuFacturasController)switchScene("MenuFacturasView.fxml", 1200, 750);
            menuFacturas.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formFacturasView(int op){
        try{
            FormFacturasController formFacturas = (FormFacturasController)switchScene("FormFacturasView.fxml", 500, 750);
            formFacturas.setOp(op);
            formFacturas.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleFacturaView(int op){
        try{
            FormDetalleFacturasController formDetalleFactura = (FormDetalleFacturasController)switchScene("FormDetalleFacturasView.fxml", 500, 750);
            formDetalleFactura.setOp(op);
            formDetalleFactura.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuComprasView(){
        try{
            MenuComprasController menuCompras = (MenuComprasController)switchScene("MenuComprasView.fxml", 1200, 750);
            menuCompras.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formComprasView(int op){
        try{
            FormComprasController formCompras = (FormComprasController)switchScene("FormComprasView.fxml", 500, 750);
            formCompras.setOp(op);
            formCompras.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleComprasView(int op){
        try{
            FormDetalleCompraController formDetalleFactura = (FormDetalleCompraController)switchScene("FormDetalleCompraView.fxml", 500, 750);
            formDetalleFactura.setOp(op);
            formDetalleFactura.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
    

