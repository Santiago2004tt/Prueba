package unibanco.model;

public class CuentaAcceso {

    /**
     * attributes
     */
    private String usuario;
    private String password;

    /**
     *  builder
     */
    public CuentaAcceso(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public CuentaAcceso() {

    }

    /**
     *Getters and Setters
     */
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * HashCode and Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuentaAcceso that)) return false;

        return getPassword() != null ? getPassword().equals(that.getPassword()) : that.getPassword() == null;
    }

    @Override
    public int hashCode() {
        return getPassword() != null ? getPassword().hashCode() : 0;
    }

    /**
     * To String
     */
    @Override
    public String toString() {
        return "CuentaAcceso{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean verificarCuentaAcceso(String usuarioAdmin, String contraseniaAdmin) {
        if(getUsuario().equals(usuarioAdmin) && getPassword().equals(contraseniaAdmin)){
            return true;
        }
        return false;
    }
}
