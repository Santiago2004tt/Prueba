package unibanco.model;

public class Transaccion {

    /**
     * attributes
     */
    private int registroValor;
    private String fecha;
    private int hora;
    private EstadoTransaccion estadoTransaccion;
    private TipoTransaccion tipoTransaccion;


    /**
     * builder the transaction
     */
    public Transaccion(int registroValor, String fecha, int hora, EstadoTransaccion estadoTransaccion, TipoTransaccion tipoTransaccion) {
        this.registroValor = registroValor;
        this.fecha = fecha;
        this.hora = hora;
        this.estadoTransaccion = estadoTransaccion;
        this.tipoTransaccion = tipoTransaccion;
    }

    public Transaccion() {
    }

    /**
     * Getters and Setters
     */
    public int getRegistroValor() {
        return registroValor;
    }

    public void setRegistroValor(int registroValor) {
        this.registroValor = registroValor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public EstadoTransaccion getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(EstadoTransaccion estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * HashCode and Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaccion that)) return false;

        return getTipoTransaccion() == that.getTipoTransaccion();
    }

    @Override
    public int hashCode() {
        return getTipoTransaccion().hashCode();
    }

    /**
     * To String
     */
    @Override
    public String toString() {
        return "Transaccion{" +
                "registroValor=" + registroValor +
                ", fecha='" + fecha + '\'' +
                ", hora=" + hora +
                ", estadoTransaccion=" + estadoTransaccion +
                ", tipoTransaccion=" + tipoTransaccion +
                '}';
    }
}


