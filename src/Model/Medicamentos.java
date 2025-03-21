
package Model;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDosisDiarias() {
        return dosisDiarias;
    }

    public void setDosisDiarias(int dosisDiarias) {
        this.dosisDiarias = dosisDiarias;
    }
    
    
}
