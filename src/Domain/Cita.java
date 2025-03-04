package Domain;
import java.util.Date;

public class Cita{
 
    private Date fecha;
    private Date hora;
    private String motivo;
    private String codigo;
    private String numeroDocumento;

    //Constructor por defecto
    public Cita(){
        
    }
    
    //Constructor parametrizado
    public Cita(Date fecha, Date hora, String motivo, String codigo, String numeroDocumento) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.codigo = codigo;
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }    
}