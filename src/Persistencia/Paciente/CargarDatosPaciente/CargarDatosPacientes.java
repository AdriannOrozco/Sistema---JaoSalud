
package Persistencia.Paciente.CargarDatosPaciente;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Adrian
 */
public class CargarDatosPacientes {
    
    public void cargarDatosPacientes(String numeroDocumento, String tipoDocumento, JTextField txtCargarEPS, JTextField txtCargarPrimerNombre, JTextField txtCargarPrimerApellido) throws Exception{
        ConexionBD con = new ConexionBD();
        String sql = "SELECT EPS, primerNombre, primerApellido FROM pacientes WHERE numeroDocumento = ? AND tipoDocumento = ?";

        try (Connection conexion = con.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            pstmt.setString(2, tipoDocumento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtCargarEPS.setText(rs.getString("EPS"));
                txtCargarPrimerNombre.setText(rs.getString("primerNombre"));
                txtCargarPrimerApellido.setText(rs.getString("primerApellido"));

            } else {
            throw new IllegalArgumentException("El paciente con número de documento ");
            }

        } catch (SQLException e) {
           throw new IllegalArgumentException("Error: " + e.getMessage());
        }
    }
    }

