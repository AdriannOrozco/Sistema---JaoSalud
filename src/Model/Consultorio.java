package Model;
public class Consultorio { 
    private String idConsultorio;
    private String especialidad;
    
    
    //Constructor por defecto
    public Consultorio(){
        
    }
    
    //Constructor con paramétros 
    public Consultorio(String idConsultorio, String especialidad){
        this.idConsultorio = idConsultorio;
        this.especialidad = especialidad;
    }
    
    //Getters y Setters

    public String getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(String idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
   

    
    
 
}
