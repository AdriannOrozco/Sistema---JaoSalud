package Persistencia.Doctor.Medicamentos;

public class MedicamentoItem {

    private int id;
    private String nombre;

    public MedicamentoItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
