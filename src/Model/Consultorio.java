package Model;
public class Consultorio { 
    private int idConsultorio;
    private String consultorio;
    private String especialidad;
    
    //Constructor por defecto
    public Consultorio(){
        
    }
    
    //Constructor con paramétros 
    public Consultorio(String consultorio, String especialidad){
        this.consultorio = consultorio;
        this.especialidad = especialidad;
    }
    
    //Getters y Setters
    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public int getIdConsultorio() {
        return idConsultorio;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    
 
}
