package Domain;

public class Usuarios {
    
    private String usuario;
    private String contraseña;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private static int numeroUsuario;
    
    public Usuarios(){
        
    }
    
    public Usuarios(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido){
      this.usuario = usuario;
      this.contraseña = contraseña;
      this.primerNombre = primerNombre;
      this.segundoNombre = segundoNombre;
      this.primerApellido = primerApellido;
      this.segundoApellido = segundoApellido;
    }

    /*public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }*/

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public static int getNumeroUsuario() {
        return numeroUsuario;
    }

    public static void setNumeroUsuario(int numeroUsuario) {
        Usuarios.numeroUsuario = numeroUsuario;
    }
    
    
    
    
    
    
    
}
