package Comandos;
import javax.swing.JTextField;


/**
 * Esto cargará campos como nombre EPS, primer primer y apelldio.
 * @author Adrian
 * Se quiere tipo y número de documento.
 */

public interface ICargarDatosPacientes {
     void cargarDatosPacientes(String numeroDocumento, String tipoDocumento,JTextField txtCargarEPS, JTextField txtCargarPrimerNombre, JTextField txtCargarPrimerApellido) throws Exception;
}
