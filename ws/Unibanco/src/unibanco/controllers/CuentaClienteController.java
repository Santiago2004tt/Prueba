package unibanco.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import unibanco.application.Main;
import unibanco.model.Cliente;
import unibanco.model.Cuenta;

public class CuentaClienteController {

    @FXML
    private ResourceBundle resources;
    Cliente clienteLogueado;
    Cliente cliente;

    private Main main;

    @FXML
    private URL location;

    @FXML
    private Button Inicio;

    @FXML
    private Button miCuenta;

    @FXML
    private Label totalSaldo;

    @FXML
    private Button transacciones;

    @FXML
    private Button volverLogin;

    @FXML
    private Label bienvenidaLabel;


    public void mostrarBienvenida(Cliente cliente) {
        String nombre ="";
        nombre = cliente.getNombre();
        bienvenidaLabel.setText("Que bueno verte de nuevo, " + nombre + "!!");
    }

    @FXML
    void volverInicio(ActionEvent event) {
        main.mostrarCuentaCliente(clienteLogueado);
    }

    @FXML
    void volverLog(ActionEvent event) {
        main.mostrarLogin();

    }
    @FXML
    void listTransaccion(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

    public void setMain(Main main) {
        this.main = main;
    }
}