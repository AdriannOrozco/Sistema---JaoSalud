package Domain;
public class Medico extends Usuarios {

    private String identificacion;
    private String especialidad;
    private double salario;
    private int añosExperiencia;
    private Consultorio consultorio;
   
    public Medico(){
        
    }
    
    public Medico(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
         String identificacion, String especialidad, double salario, int añosExperiencia, Consultorio consultorio) {
   
        super(usuario, contraseña, primerNombre, segundoNombre, primerApellido, segundoApellido);  
        this.identificacion = identificacion;
        this.especialidad = especialidad;
        this.salario = salario;
        this.añosExperiencia = añosExperiencia;
        this.consultorio = consultorio;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }   
}
