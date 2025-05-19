package Model;
import java.util.*;

public class Cita{
    
    private int idConsultorio;
    private String identificacion;
    private String motivo; 
    private Date fechaCita;
    private String hora;
    private int idCita; 
    private Date fechaRegistro;
    private String nombrePaciente;
    private String numeroDocumento;
    private boolean estado;
    
    public Cita(){
        
    }

   
    //Contructor con parámetros
    public Cita(int consultorio, String identificacion, String motivo, Date fechaCita, String hora, int idCita, Date fechaRegistro, String nombrePaciente,String numeroDocumento,boolean estado) {
        this.idConsultorio = idConsultorio;
        this.identificacion = identificacion;
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
    public int getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }  
}