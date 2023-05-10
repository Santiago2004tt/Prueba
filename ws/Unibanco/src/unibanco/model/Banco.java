package unibanco.model;

import unibanco.exceptions.AdministradorException;
import unibanco.exceptions.ClienteException;
import unibanco.exceptions.CuentaAccesoException;
import unibanco.exceptions.CuentaException;

import java.util.ArrayList;

public class Banco {

    /**
     * attributes
     */
    private String nombre;
    private String nit;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cuenta> listaCuentas;
    private ArrayList<CuentaAcceso> listaCuentasAcceso;
    private ArrayList<Administrador> listaAdministradores;

    /**
     * builder the bank
     */
    public Banco(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        listaClientes = new ArrayList<Cliente>();
        listaCuentas = new ArrayList<Cuenta>();
        listaAdministradores = new ArrayList<Administrador>();
        listaCuentasAcceso= new ArrayList<CuentaAcceso>();

        inicializarDatos();
        inicializarDatosAdmin();
    }

    private void inicializarDatos() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Vantee");
        cliente.setApellido("caicedo");
        cliente.setCedula("1");
        cliente.setDireccion("casa");
        cliente.setEmail("huendy@gmail.com");
        Cuenta cuenta = new Cuenta("1", 3000, Tipo_Cuenta.AHORRO, cliente);
        cliente.setCuenta(cuenta);
        listaCuentas.add(cuenta);
        listaClientes.add(cliente);
    }

    private void inicializarDatosAdmin() {
        Administrador administrador = new Administrador();
        administrador.setNombre("Miguel");
        administrador.setApellido("Garcia");
        administrador.setCedula("123");
        CuentaAcceso cuentaAcceso = new CuentaAcceso(administrador.getCedula(),"crespitos");
        listaCuentasAcceso.add(cuentaAcceso);
        listaAdministradores.add(administrador);
    }


    /**
     * Get and Set
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public ArrayList<CuentaAcceso> getListaCuentasAcceso() {
        return listaCuentasAcceso;
    }

    public void setListaCuentasAcceso(ArrayList<CuentaAcceso> listaCuentasAcceso) {
        this.listaCuentasAcceso = listaCuentasAcceso;
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public void setListaAdministradores(ArrayList<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    /**
     * To String
     */
    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", listaClientes=" + listaClientes +
                ", listaCuentas=" + listaCuentas +
                ", listaCuentasAcceso=" + listaCuentasAcceso +
                ", listaAdministradores=" + listaAdministradores +
                '}';
    }

    /**
     * Equals and HashCode
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banco banco)) return false;

        if (!getNombre().equals(banco.getNombre())) return false;
        return getNit().equals(banco.getNit());
    }

    @Override
    public int hashCode() {
        int result = getNombre().hashCode();
        result = 31 * result + getNit().hashCode();
        return result;
    }


    //------------------CRUD_CUENTA_ACCESO-------------------------------------------------------------------------------------

    /**
     * Create access account
     * @param usuario
     * @param password
     * @param banco
     * @return
     * @throws CuentaAccesoException
     */
    public boolean crearCuentaAcceso(String usuario, String password, Banco banco) throws CuentaAccesoException {
        CuentaAcceso cuentaAcceso = new CuentaAcceso();
        cuentaAcceso.setUsuario(usuario);
        cuentaAcceso.setPassword(password);
        if (existeCuentaAcceso(usuario, banco) == true){
            throw new CuentaAccesoException("Cuenta de acceso ya existe");
        }
        banco.getListaCuentasAcceso().add(cuentaAcceso);
        return true;
    }

    /**
     * Existence access account
     * @param usuario
     * @param banco
     * @return
     * @throws CuentaAccesoException
     */
    public boolean existeCuentaAcceso(String usuario, Banco banco) throws CuentaAccesoException{
        for (CuentaAcceso cuentaAcceso : banco.getListaCuentasAcceso()) {
            if (cuentaAcceso.getUsuario().equals(usuario)){
                return true;
            }
        }
        return false;
    }

    /**
     * Update access account
     * @param usuario
     * @param password
     * @param banco
     * @return
     */
    public boolean actualizarCuentaAcceso(String usuario, String password, Banco banco){
        for (CuentaAcceso cuentaAcceso : banco.getListaCuentasAcceso()) {
            if (cuentaAcceso.getUsuario().equals(usuario)){
                cuentaAcceso.setUsuario(usuario);
                cuentaAcceso.setPassword(password);
                return true;
            }
        }
        return false;
    }

    /**
     * Delete access account
     * @param usuario
     * @param banco
     * @return
     * @throws CuentaAccesoException
     */
    public boolean eliminarCuentaAcceso(String usuario, Banco banco) throws CuentaAccesoException{
        if (existeCuentaAcceso(usuario, banco)){
            for (CuentaAcceso cuentaAcceso : banco.getListaCuentasAcceso()) {
                if(cuentaAcceso.getUsuario().equals(usuario)){
                    banco.getListaCuentasAcceso().remove(cuentaAcceso);
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * search access account
     * @param usuario
     * @param banco
     * @return
     * @throws CuentaAccesoException
     */
    public CuentaAcceso buscarCuentaAcceso(String usuario, Banco banco) throws CuentaAccesoException{
        CuentaAcceso cuentaAccesoEncontrada = null;
        if (existeCuentaAcceso(usuario, banco)){
            for (CuentaAcceso cuentaAcceso : banco.getListaCuentasAcceso()) {
                if (cuentaAcceso.getUsuario().equals(usuario)){
                    cuentaAccesoEncontrada = cuentaAcceso;
                    return cuentaAccesoEncontrada;
                }
            }
        }
        if (cuentaAccesoEncontrada == null){
            throw new CuentaAccesoException("La cuenta no se encuentra");
        }
        return cuentaAccesoEncontrada;
    }
    //--------------------------------------------CRUD_CUENTA------------------------------------------------------

    /**
     * Create account
     * @param numeroCuenta
     * @param saldo
     * @param banco
     * @return
     * @throws CuentaException
     */
    public boolean crearCuenta(String numeroCuenta, double saldo,Tipo_Cuenta tipo_cuenta, Banco banco) throws CuentaException {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(numeroCuenta);
        cuenta.setSaldo(saldo);
        cuenta.setTipo_cuenta(tipo_cuenta);
        if (existeCuenta(numeroCuenta, banco) == true){
            throw new CuentaException("Cuenta ya existe");
        }
        banco.getListaCuentas().add(cuenta);
        return true;
    }

    /**
     *Existence
     * @param numeroCuenta
     * @param banco
     * @return
     * @throws CuentaException
     */
    public boolean existeCuenta(String numeroCuenta, Banco banco) throws CuentaException{
        for (Cuenta cuenta : banco.getListaCuentas()) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)){
                return true;
            }
        }
        return false;
    }

    /**
     *Update account
     * @param numeroCuenta
     * @param saldo

     * @return
     */
    public boolean actualizarCuenta(String numeroCuenta, double saldo, Tipo_Cuenta tipo_cuenta){
        for (Cuenta cuenta : getListaCuentas()) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)){
                cuenta.setNumeroCuenta(numeroCuenta);
                cuenta.setSaldo(saldo);
                cuenta.setTipo_cuenta(tipo_cuenta);
                return true;
            }
        }
        return false;
    }

    /**
     *Delete account
     * @param numeroCuenta
     * @param banco
     * @return
     * @throws CuentaException
     */
    public boolean eliminarCuenta(String numeroCuenta, Banco banco) throws CuentaException{
        if (existeCuenta(numeroCuenta, banco)){
            for (Cuenta cuenta : banco.getListaCuentas()) {
                if(cuenta.getNumeroCuenta().equals(numeroCuenta)){
                    banco.getListaCuentas().remove(cuenta);
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * search account
     * @param numeroCuenta
     * @param banco
     * @return
     * @throws CuentaException
     */
    public Cuenta buscarCuenta(String numeroCuenta) throws CuentaException{
        Cuenta cuentaEncontrada = null;
        for (Cuenta cuenta : getListaCuentas()) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)){
                cuentaEncontrada = cuenta;
                return cuentaEncontrada;
            }
        }
        if (cuentaEncontrada == null){
            throw new CuentaException("La cuenta no se encuentra");
        }
        return cuentaEncontrada;
    }
    //----------------CRUD_CLIENTE---------------------------------------------------------------------------

    /**
     *Create client
     * @param nombre
     * @param apellido
     * @param cedula
     * @param direccion
     * @param email
     * @return
     * @throws ClienteException
     */
    public boolean crearCliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) throws ClienteException{
        Cliente cliente = new Cliente();
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setDireccion(direccion);
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        cliente.setCuenta(cuenta);
        if (existeCliente(cedula) == true){
            throw new ClienteException("Cliente existe");
        }
        getListaClientes().add(cliente);
        return true;
    }

    /**
     *Existence
     * @param cedula
     * @return
     * @throws ClienteException
     */
    private boolean existeCliente(String cedula) throws ClienteException{
        for (Cliente cliente : getListaClientes()) {
            if (cliente.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    /**
     *Update client
     * @param nombre
     * @param apellido
     * @param cedula
     * @param direccion
     * @param email
     * @return
     */
    public boolean actualizarCliente(String nombre, String apellido, String cedula, String direccion, String email){
        for (Cliente cliente : getListaClientes()) {
            if (cliente.getCedula().equals(cedula)){
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setCedula(cedula);
                cliente.setDireccion(direccion);
                cliente.setEmail(email);
                return true;
            }
        }
        return false;
    }

    /**
     *delete client
     * @param cedula
     * @return
     * @throws ClienteException
     */
    public boolean eliminarCliente(String cedula) throws ClienteException{
        if(existeCliente(cedula)){
            for (Cliente cliente : getListaClientes()) {
                if (cliente.getCedula().equals(cedula)){
                    getListaClientes().remove(cliente);
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * search client
     * @param cedula
     * @return
     * @throws ClienteException
     */
    public Cliente buscarCliente(String cedula) throws ClienteException{
        Cliente clienteEncontrado = null;
        if (existeCliente(cedula)){
            for (Cliente cliente : getListaClientes()) {
                if (cliente.getCedula().equals(cedula)){
                    clienteEncontrado = cliente;
                    return  clienteEncontrado;
                }
            }
        }if (clienteEncontrado == null){
            throw new ClienteException("Cliente no se encuentra");
        }
        return clienteEncontrado;
    }

    //------------------------------------------CRUD_ADMIN-------------------------------------------------------

    /**
     * Create Admin
     * @param nombre
     * @param apellido
     * @param cedula
     * @param cuentaAcceso
     * @param banco
     * @return
     * @throws AdministradorException
     */
    public boolean crearAdministrador(String nombre, String apellido,String cedula, CuentaAcceso cuentaAcceso, Banco banco) throws AdministradorException {
        Administrador administrador = new Administrador();
        administrador.setApellido(apellido);
        administrador.setCedula(cedula);
        administrador.setNombre(nombre);
        administrador.setCuentaAcceso(cuentaAcceso);
        if (existeAdministrador(cedula, banco) == true){
            throw new AdministradorException("Administrador existe");
        }
        banco.getListaAdministradores().add(administrador);
        return true;
    }

    /**
     *Existence admin
     * @param cedula
     * @param banco
     * @return
     * @throws AdministradorException
     */
    private boolean existeAdministrador(String cedula, Banco banco) throws AdministradorException{
        for (Administrador administrador : banco.getListaAdministradores()) {
            if (administrador.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update admin
     * @param nombre
     * @param apellido
     * @param cedula
     * @param banco
     * @return
     */
    public boolean actualizarAdministrador(String nombre, String apellido, String cedula, Banco banco){
        for (Administrador administrador : banco.getListaAdministradores()) {
            if (administrador.getCedula().equals(cedula)){
                administrador.setNombre(nombre);
                administrador.setApellido(apellido);
                administrador.setCedula(cedula);
                return true;
            }
        }
        return false;
    }

    /**
     *delete admin
     * @param cedula
     * @param banco
     * @return
     * @throws ClienteException
     */
    public boolean eliminarAdministrador(String cedula, Banco banco) throws AdministradorException{
        if(existeAdministrador(cedula, banco)){
            for (Administrador administrador : banco.getListaAdministradores()) {
                if (administrador.getCedula().equals(cedula)){
                    banco.getListaAdministradores().remove(administrador);
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * search admin
     * @param cedula
     * @param banco
     * @return
     * @throws AdministradorException
     */
    public  Administrador buscarAdministrador(String cedula, Banco banco) throws AdministradorException{
        Administrador adminEncontrado = null;
        if (existeAdministrador(cedula, banco)){
            for (Administrador administrador : banco.getListaAdministradores()) {
                if (administrador.getCedula().equals(cedula)){
                    adminEncontrado = administrador;
                    return  adminEncontrado;
                }
            }
        }if (adminEncontrado == null){
            throw new AdministradorException("Administrador no encuentra");
        }
        return adminEncontrado;
    }
    //---------------------------------METODOS_INTERFACES-----------------------------------------------

    public boolean verificarCuenta(String cedula, String numCuenta) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente.verificarNumCuenta(numCuenta);
            }
        }
        return false;
    }
    public Cuenta obtenerCuenta(String cedula, String numCuenta) {
        Cuenta cuentaEncontrada = null;
        for (Cuenta cuenta : listaCuentas) {
            if (verificarCuenta(cedula, numCuenta)){
                cuentaEncontrada = cuenta;
                break;
            }
        }
        return cuentaEncontrada;
    }

    public boolean verificarCuentaAcceso(String usuarioAdmin, String contraseniaAdmin) {
        for (CuentaAcceso cuentaAcceso : listaCuentasAcceso) {
            if (cuentaAcceso.verificarCuentaAcceso(usuarioAdmin, contraseniaAdmin)){
                return true;
            }
        }
        return false;
    }

    public CuentaAcceso obtenerCuentaAcceso(String usuarioAdmin, String contraseniaAdmin) {
        CuentaAcceso cuentaAccesoEncontrada = null;
        for (CuentaAcceso cuentaAcceso : listaCuentasAcceso) {
            if (cuentaAcceso.verificarCuentaAcceso(usuarioAdmin, contraseniaAdmin)){
                cuentaAccesoEncontrada = cuentaAcceso;
                break;
            }
        }
        return cuentaAccesoEncontrada;
    }

    public Cliente obtenerCliente(String cedula) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(cedula)){
                clienteEncontrado = cliente;
                return clienteEncontrado;
            }
        }
        return  clienteEncontrado;
    }


    public ArrayList<Cliente> obtenerlistaClientes() {
        return getListaClientes();
    }
}