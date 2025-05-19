package Model;

public class Usuarios {
    
    private int id_usuario;
    private String usuario;
    private String contraseña;  
    private String rol;
    public Usuarios(){
        
    }
    
    public Usuarios(String usuario, String contraseña){
      this.usuario = usuario;
      this.contraseña = contraseña;
    }

    public String getUsuario() {
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
    }   
    
    public int getId_usuario() {
        return id_usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
