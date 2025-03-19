package Domain;
import java.util.ArrayList;
import java.util.Date;
public class Paciente extends Usuarios {
    
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String direccionResidencia;
    private String estadoCivil;
    private String genero;
    private String email;
    private String EPS;
    private String tipoSangre;
    private Date fechaNacimiento;
    private Date fechaRegistro;
    private ArrayList<Cita> cita;
    //private ArrayList<HistorialMedico> historialMedico;
    
  
    public Paciente(){
    }
    
    public Paciente(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String tipoDocumento, String numeroDocumento, String telefono, String direccionResidencia, String estadoCivil,
            String genero, String email, String EPS, String tipoSangre, Date fechaNacimiento, Date fechaRegistro,
            ArrayList<Cita> cita){
     
        
        super(usuario, contraseña, primerNombre, segundoNombre, primerApellido, segundoApellido);  
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.direccionResidencia = direccionResidencia;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.email = email;
        this.EPS = EPS;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.cita = new ArrayList<>(cita);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEPS() {
        return EPS;
    }

    public void setEPS(String EPS) {
        this.EPS = EPS;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ArrayList<Cita> getCita() {
        return cita;
    }

    public void setCita(ArrayList<Cita> cita) {
        this.cita = cita;
    }
   
}
