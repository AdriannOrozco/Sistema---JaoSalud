package Model;
import java.time.LocalTime;
import java.util.*;

public class Cita{
    
    private Consultorio consultorio; //Solicitado
    private Medico medicoAsignado; //Solicitado
    private String motivo; //Solicitado
    private Date fechaCita;
    private String hora; //3
    private int idCita; //4
    private Date fechaRegistro;
    private String nombrePaciente; //2
    private String numeroDocumento; //1
    private boolean estado; //Automático
    
    public Cita(){
        
    }

   
    //Contructor con parámetros
    public Cita(Consultorio consultorio, Medico medicoAsignado, String motivo, Date fechaCita, String hora, int idCita, Date fechaRegistro, String nombrePaciente,String numeroDocumento,boolean estado) {
        this.consultorio = consultorio;
        this.medicoAsignado = medicoAsignado;
        this.motivo = motivo;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.idCita = idCita;
        this.fechaRegistro = fechaRegistro;
        this.nombrePaciente = nombrePaciente;
        this.numeroDocumento = numeroDocumento;
        this.estado = estado;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    //Getters y Setters
    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPaciente() {
        return nombrePaciente;
    }

    public void setPaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }  
}