package Model;
import java.time.LocalTime;
import java.util.*;

public class Cita{
    
    private Consultorio consultorio;
    private Medico medicoAsignado;
    private String motivo;
    private Date fechaCita;
    private LocalTime hora;
    private Random idCita;
    private Date fechaRegistro;
    private Paciente paciente;
    private String numeroDocumento;
    private boolean estado;

    //Constructor por defecto
    public Cita(){
        
    }

    //Contructor con parámetros
    public Cita(Consultorio consultorio, Medico medicoAsignado, String motivo, Date fechaCita, LocalTime hora, Random idCita, Date fechaRegistro,
            Paciente paciente, String numeroDocumento, boolean estado) {
        this.consultorio = consultorio;
        this.medicoAsignado = medicoAsignado;
        this.motivo = motivo;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.idCita = idCita;
        this.fechaRegistro = fechaRegistro;
        this.paciente = paciente;
        this.numeroDocumento = numeroDocumento;
        this.estado = estado;
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Random getIdCita() {
        return idCita;
    }

    public void setIdCita(Random idCita) {
        this.idCita = idCita;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }  
}