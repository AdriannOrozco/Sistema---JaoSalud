package Model;
public class Consultorio { 
    private int numeroConsultorio;
    private String consultorio;
    
    
    //Constructor por defecto
    public Consultorio(){
        
    }
    
    //Constructor con paramétros 
    public Consultorio(String consultorio){
        this.consultorio = consultorio;
    }
    
    //Getters y Setters
    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }
 
}
