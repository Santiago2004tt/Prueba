package unibanco.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import unibanco.application.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import unibanco.exceptions.ClienteException;
import unibanco.model.Banco;
import unibanco.model.Cliente;

public class TareasAdminController {

    ObservableList<Cliente> listaClientesData = FXCollections.observableArrayList();

    Cliente clienteSeleccionado = null;

    private Main main;

    Cliente clienteLogueado;

    @FXML
    private Button actualizarClienteButton;

    @FXML
    private TableColumn<Cliente, String> apellidosClienteColum;

    @FXML
    private TableColumn<Cliente, String> cedulaClienteColum;

    @FXML
    private Button cerrarTareasButton;

    @FXML
    private TableView<Cliente> clientesTable;

    @FXML
    private TableColumn<Cliente, String> correoClienteColum;

    @FXML
    private Button crearClienteButton;

    @FXML
    private TableColumn<Cliente, String> direccionClienteColum;

    @FXML
    private Button eliminarClienteButton;

    @FXML
    private TableColumn<Cliente, String> nombreClienteColum;

    @FXML
    void cerrar(ActionEvent event) {
        main.mostrarLogin();
    }

    @FXML
    void crearcliente(ActionEvent event) {
        main.mostrarCrearCliente(clienteSeleccionado, clienteLogueado);
    }

    @FXML
    void actualizarCliente(ActionEvent event) {
        main.mostrarActualizarCliente(clienteSeleccionado, clienteLogueado);
    }

    @FXML
    void eliminarCliente(ActionEvent event) throws ClienteException {
        eliminarClienteAction();
    }

    private void eliminarClienteAction() throws ClienteException{
        String cedula = "";
        cedula = clienteSeleccionado.getCedula();
        if(main.eliminarCliente(cedula)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Listo!!");
            alert.setContentText("Cliente eliminado");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStyleClass().add("dialog");
            alert.showAndWait();
            listaClientesData.remove(clienteSeleccionado);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error :(");
            alert.setContentText("Imposible eliminar el cliente");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStyleClass().add("dialog");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        this.nombreClienteColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.apellidosClienteColum.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.cedulaClienteColum.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.direccionClienteColum.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.correoClienteColum.setCellValueFactory(new PropertyValueFactory<>("email"));

        clientesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
        });
    }

    private ObservableList<Cliente> obtenerListaClientes() {
        listaClientesData.addAll(main.obtenerListaClientes());
        return listaClientesData;
    }


    public void setMain(Main main) {
        this.main = main;
        clientesTable.getItems().clear();
        clientesTable.setItems(obtenerListaClientes());
    }
}
