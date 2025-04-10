package Model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class HistorialMedico extends Cita {
    
    private boolean alergia;
    private String observaciones;
    private ArrayList<String> resultados;
    private ArrayList<Medicamentos> medicamento;
    
    
    public HistorialMedico(){
        
    }
    
    public HistorialMedico(Consultorio consultorio, Medico medicoAsignado, String motivo, Date fechaCita, String hora, int idCita, Date fechaRegistro,
            boolean alergia, String observaciones, ArrayList<String> resultados, ArrayList<Medicamentos> medicamento,
            Paciente paciente, String numeroDocumento, boolean estado){
       
       super(consultorio, medicoAsignado, motivo, fechaCita, hora, idCita, fechaRegistro, paciente,numeroDocumento,estado);
       this.alergia = alergia;
       this.observaciones = observaciones;
       this.resultados = new ArrayList<>(resultados);
       this.medicamento = new ArrayList<>(medicamento);    
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
