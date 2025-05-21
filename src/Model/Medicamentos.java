package Model;

public class Medicamentos {

    private String nombre;
    private Double precio;
    private Integer dosisDiarias;
    private boolean disponible;

    public Medicamentos() {

    }

    public Medicamentos(String nombre, double precio, int dosisDiarias, boolean disponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.dosisDiarias = dosisDiarias;
        this.disponible = disponible;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getDosisDiarias() {
        return dosisDiarias;
    }

    public void setDosisDiarias(Integer dosisDiarias) {
        this.dosisDiarias = dosisDiarias;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
