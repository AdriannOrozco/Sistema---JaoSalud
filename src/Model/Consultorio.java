package Model;
public class Consultorio { 
    
    private String especialidad;
    private String consultorio;
    
    
    //Constructor por defecto
   
    
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


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
   

    
    
 
}
