package unibanco.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import unibanco.application.Main;
import unibanco.model.Cliente;
import unibanco.model.Cuenta;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    private Main main;

    @FXML
    private URL location;

    @FXML
    private Button ingresoAdminButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField numeroCuenta;

    @FXML
    private TextField numeroCedula;

    private Cliente clienteLogueado;

    @FXML
    void loginUser(ActionEvent event) throws IOException {
        loginEvet(event);
    }

    @FXML
    void ingresoAdmin(ActionEvent event) {
        main.mostrarLoginAdmin();
    }

    private void loginEvet(ActionEvent event) throws IOException {
        String cedula = "";
        String numCuenta = "";
        cedula = numeroCedula.getText();
        numCuenta = numeroCuenta.getText();
        if (verificarCampos(cedula, numCuenta)){
            if (main.verificarCuenta(cedula, numCuenta)){
                Cliente cliente = main.obtenerCliente(cedula);
                main.mostrarCuentaCliente(cliente);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Datos incorrectos");
                alert.setContentText("Por favor verifica tus datos y vuelve a intentar");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStyleClass().add("dialog");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Atencion");
            alert.setContentText("Completa todos los campos para poder continuar");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStyleClass().add("dialog");
            alert.showAndWait();
        }
    }

    private boolean verificarCampos(String cedula, String numCuenta) {
        if(cedula.equals("")||numCuenta.equals("")){
            return false;
        }
        return true;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}