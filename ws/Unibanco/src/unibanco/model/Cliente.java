package unibanco.model;

public class Cliente {

    /**
     * attributes
     */
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String email;
    private Cuenta cuenta;

    /**
     * builder the client
     */
    public Cliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.email = email;
        this.cuenta = cuenta;
    }

    public Cliente() {

    }

    /**
     *  Getter and Setters
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * To string
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * HashCode amd Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;

        return getCedula().equals(cliente.getCedula());
    }

    @Override
    public int hashCode() {
        return getCedula().hashCode();
    }

    public boolean verificarNumCuenta(String numCuenta) {
        return cuenta.verificarNumeroCuenta(numCuenta);
    }
}
