package unibanco.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import unibanco.application.Main;
import unibanco.exceptions.ClienteException;
import unibanco.exceptions.CuentaException;
import unibanco.model.Cliente;
import unibanco.model.Cuenta;
import unibanco.model.Tipo_Cuenta;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarClienteController {

    private Tipo_Cuenta[] listaTipos = {Tipo_Cuenta.AHORRO, Tipo_Cuenta.CORRIENTE};


    @FXML
    private ResourceBundle resources;
    Cliente cliente;

    @FXML
    private URL location;
    private Cuenta cuentaLogeada = null;

    @FXML
    private TextField apellidoCliente;

    @FXML
    private TextField cedulaCliente;

    @FXML
    private TextField direcCliente;

    @FXML
    private TextField emailCliente;

    @FXML
    private TextField nomCliente;

    @FXML
    private TextField numCuenta;

    @FXML
    private TextField saldoCliente;

    @FXML
    private ComboBox<Tipo_Cuenta> combotipo;

    @FXML
    private Button volverButton;

    @FXML
    private Button actualizarButton;

    private Main main;

    @FXML
    void actualizarClienteCuenta(ActionEvent event) throws CuentaException, ClienteException{
        actualizarClienteAction();
    }

    @FXML
    void volver(ActionEvent event) {
        main.mostrarTareasAdmin();
    }

    private void actualizarClienteAction() throws CuentaException, ClienteException {
        String name = nomCliente.getText();
        String apellido = apellidoCliente.getText();
        String cedula = cedulaCliente.getText();
        String direccion = direcCliente.getText();
        String email = emailCliente.getText();
        String numeroCuenta = numCuenta.getText();
        Tipo_Cuenta tipoCuenta = combotipo.getValue();
        double saldo = 0;
        saldo = Double.parseDouble(saldoCliente.getText());
        if (verificarCampos(name, apellido,cedula, direccion, email, numeroCuenta, saldo, tipoCuenta)){
            boolean registroCompleto = main.actualizarCliente(name, apellido, cedula, direccion, email)
                    && main.actualizarCuenta(numeroCuenta, saldo, tipoCuenta);
            if(registroCompleto){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Listo!!");
                alert.setContentText("registro actualizado");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStyleClass().add("dialog");
                alert.showAndWait();
                limpiarCampos();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error");
                alert.setContentText("no fue posible actualizar el registro");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStyleClass().add("dialog");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText("Rellena los campos de texto faltantes y vuelve a intentar");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStyleClass().add("dialog");
            alert.showAndWait();
        }

    }

    private void limpiarCampos() {
        nomCliente.clear();
        apellidoCliente.clear();
        cedulaCliente.clear();
        direcCliente.clear();
        emailCliente.clear();
        numCuenta.clear();
        saldoCliente.clear();
        combotipo.getSelectionModel().clearSelection();
    }

    private boolean verificarCampos(String name, String apellido, String cedula, String direccion, String email,
                                    String numeroCuenta, double saldo, Tipo_Cuenta tipoCuenta) {

        if(name.equals("")){
            return false;

        }
        if(apellido.equals("")){
            return false;

        }
        if(cedula.equals("")){
            return false;

        }
        if(direccion.equals("")){
            return false;

        }
        if(email.equals("")){
            return false;

        }
        if(numeroCuenta.equals("")){
            return false;

        }
        if(saldo == 0){
            return false;
        }
        if(tipoCuenta.equals("")){
            return false;

        }
        return true;
    }

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("AHORRO","CORRIENTE");
        combotipo.getItems().addAll(listaTipos);
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public void obtenerCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void obtenerCuenta(Cuenta cuenta) {
        this.cuentaLogeada = cuenta;
        numCuenta.setText(cuenta.getNumeroCuenta());
        saldoCliente.setText(String.valueOf(cuenta.getSaldo()));
    }

}
