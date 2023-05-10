package unibanco.model;

import java.util.ArrayList;

public class Administrador {

    /**
     * attributes
     */
    private String nombre;
    private String apellido;
    private String cedula;
    private CuentaAcceso cuentaAcceso;




    /**
     * builder
     */
    public Administrador(String nombre, String apellido, String cedula, CuentaAcceso cuentaAcceso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cuentaAcceso = cuentaAcceso;
    }

    public Administrador() {

    }

    /**
     * Getter and Setters
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

    public CuentaAcceso getCuentaAcceso() {
        return cuentaAcceso;
    }

    public void setCuentaAcceso(CuentaAcceso cuentaAcceso) {
        this.cuentaAcceso = cuentaAcceso;
    }

    /**
     * HashCode and Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrador that)) return false;

        return getCedula().equals(that.getCedula());
    }

    @Override
    public int hashCode() {
        return getCedula().hashCode();
    }

    /**
     * To string
     */
    @Override
    public String toString() {
        return "Administrador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", cuentaAcceso=" + cuentaAcceso +
                '}';
    }
}
