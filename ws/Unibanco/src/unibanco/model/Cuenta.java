package unibanco.model;

import unibanco.exceptions.TransaccionException;

import java.util.ArrayList;

public class Cuenta {

    /**
     * attributes
     */
    private String numeroCuenta;

    private double saldo;
    private Tipo_Cuenta tipo_cuenta;
    private  Cliente cliente;

    private ArrayList<Transaccion> listaTransacciones;

    /**
     * builder the account
     */

    public Cuenta(String numeroCuenta, double saldo, Tipo_Cuenta tipo_cuenta, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipo_cuenta = tipo_cuenta;
        this.cliente = cliente;
        listaTransacciones = new ArrayList<Transaccion>();

    }

    public Cuenta() {

    }

    /**
     * Get and Set
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public Tipo_Cuenta getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(Tipo_Cuenta tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    /**
     *To String
     */
    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", tipo_cuenta=" + tipo_cuenta +
                ", listaTransacciones=" + listaTransacciones +
                '}';
    }

    /**
     * Equals and HashCode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta cuenta)) return false;

        return getNumeroCuenta() != null ? getNumeroCuenta().equals(cuenta.getNumeroCuenta()) : cuenta.getNumeroCuenta() == null;
    }

    @Override
    public int hashCode() {
        return getNumeroCuenta() != null ? getNumeroCuenta().hashCode() : 0;
    }

    //------------------------------CRUD_TRANSACCION---------------------------------------------------------------

    /**
     * Create transaction
     * @param registroValor
     * @param fecha
     * @param hora
     * @param estadoTransaccion
     * @param tipoTransaccion
     * @param cuenta
     * @return
     * @throws TransaccionException
     */
    public boolean crearTransaccion(int registroValor, String fecha, int hora, EstadoTransaccion estadoTransaccion, TipoTransaccion tipoTransaccion, Cuenta cuenta) throws TransaccionException {
        Transaccion transaccion = new Transaccion();
        transaccion.setRegistroValor(registroValor);
        transaccion.setHora(hora);
        transaccion.setFecha(fecha);
        transaccion.setEstadoTransaccion(estadoTransaccion);
        transaccion.setTipoTransaccion(tipoTransaccion);
        if (existeTransaccion(tipoTransaccion, cuenta) == true){
            throw new TransaccionException("Transaccion existe");
        }
        cuenta.getListaTransacciones().add(transaccion);
        return true;
    }

    /**
     * Existence
     * @param tipoTransaccion
     * @param cuenta
     * @return
     * @throws TransaccionException
     */
    private boolean existeTransaccion(TipoTransaccion tipoTransaccion, Cuenta cuenta) throws TransaccionException{
        for (Transaccion transaccion : cuenta.getListaTransacciones()) {
            if (transaccion.getTipoTransaccion().equals(tipoTransaccion)){
                return true;
            }
        }
        return false;
    }

    /**
     * Update transaction
     * @param registroValor
     * @param fecha
     * @param hora
     * @param estadoTransaccion
     * @param tipoTransaccion
     * @param cuenta
     * @return
     */
    public boolean actualizarTransaccion(int registroValor, String fecha, int hora, EstadoTransaccion estadoTransaccion, TipoTransaccion tipoTransaccion, Cuenta cuenta){
        for (Transaccion transaccion : cuenta.getListaTransacciones()) {
            if (transaccion.getTipoTransaccion().equals(tipoTransaccion)){
                transaccion.setRegistroValor(registroValor);
                transaccion.setFecha(fecha);
                transaccion.setHora(hora);
                transaccion.setEstadoTransaccion(estadoTransaccion);
                transaccion.setTipoTransaccion(tipoTransaccion);
                return true;
            }
        }
        return false;
    }

    /**
     * Delete transaction
     * @param tipoTransaccion
     * @param cuenta
     * @return
     * @throws TransaccionException
     */
    public boolean eliminarTransaccion(TipoTransaccion tipoTransaccion, Cuenta cuenta) throws TransaccionException{
        if (existeTransaccion(tipoTransaccion, cuenta)) {
            for (Transaccion transaccion : cuenta.getListaTransacciones()) {
                if (transaccion.getTipoTransaccion().equals(tipoTransaccion)) {
                    cuenta.getListaTransacciones().remove(transaccion);
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * Search transaction
     * @param tipoTransaccion
     * @param cuenta
     * @return
     * @throws TransaccionException
     */
    public Transaccion buscarTransaccion(TipoTransaccion tipoTransaccion, Cuenta cuenta) throws TransaccionException{
        Transaccion transaccionEncontrada = null;
        if (existeTransaccion(tipoTransaccion, cuenta)){
            for (Transaccion transaccion : cuenta.getListaTransacciones()) {
                if (transaccion.getTipoTransaccion().equals(tipoTransaccion)){
                    transaccionEncontrada = transaccion;
                    return transaccionEncontrada;
                }
            }
        }if (transaccionEncontrada == null){
            throw new TransaccionException("Transaccion no se encuentra");
        }
        return transaccionEncontrada;
    }

    public boolean verificarNumeroCuenta(String numCuenta) {
        if (numCuenta.equals(numeroCuenta)){
            return true;
        }
        return false;
    }
}
