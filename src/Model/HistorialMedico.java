package Model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class HistorialMedico {
    
    private Paciente paciente;
    private ArrayList<Cita> citas;
    private boolean alergia;
    private String observaciones;
    private ArrayList<String> resultados;
    private ArrayList<Medicamentos> medicamento;

    public HistorialMedico(Paciente paciente, ArrayList<Cita> citas, boolean alergia, String observaciones, ArrayList<String> resultados, ArrayList<Medicamentos> medicamento) {
        this.paciente = paciente;
        this.citas = citas;
        this.alergia = alergia;
        this.observaciones = observaciones;
        this.resultados = resultados;
        this.medicamento = medicamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }

    public boolean isAlergia() {
        return alergia;
    }

    public void setAlergia(boolean alergia) {
        this.alergia = alergia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ArrayList<String> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<String> resultados) {
        this.resultados = resultados;
    }

    public ArrayList<Medicamentos> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(ArrayList<Medicamentos> medicamento) {
        this.medicamento = medicamento;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


    