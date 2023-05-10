package unibanco.application;

//imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import unibanco.exceptions.ClienteException;
import unibanco.exceptions.CuentaException;
import unibanco.controllers.*;


import unibanco.model.*;

import unibanco.model.Cuenta;
import unibanco.model.Banco;

import java.io.IOException;
import java.util.ArrayList;


public class Main extends Application {

    private Stage stage;

    Banco banco = new Banco("Unibanco", "123");

    public double imprimirHuendy(){
        int a = 0;
        int b = 0;
        double resultado = 0;
        if(a>0 && b>0){
           resultado = (Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
        }
        return resultado;
    }

    public void start(Stage stage) throws Exception{
        /*
        inicializa el fxml
         */
        this.stage = stage;
        mostrarLogin();
    }

    public void mostrarLogin() {
        try{
            /*
            carga fxml
             */

            FXMLLoader loader = new FXMLLoader();
            /*
            localiza el fxml
             */
            loader.setLocation(Main.class.getResource("../views/Login.fxml"));
            AnchorPane rootLayout = loader.load();
            /*
            invoca los controladores
             */

            LoginController controller = loader.getController();
            controller.setMain(this);
            /*
            inicializar la escena
             */
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Que bueno verte de vuelta!!");
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void mostrarCuentaCliente(Cliente cliente) {
        try{
            /*
            carga fxml
             */

            FXMLLoader loader = new FXMLLoader();
            /*
            localiza el fxml
             */
            loader.setLocation(Main.class.getResource("../views/CuentaCliente.fxml"));
            AnchorPane rootLayout = loader.load();
            /*
            invoca los controladores
             */

            CuentaClienteController controller = loader.getController();
            controller.mostrarBienvenida(cliente);
            controller.setMain(this);
            /*
            inicializar la escena
             */
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Suerte!!");
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void mostrarLoginAdmin() {
        try{
            /*
            carga fxml
             */

            FXMLLoader loader = new FXMLLoader();
            /*
            localiza el fxml
             */
            loader.setLocation(Main.class.getResource("../views/LoginAdmin.fxml"));
            AnchorPane rootLayout = loader.load();
            /*
            invoca los controladores
             */

            LoginAdminController controllerAdmin = loader.getController();
            controllerAdmin.setMain(this);
            /*
            inicializar la escena
             */
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Suerte!!");
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void mostrarTareasAdmin() {
        try {
            /*
            carga fxml
             */

            FXMLLoader loader = new FXMLLoader();
            /*
            localiza el fxml
             */
            loader.setLocation(Main.class.getResource("../views/TareasAdmin.fxml"));
            AnchorPane rootLayout = loader.load();
            /*
            invoca los controladores
             */

            TareasAdminController controller = loader.getController();
            controller.setMain(this);
            /*
            inicializar la escena
             */
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Suerte!!");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCrearCliente(Cliente clienteSeleccionado, Cliente cliente ) {
        try{
            /*
            carga el fxml
             */
            FXMLLoader loader = new FXMLLoader();
            /*
            localiza el fxml
             */
            loader.setLocation(Main.class.getResource("../views/CrearCliente.fxml"));
            AnchorPane rootLayout = loader.load();
            /*
            invoca los controladores
             */
            CrearClienteController controller = loader.getController();
            controller.obtenerCliente(cliente);
            controller.setMain(this);
            /*
            inicializa la escena
             */
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Crea el cliente a continuacion");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarActualizarCliente(Cliente clienteSeleccionado, Cliente cliente ) {
        try{
            /*
            carga el fxml
             */
            FXMLLoader loader = new FXMLLoader();
            /*
            localiza el fxml
             */
            loader.setLocation(Main.class.getResource("../views/ActualizarCliente.fxml"));
            AnchorPane rootLayout = loader.load();
            /*
            invoca los controladores
             */
            ActualizarClienteController controller = loader.getController();
            controller.obtenerCliente(cliente);
            controller.setMain(this);
            /*
            inicializa la escena
             */
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Actualiza el cliente a continuacion");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        //--------------------------------Mas_Metodos---------------------------------------------
    public boolean verificarCuenta(String cedula, String numCuenta) {
        return banco.verificarCuenta(cedula, numCuenta);
    }

    public Cuenta obtenerCuenta(String cedula, String numCuenta) {
        return banco.obtenerCuenta(cedula, numCuenta);
    }

    public boolean verificarCuentaAcceso(String usuarioAdmin, String contraseniaAdmin) {
        return banco.verificarCuentaAcceso(usuarioAdmin, contraseniaAdmin);
    }


    public CuentaAcceso obtenerCuentaAcceso(String usuarioAdmin, String contraseniaAdmin) {
        return banco.obtenerCuentaAcceso(usuarioAdmin, contraseniaAdmin);
    }

    public boolean crearCliente(String name, String apellido, String cedula, String direccion, String email, Cuenta cuenta) throws ClienteException {
        return banco.crearCliente(name, apellido, cedula, direccion, email, cuenta);
    }

    public boolean actualizarCliente(String name, String apellido, String cedula, String direccion, String email) throws ClienteException {
        return banco.actualizarCliente(name, apellido, cedula, direccion, email);
    }

    public boolean eliminarCliente(String cedula) throws ClienteException {
        return banco.eliminarCliente(cedula);
    }

    public boolean crearCuenta(String numeroCuenta, double saldo,  Tipo_Cuenta tipoCuenta) throws CuentaException {
        return banco.crearCuenta(numeroCuenta, saldo, tipoCuenta, banco);
    }


    public ArrayList<Cliente> obtenerListaClientes() {
        return banco.obtenerlistaClientes();
    }

    public boolean actualizarCuenta(String numeroCuenta, double saldo, Tipo_Cuenta tipoCuenta) {
        return banco.actualizarCuenta(numeroCuenta, saldo, tipoCuenta);
    }

    public Cliente obtenerCliente(String cedula) {
        return banco.obtenerCliente(cedula);
    }

    public Cuenta buscarCuenta(String numeroCuenta) throws CuentaException {
        return banco.buscarCuenta(numeroCuenta);

    }
}