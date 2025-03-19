package Domain;

public class Recepcionista extends Usuarios {
    

    private String id;
    private String email;
    private double sueldo;
    
    //Constructor por defecto
    public Recepcionista(){
        
    }
    
    //Constructor con paramétros
    public Recepcionista(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String nombreCompletos, String id, String email, double sueldo) {
        super(usuario, contraseña, primerNombre, segundoNombre, primerApellido, segundoApellido);
        this.id = id;
        this.email = email;
        this.sueldo = sueldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
  }
