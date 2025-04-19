package Model;
public class Medico extends Usuarios {

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String identificacionDoctor;
    private String especialidad;
    private double salario;
    private int añosExperiencia;
  
   
    public Medico(){
        
    }
    
    public Medico(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
         String identificacionDoctor, String especialidad, double salario, int añosExperiencia, Consultorio consultorio) {
   
        super(usuario, contraseña);
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.identificacionDoctor = identificacionDoctor;
        this.especialidad = especialidad;
        this.salario = salario;
        this.añosExperiencia = añosExperiencia;
       
    }

    public String getIdentificacionDoctor() {
        return identificacionDoctor;
    }

    public void setIdentificacionDoctor(String identificacionDoctor) {
        this.identificacionDoctor = identificacionDoctor;
    }
   

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

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
    
    
}
