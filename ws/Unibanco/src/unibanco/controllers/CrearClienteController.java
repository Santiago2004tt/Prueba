package unibanco.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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

public class CrearClienteController {

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
    private ComboBox<String> combotipo;

    @FXML
    private Button volverButton;

    @FXML
    private Button registroButton;

    private Main main;


    @FXML
    void volver(ActionEvent event) {
        main.mostrarTareasAdmin();
    }

    @FXML
    void crearClienteCuenta(ActionEvent event) throws  CuentaException, ClienteException{
        crearClienteCuentaAction();
    }

    private void crearClienteCuentaAction() throws CuentaException, ClienteException {
        String name = nomCliente.getText();
        String apellido = apellidoCliente.getText();
        String cedula = cedulaCliente.getText();
        String direccion = direcCliente.getText();
        String email = emailCliente.getText();
        String numeroCuenta = numCuenta.getText();
        Tipo_Cuenta tipoCuenta = Tipo_Cuenta.valueOf(combotipo.getValue());
        double saldo = Double.parseDouble(saldoCliente.getText());
            try {
                if (verificarCampos(name, apellido,cedula, direccion, email, numeroCuenta, saldo, String.valueOf(tipoCuenta))){
                    main.crearCuenta(numeroCuenta, saldo, tipoCuenta);
                    Cuenta cuenta = main.buscarCuenta(numeroCuenta);
                    boolean registroCompleto = main.crearCliente(name, apellido, cedula,direccion, email, cuenta);
                    System.out.println("funciono");
                    if(registroCompleto){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Listo!!");
                        alert.setContentText("registro creado");
                        alert.showAndWait();
                        limpiarCampos();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Error");
                        alert.setContentText("no fue posible terminar el registro");
                        alert.showAndWait();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error");
                    alert.setContentText("Rellena los campos de texto faltantes y vuelve a intentar");
                    alert.showAndWait();
                }
            }catch (ClienteException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error");
                alert.setContentText("Rellena los campos de texto faltantes y vuelve a intentar");
                alert.showAndWait();
            }catch (CuentaException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error");
                alert.setContentText("Rellena los campos de texto faltantes y vuelve a intentar");
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
                                    String numeroCuenta, double saldo, String tipoCuenta) {

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
        combotipo.setItems(list);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void obtenerCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}