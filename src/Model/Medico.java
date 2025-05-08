package Model;
public class Medico extends Usuarios {

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String identificacionDoctor;
    private String especialidad;
    private String añosExperiencia; 

    public Medico(){

    }

    public Medico(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
         String identificacionDoctor, String especialidad, String añosExperiencia) {

        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.identificacionDoctor = identificacionDoctor;
        this.especialidad = especialidad;
        this.añosExperiencia = añosExperiencia;
    }

    public Medico(String primerNombre) {
    this.primerNombre = primerNombre;
}

    public String getIdentificacionDoctor() {
        return identificacionDoctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setIdentificacionDoctor(String identificacionDoctor) {
        this.identificacionDoctor = identificacionDoctor;
    }

    public void setAñosExperiencia(String añosExperiencia) {
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