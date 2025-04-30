package Model;

import java.util.ArrayList;
import java.util.Date;

public class ResultadosMedicos {

    private int idCita; //Forean key
    private int idDiagnostico; //Primary key
    private String diagnostico;
    private boolean alergia;
    private boolean observaciones;
    private ArrayList<Integer> idMedicamentos;
    private Date fechaCreacion;

    public ResultadosMedicos(int idCita, String diagnostico, boolean alergia, boolean observaciones, ArrayList<Integer> idMedicamentos, Date fechaCreacion) {
        this.idCita = idCita;
        this.diagnostico = diagnostico;
        this.alergia = alergia;
        this.observaciones = observaciones;
        this.idMedicamentos = idMedicamentos;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public boolean isAlergia() {
        return alergia;
    }

    public void setAlergia(boolean alergia) {
        this.alergia = alergia;
    }

    public boolean isObservaciones() {
        return observaciones;
    }

    public void setObservaciones(boolean observaciones) {
        this.observaciones = observaciones;
    }

    public ArrayList<Integer> getIdMedicamentos() {
        return idMedicamentos;
    }

    public void setIdMedicamentos(ArrayList<Integer> idMedicamentos) {
        this.idMedicamentos = idMedicamentos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ResultadosMedicos(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

}
