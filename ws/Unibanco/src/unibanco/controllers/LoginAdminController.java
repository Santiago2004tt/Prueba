package unibanco.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import unibanco.application.Main;
import unibanco.model.Cuenta;
import unibanco.model.CuentaAcceso;

public class LoginAdminController {


    @FXML
    private PasswordField contrasenia;
    private Main main;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usuario;

    @FXML
    private Button volverButton;

    @FXML
    void loginAdminUser(ActionEvent event) throws IOException {
        loginAdminEvet(event);
    }

    private void loginAdminEvet(ActionEvent event)  throws  IOException{
        String usuarioAdmin = usuario.getText();
        String contraseniaAdmin = contrasenia.getText();
        if (verificarCampos(usuarioAdmin, contraseniaAdmin)){
            if (main.verificarCuentaAcceso(usuarioAdmin, contraseniaAdmin)){
                CuentaAcceso cuentaAcceso = main.obtenerCuentaAcceso(usuarioAdmin, contraseniaAdmin);
                main.mostrarTareasAdmin();
            }
        }
    }

    private boolean verificarCampos(String usuarioAdmin, String contraseniaAdmin) {
        if(usuarioAdmin.equals("")||contraseniaAdmin.equals("")){
            return false;
        }
        return true;
    }

    @FXML
    void volverLogin(ActionEvent event) {
        main.mostrarLogin();
    }

    @FXML
    void initialize() {

    }

    public void setMain(Main main) {
        this.main = main;
    }
}