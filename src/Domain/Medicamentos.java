
package Domain;

public class Medicamentos {
    
    private String nombre;
    private double precio;
    private int dosisDiarias;
    
    public Medicamentos(){
        
    }
    
    public Medicamentos(String nombre, double precio, int dosisDiarias){
        this.nombre = nombre;
        this.precio = precio;
        this.dosisDiarias = dosisDiarias;
        
    }
}
